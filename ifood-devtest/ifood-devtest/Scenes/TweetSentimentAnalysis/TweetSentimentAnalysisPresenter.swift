//
//  TweetSentimentAnalysisPresenter.swift
//  ifood-devtest
//
//  Created by Rafael Zilião on 29/11/18.
//  Copyright (c) 2018 Rafael Zilião. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//

import UIKit


enum SentimentAnalyzed {
    case sad
    case happy
    case neutral
}

extension SentimentAnalyzed {
    
    var typeOftweet: (emoji: String, text: String, color: UIColor) {
        switch self {
        case .sad:
            return (emoji: "😔", text: "This is a sad tweet", color: .twitterVerifiedBlue)
        case .happy:
            return (emoji: "😃", text: "This is a happy tweet", color: .yellow)
        case .neutral:
            return (emoji: "😐", text: "This is a neutral tweet", color: .gray)
            
        }
    }
    
}

protocol TweetSentimentAnalysisPresentationLogic {
    func presentAnalyzedSentiment(response: TweetSentimentAnalysis.SentimentAnalyzed.Response)
    func presentAnalyzedSentimentError()
}

class TweetSentimentAnalysisPresenter: TweetSentimentAnalysisPresentationLogic {
    weak var viewController: TweetSentimentAnalysisDisplayLogic?
  
    // MARK: Presentation
    private func analyzedSentiment(score: Double) -> (emoji: String, text: String, color: UIColor){
        if score < 0.0{
            return SentimentAnalyzed.sad.typeOftweet
        } else if score > 0.0 {
            return SentimentAnalyzed.happy.typeOftweet
        } else {
            return SentimentAnalyzed.neutral.typeOftweet
        }
    }

    func presentAnalyzedSentiment(response: TweetSentimentAnalysis.SentimentAnalyzed.Response) {
        
        guard let score = response.sentimentAnalysis.documentSentiment?.score else {
            return
        }

        let textAlignment = NSMutableParagraphStyle()
        textAlignment.alignment = .center
        
        let emojiAttributes = [NSAttributedStringKey.font : UIFont.systemFont(ofSize: 80),
                               NSAttributedStringKey.paragraphStyle: textAlignment]
        
        let typeOfTweetAttributes = [NSAttributedStringKey.font : UIFont.boldSystemFont(ofSize: 20),
                                     NSAttributedStringKey.paragraphStyle: textAlignment]
        
        let tweetAttributes = [NSAttributedStringKey.font : UIFont.systemFont(ofSize: 12),
                                     NSAttributedStringKey.paragraphStyle: textAlignment]

        // emoji
        let formattedText = NSMutableAttributedString(string: analyzedSentiment(score: score).emoji,
                                                      attributes: emojiAttributes)
       // result of analysis
        formattedText.append(NSMutableAttributedString(string: "\n\n\n\(analyzedSentiment(score: score).text)",
                                                       attributes: typeOfTweetAttributes))
        
        // tweet text
        formattedText.append(NSMutableAttributedString(string: "\n\n\(response.tweet)", attributes: tweetAttributes))
        
        let sentimentAnalyzed = (formattedText: formattedText, backgroundColor: analyzedSentiment(score: score).color)
        
        let viewModel = TweetSentimentAnalysis.SentimentAnalyzed.ViewModel(sentimentAnalyzed: sentimentAnalyzed)
        
        viewController?.displayAnalyzedSentiment(viewModel: viewModel)
    }
    
    func presentAnalyzedSentimentError() {
        
    }
}

