//
//  TweetListViewController.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

protocol TweetListDisplayLogic: class {
    func displayLoadInformation(viewModel: TweetList.LoadInformation.ViewModel)
    func displaySelectTweet(viewModel: TweetList.SelectTweet.ViewModel)
    func displayError(viewModel: TweetList.Error.ViewModel)
}

class TweetListViewController: UIViewController, TweetListDisplayLogic {
    var interactor: TweetListBusinessLogic?
    var router: (NSObjectProtocol & TweetListRoutingLogic & TweetListDataPassing)?
    private lazy var tweetListView = TweetListView()
    private var model: TweetList.LoadInformation.ViewModel?
    
    // MARK: Object lifecycle
    
    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
        setup()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setup()
    }
    
    override func loadView() {
        super.loadView()
        view = tweetListView
    }
    
    // MARK: Setup
    
    private func setup() {
        let viewController = self
        let interactor = TweetListInteractor(presenter: TweetListPresenter(viewController: self), worker: TweetListWorker(service: TwitterAnalyzerService()))
        let router = TweetListRouter()
        viewController.interactor = interactor
        viewController.router = router
        router.viewController = viewController
        router.dataStore = interactor
    }
    
    // MARK: View lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        title = "Tweet List"
        setupTableView()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        loadInformation()
    }
    
    func setupTableView() {
        tweetListView.tweetsTableView.delegate = self
        tweetListView.tweetsTableView.dataSource = self
        tweetListView.tweetsTableView.register(TweetListTableViewCell.self, forCellReuseIdentifier: TweetListTableViewCell.identifier)
        tweetListView.tweetsTableView.register(TweetListHeaderView.self, forHeaderFooterViewReuseIdentifier: TweetListHeaderView.identifier)
    }
    
    // MARK: Load Information
    
    func loadInformation() {
        LoadingView.addToView(view)
        tweetListView.tweetsTableView.isHidden = true
        interactor?.loadInformation(request: TweetList.LoadInformation.Request())
    }
    
    func displayLoadInformation(viewModel: TweetList.LoadInformation.ViewModel) {
        model = viewModel
        DispatchQueue.main.async { [weak self] in
            if let self = self {
                self.tweetListView.tweetsTableView.reloadData()
                self.tweetListView.tweetsTableView.isHidden = false
                LoadingView.removeFromView(self.view)
            }
        }
    }
    
    // MARK: Select Tweet
    
    func selectTweet(at row: Int) {
        LoadingView.addToView(view)
        interactor?.selectTweet(request: TweetList.SelectTweet.Request(row: row))
    }
    
    func displaySelectTweet(viewModel: TweetList.SelectTweet.ViewModel) {
        LoadingView.removeFromView(view)
        router?.routeToTweetSentiment()
    }
    
    // MARK: Error
    
    func displayError(viewModel: TweetList.Error.ViewModel) {
        UIAlertController.showAlert(in: self, withTitle: viewModel.errorTitle, message: viewModel.errorMessage)
    }
}

extension TweetListViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        selectTweet(at: indexPath.row)
    }
}

extension TweetListViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        guard let model = model else {
            return 0
        }
        return model.tweets.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if let model = model {
            if indexPath.row < model.tweets.count {
                let tweet = model.tweets[indexPath.row]
                if let cell = tableView.dequeueReusableCell(withIdentifier: TweetListTableViewCell.identifier) as? TweetListTableViewCell {
                    cell.setTweetInformation(text: tweet.text, date: tweet.date, detail: tweet.detail)
                    return cell
                }
            }
        }
        return UITableViewCell()
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        guard let model = model else {
            return UIView()
        }
        if let header = tableView.dequeueReusableHeaderFooterView(withIdentifier: TweetListHeaderView.identifier) as? TweetListHeaderView {
            header.setHeaderInformation(name: model.name, user: model.user, profileImage: model.profileImage)
            return header
        }
        return UIView()
    }
}
