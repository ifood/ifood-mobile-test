//
//  TweetAnalyzeController.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import UIKit
import RxSwift

final class TweetAnalyzeController: UIViewController {
    private var tweetAnalyzeView: TweetAnalyzeView
    private var viewModel: TweetAnalyzeViewModelInput & TweetAnalyzeViewModelOutput
    
    var onBack = PublishSubject<Void>()
    var bag = DisposeBag()
    
    init(viewModel: TweetAnalyzeViewModelInput & TweetAnalyzeViewModelOutput) {
        self.viewModel = viewModel
        tweetAnalyzeView = TweetAnalyzeView()
        super.init(nibName: nil, bundle: nil)
        bindProperties()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func loadView() {
        self.view = tweetAnalyzeView
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        setupNavigationbar()
    }
    
    private func setupNavigationbar() {
        self.navigationController?.isNavigationBarHidden = false
        let backButton = UIBarButtonItem(image: Asset.icBack.image, style: .plain, target: nil, action: nil)
        backButton.rx.tap.bind(to: onBack).disposed(by: bag)
        navigationItem.leftBarButtonItem = backButton
        navigationItem.title = "Sentimental Analyzer"
    }
    
    private func bindProperties() {
        viewModel.bgColor.bind(to: tweetAnalyzeView.rx.backgroundColor).disposed(by: bag)
        viewModel.emoji.bind(to: tweetAnalyzeView.emoji.rx.text).disposed(by: bag)
    }
}
