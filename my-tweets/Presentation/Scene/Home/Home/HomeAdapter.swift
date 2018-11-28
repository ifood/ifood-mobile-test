//
//  HomeAdapter.swift
//  my-tweets
//
//  Created by Gabriel Catice on 25/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

class HomeAdapter: NSObject, SceneTableViewAdapter {
    var disposeBag = DisposeBag()
    var dataSource: [HomeVMs.Tweet] = []
    var tableView: UITableView
    
    private var onTryAgainSubject: PublishSubject<Void> = PublishSubject<Void>()
    var onTryAgain: Observable<Void> { return onTryAgainSubject }
    
    init(tableView: UITableView) {
        self.tableView = tableView
        super.init()
        
        let refreshControl = UIRefreshControl()
        refreshControl.rx.controlEvent([.valueChanged]).asObservable().bind(to: onTryAgainSubject).disposed(by: disposeBag)
        tableView.refreshControl = refreshControl
        
        self.tableView.register(HomeTableViewCell.nib, forCellReuseIdentifier: HomeTableViewCell.reuseIdentifier)
        self.tableView.dataSource = self
        self.tableView.delegate = self
    }
    
    func setData(_ data: [HomeVMs.Tweet]) {
        self.dataSource = data
        self.tableView.reloadData()
    }
}

extension HomeAdapter {
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return dataSource.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: HomeTableViewCell.reuseIdentifier) as? HomeTableViewCell else {
            return UITableViewCell()
        }
        cell.configure(viewModel: dataSource[indexPath.row])
        return cell
    }
    
    func tableView(_ tableView: UITableView, estimatedHeightForRowAt indexPath: IndexPath) -> CGFloat {
        return 90
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 0.1
    }
    
    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        return 15
    }
}
