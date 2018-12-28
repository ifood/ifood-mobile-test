//
//  Sentiment.swift
//  ifood-mobile-test
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation
import Resources

public enum Sentiment {
    case happy
    case neutral
    case sad
    case error
}

public struct SentimentOutput {
    public let sentiment: Sentiment
    public let emoji: String
    public let text: String
    public let color: UIColor
}

extension SentimentOutput: Equatable {
    public static func ==(lhs: SentimentOutput, rhs: SentimentOutput) -> Bool {
        return lhs.sentiment == rhs.sentiment &&
                lhs.emoji == rhs.emoji &&
                lhs.text == rhs.text &&
                lhs.color == rhs.color
    }
}

private func color(_ sentiment: Sentiment) -> UIColor {
    switch sentiment {
    case .happy:
        return .vibrantYellow
    case .neutral:
        return .neutralGray
    case .sad:
        return .sadBlue
    case .error:
        return .white
    }
}

private func emoji(_ sentiment: Sentiment) -> String {
    switch sentiment {
    case .happy:
        return "😃"
    case .neutral:
        return "😐"
    case .sad:
        return "😔"
    case .error:
        return "❌"
    }
}

private func text(_ sentiment: Sentiment) -> String {
    switch sentiment {
    case .happy:
        return L10n.Sentiment.happyTweet
    case .neutral:
        return L10n.Sentiment.neutralTweet
    case .sad:
        return L10n.Sentiment.sadTweet
    case .error:
        return L10n.TwitterError.couldNotLoadSentiment
    }
}

public func sentimentFactory(sentiment: Sentiment) -> SentimentOutput {
    return SentimentOutput(sentiment: sentiment, emoji: emoji(sentiment), text: text(sentiment), color: color(sentiment))
}
