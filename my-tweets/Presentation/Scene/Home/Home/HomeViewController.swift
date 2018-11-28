//
//  HomeViewController.swift
//  my-tweets
//
//  Created by Gabriel Catice on 25/11/18.
//  Copyright (c) 2018 Gabriel Catice. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

protocol HomeViewProtocol: AnyObject, SceneView {
    func displayUserTimeline(viewModel: [HomeVMs.Tweet])
}

class HomeViewController: SceneViewController {
    var presenter: HomePresenterProtocol!

    var adapter: HomeAdapter!
    var userTweets: [HomeVMs.Tweet] = []
    
    var onSearchChange: Observable<String> { return self.onSearchChangeSubject }
    fileprivate var onSearchChangeSubject: PublishSubject<String> = PublishSubject<String>()
    
    let searchController = UISearchController(searchResultsController: nil)
    @IBOutlet var contentTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        adapter = HomeAdapter(tableView: contentTableView)
        
        setupLayout()
        setupObservables()
    }
    func setupLayout() {
        searchController.obscuresBackgroundDuringPresentation = false
        searchController.searchBar.placeholder = R.string.localizable.home_search_bar()
        searchController.searchBar.setSearchFieldBackground(color: UIColor.gray.withAlphaComponent(0.05))
        navigationItem.searchController = searchController
        contentTableView.sectionHeaderHeight = 0.1
        definesPresentationContext = true
    }
    
    func setupObservables() {
        onTryAgain.bind { [unowned self] _ in
            self.presenter.getUserTimeline(username: self.searchController.searchBar.text ?? "")
            }.disposed(by: disposeBag)
        
        searchController.searchBar.rx.searchButtonClicked.map { [unowned self] in self.searchController.searchBar.text ?? ""} .bind { [unowned self] text in
            guard !text.isEmpty else { return }
            self.emptyErrorViewContainer.subviews.first?.removeFromSuperview()
            self.presenter.getUserTimeline(username: text)
            }.disposed(by: disposeBag)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        navigationController?.setDefaultNavigationBarApperance()
        navigationItem.hidesSearchBarWhenScrolling = false
        tabBarController?.tabBar.isHidden = true
    }
}

extension HomeViewController: HomeViewProtocol {
    func displayUserTimeline(viewModel: [HomeVMs.Tweet]) {
        adapter.setData(viewModel)
    }
}
