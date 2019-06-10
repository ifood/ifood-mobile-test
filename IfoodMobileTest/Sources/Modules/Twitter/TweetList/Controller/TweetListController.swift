//
//  TweetListController.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import UIKit

final class TweetListController: UIViewController {
    private var tweetListView: TweetListView
    private var viewModel: TweetListViewModelInput & TweetListViewModelOutput
    
    init(viewModel: TweetListViewModelInput & TweetListViewModelOutput) {
        self.viewModel = viewModel
        tweetListView = TweetListView()
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func loadView() {
        self.view = tweetListView
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        setupNavigationbar()
    }
    
    private func setupNavigationbar() {
        self.navigationController?.isNavigationBarHidden = false
    }
}
