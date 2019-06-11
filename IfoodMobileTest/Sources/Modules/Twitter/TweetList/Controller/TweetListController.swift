//
//  TweetListController.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

final class TweetListController: UIViewController {
    private var tweetListView: TweetListView
    private var viewModel: TweetListViewModelInput & TweetListViewModelOutput
    private var bag = DisposeBag()
    
    var onBack = PublishSubject<Void>()
    var onSentimentAnalyzer = PublishSubject<String>()
    
    init(viewModel: TweetListViewModelInput & TweetListViewModelOutput) {
        self.viewModel = viewModel
        tweetListView = TweetListView()
        super.init(nibName: nil, bundle: nil)
        bindTableView()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func loadView() {
        self.view = tweetListView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupNavigationbar()
    }
    
    private func setupNavigationbar() {
        self.navigationController?.isNavigationBarHidden = false
        let backButton = UIBarButtonItem(image: Asset.icBack.image, style: .plain, target: nil, action: nil)
        backButton.rx.tap.bind(to: onBack).disposed(by: bag)
        navigationItem.leftBarButtonItem = backButton
        navigationItem.title = viewModel.title
    }
    
    private func bindTableView() {
        viewModel
            .tweets
            .bind(to: tweetListView
                .rx
                .items(cellIdentifier: "TweetListCell",
                       cellType: TweetListCell.self)) { (_, tweet: TweetModel, cell: TweetListCell) in
                        cell.configuretion(viewModel: TweetListCellViewModel(tweet: tweet))
            }.disposed(by: bag)
        
        tweetListView
            .rx
            .modelSelected(TweetModel.self)
            .map { $0.text ?? "" }
            .bind(to: onSentimentAnalyzer)
            .disposed(by: bag)
    }
}
