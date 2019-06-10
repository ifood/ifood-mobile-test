//
//  TweetAnalyzeViewModel.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

protocol TweetAnalyzeViewModelOutput {
    var bgColor: BehaviorSubject<UIColor> { get }
    var emoji: BehaviorSubject<String> { get }
}

protocol TweetAnalyzeViewModelInput {}

final class TweetAnalyzeViewModel: TweetAnalyzeViewModelOutput, TweetAnalyzeViewModelInput {

    private var text: String
    
    var bgColor: BehaviorSubject<UIColor>
    var emoji: BehaviorSubject<String>
    
    init(text: String) {
        self.text = text
        bgColor = BehaviorSubject<UIColor>(value: .white)
        emoji = BehaviorSubject<String>(value: "")
    }
}
