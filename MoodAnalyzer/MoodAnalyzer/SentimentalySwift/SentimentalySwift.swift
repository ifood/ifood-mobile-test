// Sentimentaly Swift - https://github.com/benbahrenburg/SentimentlySwift

import Foundation


public struct wordToken {
    let word: String
    let wordStem: String?
    init(word: String, wordStem: String?) {
        self.word = word
        self.wordStem = wordStem
    }
    
    func hasWordStemValue() -> Bool {
        if let stemValue = wordStem {
            return stemValue.lowercased() != word.lowercased()
        }
        return false
    }
}

public struct analysisResult {
    var score: Int
    var phrase: String
    var comparative: Double
    var positive: [String]
    var negative: [String]
    var wordTokens: [wordToken]
}

public struct sentimentWeightValue {
    public init(word: String, score: Int) {
        self.word = word
        self.score = score
    }
    var word: String
    var score: Int
}

public protocol sentimentAdjusters {
    var negators: [String] {get set}
    var incrementors: [String] {get set}
    var hybrid: [String] {get set}
}

internal struct defaultAdjusters: sentimentAdjusters {
    var negators = [
        "cant",
        "can't",
        "didnt",
        "didn't",
        "dont",
        "don't",
        "doesnt",
        "doesn't",
        "not",
        "non",
        "wont",
        "won't",
        "isnt",
        "isn't"
    ]
    
    var incrementors = [
        "very", "really"
    ]
    
    var hybrid = [
        "super", "extremely"
    ]
}

internal struct applyWeights {
    let weights: sentimentAdjusters
    
    init(weights: sentimentAdjusters) {
        self.weights = weights
    }
    
    func tokenContained(token: wordToken, array: [String]) -> Bool {
        if array.contains(token.word) {
            return true
        }
        if token.hasWordStemValue() {
            return array.contains(token.wordStem!)
        }
        return false
    }
    
    func applyNegatorScore(token: wordToken) -> Int {
        return tokenContained(token: token, array: weights.negators) ? -1 : 0
    }
    
    func applyIncrementorScore(token: wordToken) -> Int {
        return tokenContained(token: token, array: weights.incrementors) ? 1 : 0
    }
    
    func applyHybridScore(token: wordToken, defaultScore: Int) -> Int {
        return tokenContained(token: token, array: weights.incrementors) ? defaultScore : 0
    }
}

public struct Sentimently {
    fileprivate var wordSource: [NSString: AnyObject]?
    fileprivate let adjustments: sentimentAdjusters
    
    fileprivate func loadWordList(path: URL) -> [NSString: AnyObject]? {
        do {
            let data = try Data(contentsOf: path, options: .alwaysMapped)
            guard let jsonResult: Dictionary<NSString, AnyObject> = try JSONSerialization.jsonObject(with: data, options: .mutableContainers) as? [NSString: AnyObject] else {
                return [NSString: AnyObject]()
            }
            
            return jsonResult
            
        } catch let error as NSError {
            print(error.localizedDescription)
            return nil
        }
    }
    
    fileprivate func defaultAFINNUrl() -> URL {
        return URL(fileURLWithPath: Bundle.main.path(forResource: "AFINN", ofType: "json")!)
    }
    
    public init(rulesFile: URL? = nil, adjustments: sentimentAdjusters? = nil, addWeights: [sentimentWeightValue] = []) {
        self.adjustments = adjustments ?? defaultAdjusters()
        let rulesFile = rulesFile ?? defaultAFINNUrl()
        self.wordSource = loadWordList(path: rulesFile)
        
        if addWeights.count > 0  {
            for injectItem in addWeights {
                wordSource?[injectItem.word as NSString] = injectItem.score as AnyObject
            }
        }
        
    }
    
    fileprivate func getWordScore(token: wordToken, wordSource: [NSString: AnyObject]) -> Int {
        var score: Int = 0
        
        if let wordValue = wordSource[token.word as NSString] {
            score = Int(truncating: wordValue as! NSNumber)
        }
        
        if token.hasWordStemValue() {
            if let stemValue = wordSource[token.wordStem! as NSString] {
                let stemScore = Int(truncating: stemValue as! NSNumber)
                if abs(stemScore) > abs(score) {
                    score = stemScore
                }
            }
        }
        
        return score
    }
    
    fileprivate func lemmatize(_ text: String) -> [wordToken] {
        let text = text.lowercased()
        let options: NSLinguisticTagger.Options = [.omitWhitespace, .omitPunctuation, .omitOther]
        let tagger = NSLinguisticTagger(tagSchemes: NSLinguisticTagger.availableTagSchemes(forLanguage: "en"),
                                        options: Int(options.rawValue))
        tagger.string = text
        
        var tokens: [wordToken] = []
        
        tagger.enumerateTags(in: NSMakeRange(0, text.count), scheme: NSLinguisticTagScheme.lemma, options: options) { tag, tokenRange, _, _ in
            let word = (text as NSString).substring(with: tokenRange)
            tokens.append(wordToken(word: word, wordStem: tag.map { $0.rawValue }))
        }
        return tokens
    }
    
    public func score(_ phrase: String, addWeights: [sentimentWeightValue] = []) -> analysisResult {
        
        var output = analysisResult(score: 0, phrase: phrase, comparative: 0, positive: [], negative: [], wordTokens: [])
        
        guard phrase.trimmingCharacters(in: .whitespacesAndNewlines).count > 0 else {
            return output
        }
        
        guard var wordSource = wordSource else {
            return output
        }
        
        if addWeights.count > 0  {
            for injectItem in addWeights {
                wordSource[injectItem.word as NSString] = injectItem.score as AnyObject
            }
        }
        
        output.wordTokens = lemmatize(phrase)
        guard output.wordTokens.count > 0 else {
            return output
        }
        
        let adjustmentProvider = applyWeights(weights: adjustments)
        
        for position in 0...output.wordTokens.count - 1 {
            let token = output.wordTokens[position]
            let wordScore = getWordScore(token: token, wordSource: wordSource)
            var itemScore = wordScore
            if position > 0 {
                let prevtoken = output.wordTokens[position - 1]
                itemScore += adjustmentProvider.applyNegatorScore(token: prevtoken)
                itemScore += adjustmentProvider.applyIncrementorScore(token: prevtoken)
                itemScore += adjustmentProvider.applyHybridScore(token: prevtoken, defaultScore: wordScore)
            }
            
            if itemScore > 0 {
                output.positive.append(token.word)
            }
            if itemScore < 0 {
                output.negative.append(token.word)
            }
            output.score += itemScore
        }
        
        output.comparative = output.wordTokens.count > 0 ? Double(Double(output.score) / Double(output.wordTokens.count)) : 0
        return output
    }
}
