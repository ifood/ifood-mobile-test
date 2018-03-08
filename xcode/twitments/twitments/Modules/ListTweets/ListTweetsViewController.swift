//
//  ListTweetsViewController.swift
//  Project: twitments
//
//  Module: ListTweets
//
//  By Ezequiel 08/03/18
//  Ezequiel 2018
//

// MARK: Imports

import UIKit
import PINRemoteImage
import SwiftyVIPER

// MARK: Protocols

/// Should be conformed to by the `ListTweetsViewController` and referenced by `ListTweetsPresenter`
protocol ListTweetsPresenterViewProtocol: class {
    /** Sets the title for the view
     - parameters:
     - title The title to set
     */
    func set(title: String?)
}

// MARK: -

/// The View Controller for the ListTweets module
class ListTweetsViewController: UIViewController, StoryboardIdentifiable, ListTweetsPresenterViewProtocol {
    
    // MARK: - Constants
    
    // MARK: Variables
    
    var presenter: ListTweetsViewPresenterProtocol?
    private var viewModels: [TwitterResultViewModel] = []
    
    @IBOutlet weak private(set) var header: UILabel? {
        willSet(label) {
            label?.textAlignment = .center
            label?.text = self.viewModels.first?.username
        }
    }
    
    @IBOutlet weak private(set) var profile: UIImageView? {
        willSet(profile) {
            profile?.pin_setImage(from: URL(string: (self.viewModels.first?.profileImageURL)!))
            profile?.layer.cornerRadius = (profile?.frame.size.width)! / 2
            profile?.layer.masksToBounds = true
        }
    }
    
    @IBOutlet weak private(set) var tableView: UITableView? {
        willSet(tableView) {
        }
    }
    
    @IBOutlet weak private(set) var closeButton: UIButton? {
        willSet(button) {
            button?.addTarget(self, action: #selector(closeSelected), for: .touchUpInside)
        }
    }
    
    // MARK: - Load Functions
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter?.viewLoaded()
        self.setupTableView()
        self.tableView?.reloadData()
        //view.backgroundColor = .white
    }
    
    func setViewModels(_ viewModels:[TwitterResultViewModel]) {
        self.viewModels = viewModels
    }
    
    func setupTableView() {
        self.tableView?.dataSource = self
        self.tableView?.delegate = self
        self.tableView?.rowHeight = UITableViewAutomaticDimension
        self.tableView?.estimatedRowHeight = 140
    }
    
    @objc
    func closeSelected() {
        presenter?.closeSelected()
    }
    
    // MARK: - ListTweets Presenter to View Protocol
    
    func set(title: String?) {
        self.title = title
    }
}

extension ListTweetsViewController : UITableViewDataSource, UITableViewDelegate {
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.viewModels.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "TweetCell", for: indexPath) as? TweetCell else {
            return UITableViewCell()
        }
        return cell
    }
    
    func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        (cell as! TweetCell).setup(viewModel: self.viewModels[indexPath.row])
    }
    
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100.0
    }
}
