//
//  TweetSentimentPresenter.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

protocol TweetSentimentPresentationLogic {
    func presentLoadInformation(response: TweetSentiment.LoadInformation.Response)
}

class TweetSentimentPresenter: TweetSentimentPresentationLogic {
    weak var viewController: TweetSentimentDisplayLogic?
    
    // MARK: Load Information
    
    func presentLoadInformation(response: TweetSentiment.LoadInformation.Response) {
        var color: UIColor = .white
        var emoji: String = ""
        switch response.sentiment {
        case .NEGATIVE:
            color = .sentimentBlue
            emoji = "😔"
        case .NEUTRAL:
            color = .sentimentGray
            emoji = "😐"
        case .POSITIVE:
            color = .sentimentYellow
            emoji = "😃"
        }
        viewController?.displayLoadInformation(viewModel: TweetSentiment.LoadInformation.ViewModel(backgroundColor: color,
                                                                                                   emoji: emoji))
    }
}

private extension UIColor {
    static let sentimentYellow: UIColor = UIColor.colorWith(red: 255, green: 247, blue: 17)
    static let sentimentGray: UIColor = UIColor.colorWith(red: 84, green: 95, blue: 102)
    static let sentimentBlue: UIColor = UIColor.colorWith(red: 35, green: 87, blue: 137)
}
