//
//  ListTweetsViewModel.swift
//  ListTweets
//
//  Created by Kaique de Souza Monteiro on 06/09/2018.
//

import Foundation
import SwiftyJSON
import TwitterKit
import CoreML

/// Prove os dados para a tela que mostra os tweets
class ListTweetsViewModel {
   
    /// Enum para tratar erros no modelo de analise textual
    private enum Error: Swift.Error {
        case featuresMissing
    }
    
    /// modelo que sera usado para analisar os tweets
    private let model = SentimentPolarity()
    
    /// opcoes de configuracao para a separacao das palavras do tweet
    private let options: NSLinguisticTagger.Options = [.omitWhitespace, .omitPunctuation, .omitOther]
    
    /// opcoes de configuracao para o modelo de aprendizagem
    private lazy var tagger: NSLinguisticTagger = .init(
        tagSchemes: NSLinguisticTagger.availableTagSchemes(forLanguage: "en"),
        options: Int(self.options.rawValue)
    )
    
    /// Envia requisição para obter os tweets
    ///
    /// - Parameters:
    ///     - user: nome de usuario informado
    ///     - success: callback chamada ao fim da requisição se bem sucedida
    ///     - failure: callback chamada se houver algum erro
    ///     - isTest: flag utilizada para escolher um arquivo json de testes caso precise
    func getTweetsFrom(user: String?, success: @escaping ([String]) -> Void, failure: @escaping () -> Void, isTest : Bool = false) {
        var tweets : [String] = []
        if (isTest) {
            if let filepath = Bundle.main.path(forResource: "tweets", ofType: "json") {
                let data = NSData(contentsOfFile: filepath)
                let json = JSON(data as Any)
                for (_,subJson):(String, JSON) in json {
                    tweets.append(subJson.dictionaryObject!["text"] as! String)
                }
                success(tweets)
            } else {
                failure()
            }
            
        } else {
            let twError:NSErrorPointer = nil
            let client = TWTRAPIClient()
            let request = client.urlRequest(withMethod: "GET",
                                            url: Constants.TwitterApiBaseURL + Constants.URL.GetTweets,
                                            parameters: ["screen_name":user as Any],
                                            error: twError)
            if twError == nil {
                client.sendTwitterRequest(request, completion: { (response, data, error) -> Void in
                    do {
                        let json = try JSON(data: data!)
                        for (_,subJson):(String, JSON) in json {
                            tweets.append(subJson.dictionaryObject!["text"] as! String)
                        }
                        success(tweets)
                    } catch {
                        failure()
                    }
                })
            }
        }
    }
    
    /// Analisa o texto do tweet para definir o sentimento. Codigo fortemente baseado na seguinte demo
    /// https://github.com/cocoa-ai/SentimentCoreMLDemo
    ///
    /// - Parameters:
    ///     - tweet: texto do tweet selecionado
    ///     - success: callback chamada ao fim da analise
    func getSentimentalAnalysisFrom(tweet: String, success: (Sentiment) -> Void) {
        do {
            let inputFeatures = features(from: tweet)
            // Faz a analise apenas em frases com 2 ou mais palavras
            guard inputFeatures.count > 1 else {
                throw Error.featuresMissing
            }
            
            let output = try model.prediction(input: inputFeatures)
            
            switch output.classLabel {
            case "Pos":
                success(.positive)
            case "Neg":
                success(.negative)
            default:
                success(.neutral)
            }
        } catch {
            return success(.neutral)
        }
        
    }
}
    
private extension ListTweetsViewModel {
    
    /// Separa as palavras de um tweet, contando o numero de repeticoes
    ///
    /// - Parameters:
    ///     - text: texto a ser separado
    /// - Returns: palavras e o numero de vezes que ela aparece
    func features(from text: String) -> [String: Double] {
        var wordCounts = [String: Double]()
        
        tagger.string = text
        let range = NSRange(location: 0, length: text.utf16.count)
        
        // Tokenize and count the sentence
        tagger.enumerateTags(in: range, scheme: .nameType, options: options) { _, tokenRange, _, _ in
            let token = (text as NSString).substring(with: tokenRange).lowercased()
            // Skip small words
            guard token.count >= 3 else {
                return
            }
            
            if let value = wordCounts[token] {
                wordCounts[token] = value + 1.0
            } else {
                wordCounts[token] = 1.0
            }
        }
        
        return wordCounts
    }
}

