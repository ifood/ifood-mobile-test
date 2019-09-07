//
//  TweetListViewController.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit
import RxDataSources
import RxSwift
import RxCocoa
import Domain
import Utility

typealias TweetSectionModel = SectionModel<String, Tweet>

final class TweetListViewController: ViewController<TweetListView>, BindableType {
    
    // MARK: Var
    
    typealias ViewModelType = TweetListViewModel
    var viewModel: TweetListViewModel!
    
    lazy var datasource: RxTableViewSectionedReloadDataSource<TweetSectionModel> = {
        return RxTableViewSectionedReloadDataSource<TweetSectionModel>(configureCell: { (_, tableView, indexPath, tweet) -> UITableViewCell in
            do {
                return try tableView.dequeueReusableClass(TweetCell.self, at: indexPath, object: tweet)
            } catch let error {
                fatalError(error.localizedDescription)
            }
        })
    }()
    
    // MARK: Init

    func bindViewModel() {
        
        let pull = self.baseView.tableView.refresh.rx.controlEvent(.valueChanged).asDriver()
        
        let input = TweetListViewModel.Input(trigger: Driver.merge([self.rx.viewWillAppear.mapToVoid(), pull]), onTapTweet: self.baseView.tableView.rx.modelSelected(Tweet.self).asDriver())
        let output = viewModel.transform(input: input)
        
        output.tweetList.drive(self.baseView.tableView.rx.items(dataSource: self.datasource)).disposed(by: self.rx.disposeBag)
        
        output.title.drive(self.rx.title).disposed(by: self.rx.disposeBag)
        output.fetching.drive(self.baseView.tableView.refresh.onRefreshing).disposed(by: self.rx.disposeBag)
        
        output.onTapTweet.drive().disposed(by: self.rx.disposeBag)
    }
}
