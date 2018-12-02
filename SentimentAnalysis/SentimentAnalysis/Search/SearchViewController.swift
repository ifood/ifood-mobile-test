//
//  ViewController.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 27/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import UIKit

class SearchViewController: UIViewController, ErrorDisplayer, Loadable {

    @IBOutlet weak var tableView: UITableView!
    
    let searchController = UISearchController(searchResultsController: nil)
    var pendingRequestWorkItem: DispatchWorkItem?
    
    var viewModel: SearchViewModel!
    
    init(viewModel: SearchViewModel) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupTableView()
        setupSearch()
        setupObservers()
    }
    
    func setupObservers() {
        viewModel.searchState.onUpdate = { [weak self] state in
            self?.stopAnimating()
            switch state {
            case .loading:
               self?.startLoading()
                break
            case .load:
                self?.tableView.reloadData()
                break
            case .empty:
                self?.tableView.reloadData()
                break
            case .error(let error):
                self?.show(error)
                break
            }
        }
    }
}

// MARK: Search
extension SearchViewController {
    
    func setupSearch() {
        searchController.searchResultsUpdater = self
        searchController.obscuresBackgroundDuringPresentation = false
        searchController.searchBar.placeholder = "Search users"
        navigationItem.searchController = searchController
        navigationItem.title = "Sentiment Analysis"
        navigationItem.hidesSearchBarWhenScrolling = false
        navigationItem.largeTitleDisplayMode = .always
        definesPresentationContext = true
    }
}

// MARK: UISearchResultsUpdating
extension SearchViewController: UISearchResultsUpdating {
    
    func updateSearchResults(for searchController: UISearchController) {
        print(searchController.searchBar.text ?? "Empty")
        
        guard !searchController.searchBar.text.isNilOrEmpty else {
            return
        }
        
        let searchText = searchController.searchBar.text!
        
        pendingRequestWorkItem?.cancel()

        let requestWorkItem = DispatchWorkItem { [weak self] in
            self?.viewModel.search(with: searchText)
        }
        
        pendingRequestWorkItem = requestWorkItem
        DispatchQueue
            .main
            .asyncAfter(
                deadline: .now() + .milliseconds(500),
                execute: requestWorkItem
        )
        
    }
}

// MARK: TableView
extension SearchViewController {
    
    func setupTableView() {
        tableView.register(cellType: TweetTableViewCell.self)
        tableView.dataSource = self
        tableView.delegate = self
        tableView.estimatedRowHeight = 60
        tableView.rowHeight = UITableView.automaticDimension
    }
}

// MARK: UITableViewDataSource
extension SearchViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.numberOfItems
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(for: indexPath) as TweetTableViewCell
        let tweet = viewModel.item(at: indexPath.row)
        cell.render(tweet)
        return cell
    }
}

// MARK: UITableViewDelegate
extension SearchViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        viewModel.process(with: indexPath.row)
    }
}

