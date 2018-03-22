//
//  ShowSentimentDataProvider.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation

public enum Feeling {

    case happy
    case normal
    case bad
    case none

    var emojiValue: String {
        switch self {
        case .happy:
            return "😊"
        case .normal:
            return "😐"
        case .bad:
            return "😔"
        case .none:
            return "😶"
        }
    }

    var textValue: String {
        switch self {
        case .happy:
            return "Happy"
        case .normal:
            return "Neutral"
        case .bad:
            return "Sad"
        case .none:
            return "Nothing"
        }
    }

    var showTextValue: String {
        return self.emojiValue + "\n" + self.textValue
    }
}

struct SentimentConverter {

    static func analyzeFeeling(document: DocumentSentiment) -> Feeling {

        guard let sentiment = document.magnitude else {
            return .none
        }

        guard let score = document.score else {
            return .none
        }

        switch sentiment {
        case 0...0.09999:
            return .normal
        default:
            break
        }

        switch score {
        case 0:
            return .normal
        case let score where score < 0:
            return .bad
        case let score where score > 0:
            return .happy
        default:
            return .none
        }
    }

}

protocol ShowSentimentDataProvider: AbstractDataProvider {
    func reloadData(_ provider: AbstractDataProvider?, viewModels: TwitterSentimentViewModel)
    func reloadData(_ provider: AbstractDataProvider?, sentiments: Feeling)
}

class ShowSentimentDataProviderManager: AbstractDataProviderManager<ShowSentimentDataProvider, TwitterSentimentViewModel> {

    func avaliateSentiment(_ message: String) {
        GoogleAPIStore().sentimentsOf(tweet: message) { [weak self] (result, error) in
            guard let _error = error else {
                guard let _result = result else {
                    self?.dataProvider?.errorData(self?.dataProvider, error: (self?.dataError)!)
                    return
                }
                let feeling = SentimentConverter.analyzeFeeling(document: _result)
                self?.dataProvider?.reloadData(self?.dataProvider, sentiments: feeling)
                return
            }
            self?.dataProvider?.errorData(self?.dataProvider, error: _error)
        }
    }
}
