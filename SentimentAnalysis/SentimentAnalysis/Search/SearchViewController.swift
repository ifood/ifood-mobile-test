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
    
    let emptyVC = EmptyViewController()
    
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
            DispatchQueue.main.async {
                guard let vc = self else { return }
                
                vc.stopAnimating()
                switch state {
                case .loading:
                    vc.startLoading()
                case .empty:
                    vc.emptyVC.reset()
                case .error(let anyError):
                    
                    switch anyError.error {
                    case let error as SearchRepositoryError:
                        vc.emptyVC.render(error)
                        break

                    default:
                        vc.searchController.searchBar.isUserInteractionEnabled = false
                        vc.show(anyError.error, then: { [weak vc] in
                            vc?.searchController.searchBar.text = ""
                            vc?.searchController.searchBar.isUserInteractionEnabled = true
                        })
                    }
                    
                default:
                    return
                }
            }
        }
        
        viewModel.dataSource.onUpdate = { [weak self] _ in
            self?.tableView.reloadData()
        }
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
}

// MARK: Search
extension SearchViewController {
    
    func setupSearch() {
        searchController.searchResultsUpdater = self
        searchController.obscuresBackgroundDuringPresentation = false
        searchController.searchBar.placeholder = Localized(key: "SEARCH_BAR_PLACEHOLDER")
        searchController.searchBar.delegate = self
        navigationItem.searchController = searchController
        navigationItem.largeTitleDisplayMode = .always
        navigationItem.title = Localized(key: "SEARCH_TITLE")
        navigationItem.hidesSearchBarWhenScrolling = false
        definesPresentationContext = true
    }
}

// MARK: UISearchResultsUpdating
extension SearchViewController: UISearchResultsUpdating {
    
    func updateSearchResults(for searchController: UISearchController) {
        
        pendingRequestWorkItem?.cancel()
        
        guard !searchController.searchBar.text.isNilOrEmpty else {
            viewModel.reset()
            return
        }
        
        let searchText = searchController.searchBar.text!
        
        let requestWorkItem = DispatchWorkItem { [weak self] in
            self?.viewModel.search(with: searchText)
        }
        
        pendingRequestWorkItem = requestWorkItem
        DispatchQueue
            .main
            .asyncAfter(
                deadline: .now() + .milliseconds(750),
                execute: requestWorkItem
        )
        
    }
}

// MARK: TableView
extension SearchViewController {
    
    func setupTableView() {
        tableView.register(cellType: TweetTableViewCell.self)
        tableView.dataSource = self
//        tableView.prefetchDataSource = self
        tableView.delegate = self
        tableView.estimatedRowHeight = 60
        tableView.rowHeight = UITableView.automaticDimension

        emptyVC.tapHandler = { [weak self] in
            self?.searchController.searchBar.becomeFirstResponder()
        }
        add(emptyVC)
    }
}

// MARK: UITableViewDataSource
extension SearchViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        let rows = viewModel.numberOfItems
        if rows > 0 {
            emptyVC.remove()
        } else {
            add(emptyVC)
        }
        return rows
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(for: indexPath) as TweetTableViewCell
        let tweet = viewModel.item(at: indexPath.row)
        cell.render(tweet)
        return cell
    }
    
    func scrollViewWillBeginDragging(_ scrollView: UIScrollView) {
        searchController.searchBar.resignFirstResponder()
    }
}

// MARK: UITableViewDelegate
extension SearchViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        viewModel.process(with: indexPath.row)
    }
}

extension SearchViewController: UISearchBarDelegate {
    
    func searchBar(_ searchBar: UISearchBar, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {
        return viewModel.shouldChangeText(with: text)
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        searchBar.resignFirstResponder()
    }
}
