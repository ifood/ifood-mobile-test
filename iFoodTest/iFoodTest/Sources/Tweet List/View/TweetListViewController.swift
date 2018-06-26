//
//  TweetListViewController.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class TweetListViewController: UIViewController, TweetListViewProtocol {
    
    fileprivate let kTableViewCellIdentifier = "TweetListTableViewCell"
    fileprivate var tweets: [Tweet] = []    
    
    var presenter: TweetListPresenterProtocol!
    var mainView: TweetListView {
        guard let mainView = self.view as? TweetListView else { fatalError("ControllerWithMainViewFatalErrorMainView") }
        return mainView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupView()
        presenter.viewDidLoad()
    }
    
    fileprivate func setupView() {
        mainView.showLoadingScreen()
        
        mainView.tableView.rowHeight = UITableViewAutomaticDimension
        mainView.tableView.estimatedRowHeight = 230.0
        let nib = UINib(nibName: kTableViewCellIdentifier, bundle: nil)
        mainView.tableView.register(nib, forCellReuseIdentifier: kTableViewCellIdentifier)
    }
    
    func displayTweets(_ tweets: [Tweet], forUser user: String) {
        mainView.hideLoadingScreen()
        
        self.tweets = tweets
        mainView.tableView.reloadData()
        navigationItem.title = "@\(user)"
    }
    
    func displayErrorMessage(_ error: String) {
        mainView.showError(error)
    }
    
}

extension TweetListViewController: UITableViewDataSource, UITableViewDelegate {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return tweets.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: kTableViewCellIdentifier) as! TweetListTableViewCell
        let tweet = tweets[indexPath.row]
        
        cell.setup(tweet)
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let tweet = tweets[indexPath.row]
        presenter.didSelectTweeet(tweet)
    }
}
