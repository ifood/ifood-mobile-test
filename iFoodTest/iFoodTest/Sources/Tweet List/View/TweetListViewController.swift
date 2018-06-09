//
//  TweetListViewController.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class TweetListViewController: UIViewController, TweetListViewProtocol {
    
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
        mainView.tableView.rowHeight = UITableViewAutomaticDimension
        mainView.tableView.estimatedRowHeight = 230.0
        let nib = UINib(nibName: "TweetListTableViewCell", bundle: nil)
        mainView.tableView.register(nib, forCellReuseIdentifier: "TweetListTableViewCell")
    }
    
    func displayTweets(_ tweets: [Tweet]) {        
        self.tweets = tweets
        self.mainView.tableView.reloadData()
    }
    
}

extension TweetListViewController: UITableViewDataSource, UITableViewDelegate {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return tweets.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "TweetListTableViewCell") as! TweetListTableViewCell
        let tweet = tweets[indexPath.row]
        
        cell.setup(tweet)
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let tweet = tweets[indexPath.row]
        presenter.didSelectTweeet(tweet)
    }
}
