//
//  TweetListViewController.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class TweetListViewController: UIViewController, TweetListViewProtocol {
    
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
}

extension TweetListViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 10
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "TweetListTableViewCell") as! TweetListTableViewCell
        cell.setup("oxi")
        return cell
    }
}
