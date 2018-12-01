//
//  ViewController.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 27/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import UIKit
import Moya
import Result
import Reusable

class SearchViewController: UIViewController {

    @IBOutlet weak var tableView: UITableView!
    
    let searchController = UISearchController(searchResultsController: nil)
    var pendingRequestWorkItem: DispatchWorkItem?
    
    var provider: MoyaProvider<TwitterAPI>!
    var authModel: AuthModel! {
        didSet {
            let tuple: (String) = (authModel.token)
            let accessTokenPlugin = AccessTokenPlugin { () -> String in
                return tuple
            }
            
            provider = MoyaProvider<TwitterAPI>(plugins: [accessTokenPlugin])
        }
    }
    
    var tweets = [Tweet]() {
        didSet {
            self.tableView.reloadData()
        }
    }
    
    init(authModel: AuthModel) {
        self.authModel = authModel
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupTableView()
        setupSearch()
    }
}

// MARK: Request
extension SearchViewController {
    typealias Handler = (Result<[Tweet], AnyError>) -> Void
    
    func search(with username: String, then handler: @escaping Handler) {
        provider.request(.search(username: username)) { (result) in
            do {
                let tweets = try result.dematerialize().map([Tweet].self)
                handler(.success(tweets))
            } catch {
                handler(.failure(AnyError(error)))
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
        definesPresentationContext = true
    }
}

// MARK: UISearchResultsUpdating
extension SearchViewController: UISearchResultsUpdating {
    
    func updateSearchResults(for searchController: UISearchController) {
        print(searchController.searchBar.text ?? "Empty")
        
        guard let searchText = searchController.searchBar.text else {
            return
        }
        
        pendingRequestWorkItem?.cancel()
        tweets.removeAll()

        let requestWorkItem = DispatchWorkItem { [weak self] in
            self?.search(with: searchText) { [weak self] result in
                self?.tweets = result.value ?? []
            }
        }
        
        pendingRequestWorkItem = requestWorkItem
        DispatchQueue
            .main
            .asyncAfter(
                deadline: .now() + .milliseconds(3000),
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
        return tweets.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(for: indexPath) as TweetTableViewCell
        let tweet = tweets[indexPath.row]
        cell.render(tweet)
        return cell
    }
}

// MARK: UITableViewDelegate
extension SearchViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print("Item selected at IndexPath \(indexPath)")
        
        let sentimentVC = UIStoryboard(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "SentimentViewController") as! SentimentViewController
        
        sentimentVC.tweet = tweets[indexPath.row]
        
        let navigationVC = UINavigationController(rootViewController: sentimentVC)
        
        present(navigationVC, animated: true, completion: nil)
    }
}

