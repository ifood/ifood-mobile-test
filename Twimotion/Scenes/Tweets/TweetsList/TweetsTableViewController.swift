//
//  TweetsTableViewController.swift
//  Twimotion
//
//  Created by Antony on 27/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import UIKit

class TweetsTableViewController: UITableViewController {

    var viewModel: TweetsListViewModelType?

    // MARK: - Initializers

    init(viewModel: TweetsListViewModelType) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

    // MARK: - Lifecycler

    override func viewDidLoad() {
        super.viewDidLoad()
        setupViews()
        bindViews()
    }

}

// MARK: - Setups
extension TweetsTableViewController {
    private func setupViews() {
        if #available(iOS 11.0, *) {
            self.navigationController?.navigationBar.prefersLargeTitles = true
            self.navigationItem.largeTitleDisplayMode = .always
        }
        self.navigationController?.isNavigationBarHidden = false
        self.clearsSelectionOnViewWillAppear = false
    }
}

// MARK: - Rx
extension TweetsTableViewController {

    private func bindViews() {
        title = L10n.Tweets.title
    }

}
