//
//  UserTweetsViewController.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/6/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import UIKit
import RxSwift

class UserTweetsViewController: BaseViewController, BindableType {
    
    @IBOutlet private weak var tableView: UITableView!
    internal var viewModel: UserTweetsViewModel!
    private(set) var tweetDetailObservable: BehaviorSubject = BehaviorSubject<UserTweetCellViewModel?>(value: nil)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        registerNibs()
        setupTableView()
        bindViewModel()
    }
    
    override func setViewModel(viewModel: Any) {
        guard let viewModel = viewModel as? UserTweetsViewModel else {
            fatalError("viewmodel of unexpected type")
        }
        self.viewModel = viewModel
        viewModel.loadUserTweets()
    }
    
    private func registerNibs() {
        tableView.register(cellType: TweetTableViewCell.self)
    }
    
    private func setupTableView() {
        tableView.tableFooterView = nil
        tableView.backgroundColor = UIColor.clear
        tableView.rowHeight = TweetTableViewCell.cellSize
    }
    
    func bindViewModel() {
        self.title = viewModel.username
        
        viewModel.isLoading.asDriver(onErrorJustReturn: false).drive(onNext: { [weak self] (isLoading) in
            isLoading ? self?.view.lockView() : self?.view.unlockView()
        }).disposed(by: disposeBag)
        
        viewModel.userTweets
            .bind(to: tableView.rx.items(cellIdentifier: TweetTableViewCell.typeName, cellType: TweetTableViewCell.self)) { (_, element, cell) in
                cell.viewModel = element
            }.disposed(by: disposeBag)
        
        tableView
            .rx.modelSelected(UserTweetCellViewModel.self)
            .bind(to: tweetDetailObservable)
            .disposed(by: disposeBag)
        
        viewModel.errorObservable.asObservable()
            .filter({ $0 != nil })
            .subscribe(onNext: { [weak self] (error) in
                let okAction: AlertAction = (title: BaseLocalizable.okTitle, style:.default, handler:({ self?.dismiss(animated: true, completion: nil) }))
                self?.showRequestErrorAlert(error, actions: [okAction])
            }).disposed(by: disposeBag)
    }
}

