//
//  TweetMoodViewModel.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/11/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation
import UIKit

internal protocol TweetMoodViewModelInput {
    var tweetMood: TweetMood { get }
}

class TweetMoodViewModel: BaseViewModel, TweetMoodViewModelInput {
    
    internal var tweetMood: TweetMood
    private var happyRange: ClosedRange<Double> = 0.25...1.0
    private var neutralRange: ClosedRange<Double> = -0.25...0.25
    private var sadRange: ClosedRange<Double> = -1.0 ... -0.25
    
    enum MoodState {
        case happy
        case neutral
        case sad
        case unknown
    }
    
    init(tweetMood: TweetMood) {
        self.tweetMood = tweetMood
    }
    
    var emojiForMood: String {
        switch moodState {
        case .happy:
            return "😃"
        case .neutral:
            return "😐"
        case .sad:
            return "😔"
        default:
            return "🤔"
        }
    }
    
    var colorForMood: UIColor {
        switch moodState {
        case .happy:
            return UIKit.yellowF5CE42
        case .neutral:
            return UIKit.greyCECECE
        case .sad:
            return UIKit.blue42A7F5
        default:
            return UIColor.clear
        }
    }
    
    var moodState: MoodState {
        guard let score = tweetMood.mood?.score else { return .unknown }
        
        if happyRange.contains(score) {
            return .happy
        } else if neutralRange.contains(score) {
            return .neutral
        } else if sadRange.contains(score) {
            return .sad
        } else {
            return .unknown
        }
    }
}
