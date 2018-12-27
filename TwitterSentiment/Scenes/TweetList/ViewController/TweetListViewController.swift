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

class TweetListViewController: ViewController<TweetListView>, BindableType, UICollectionViewDelegateFlowLayout {
    
    // MARK: Var
    
    typealias ViewModelType = TweetListViewModel
    var viewModel: TweetListViewModel!
    
    lazy var datasource: RxCollectionViewSectionedReloadDataSource<TweetSectionModel> = {
        return RxCollectionViewSectionedReloadDataSource<TweetSectionModel>(configureCell: { (_, collection, indexPath, tweet) -> UICollectionViewCell in
            do {
                return try collection.dequeueReusableClass(TweetCell.self, at: indexPath, object: tweet)
            } catch let error {
                fatalError(error.localizedDescription)
            }
        })
    }()
    
    // MARK: Init

    func bindViewModel() {
        
        self.baseView.collectionView.rx.setDelegate(self).disposed(by: self.rx.disposeBag)
        
        let pull = self.baseView.collectionView.refresh.rx.controlEvent(.valueChanged).asDriver()
        
        let input = TweetListViewModel.Input(trigger: Driver.merge([self.rx.viewWillAppear.mapToVoid(), pull]), onTapTweet: self.baseView.collectionView.rx.modelSelected(Tweet.self).asDriver())
        let output = viewModel.transform(input: input)
        
        output.tweetList.drive(self.baseView.collectionView.rx.items(dataSource: self.datasource)).disposed(by: self.rx.disposeBag)
        
        output.title.drive(self.rx.title).disposed(by: self.rx.disposeBag)
        output.fetching.drive(self.baseView.collectionView.refresh.onRefreshing).disposed(by: self.rx.disposeBag)
        
        output.onTapTweet.drive().disposed(by: self.rx.disposeBag)
    }
    
    // MARK: UICollectionViewDelegateFlowLayout
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return collectionView.size(cell: TweetCell.self, at: indexPath, with: self.datasource.sectionModels[indexPath.section].items[indexPath.row])
    }
}
