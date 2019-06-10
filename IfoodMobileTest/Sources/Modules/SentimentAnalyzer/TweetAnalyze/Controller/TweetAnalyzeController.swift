//
//  TweetAnalyzeController.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import UIKit

final class TweetAnalyzeController: UIViewController {
    private var tweetAnalyzeView: TweetAnalyzeView
    private var viewModel: TweetAnalyzeViewModelInput & TweetAnalyzeViewModelOutput
    
    init(viewModel: TweetAnalyzeViewModelInput & TweetAnalyzeViewModelOutput) {
        self.viewModel = viewModel
        tweetAnalyzeView = TweetAnalyzeView()
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
