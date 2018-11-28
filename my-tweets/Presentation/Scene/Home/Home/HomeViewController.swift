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
    //sourcery:begin: inject
    var presenter: HomePresenterProtocol!
    //sourcery:end
    var adapter: HomeAdapter!
    var userTweets: [HomeVMs.Tweet] = []
    
    var onSearchChange: Observable<String> { return self.onSearchChangeSubject }
    fileprivate var onSearchChangeSubject: PublishSubject<String> = PublishSubject<String>()
    
    let searchController = UISearchController(searchResultsController: nil)
    @IBOutlet var contentTableView: UITableView!

    
    //sourcery:begin: data
    //sourcery:end
    
    override func viewDidLoad() {
        super.viewDidLoad()
        adapter = HomeAdapter(tableView: contentTableView)
        
        setupLayout()
        setupObservables()
    }
    func setupLayout() {
        searchController.searchResultsUpdater = self
        searchController.obscuresBackgroundDuringPresentation = false
        searchController.searchBar.placeholder = R.string.localizable.home_search_bar()
        searchController.searchBar.setSearchFieldBackground(color: UIColor(red: 245.0 / 255.0, green: 245.0 / 255.0, blue: 245.0 / 255.0, alpha: 1))
        navigationItem.searchController = searchController
        contentTableView.sectionHeaderHeight = 0.1
        definesPresentationContext = true
    }
    
    func setupObservables() {
        onTryAgain.bind { [unowned self] _ in
            self.presenter.getUserTimeline(username: self.searchController.searchBar.text ?? "")
            }.disposed(by: disposeBag)
    }
    
    func filterContentForSearchText(_ searchText: String, scope: String = "All") {
        self.presenter.getUserTimeline(username: searchText)
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

extension HomeViewController: UISearchResultsUpdating {
    func updateSearchResults(for searchController: UISearchController) {
        self.filterContentForSearchText(searchController.searchBar.text!)
    }
}
