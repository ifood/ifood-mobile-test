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
        search(with: "johnsundell") { [weak self] (result) in
            self?.tweets = result.value ?? []
        }
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

// MARK: TableView
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

// MARK: TableView
extension SearchViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print("Item selected at IndexPath \(indexPath)")
    }
}

