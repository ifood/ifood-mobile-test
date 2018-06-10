//
//  TweetListView.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 07/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class TweetListView: UIView {
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    @IBOutlet weak var errorLabel: UILabel!
    
    func showLoadingScreen() {
        tableView.separatorStyle = .none
        activityIndicator.startAnimating()
    }
    
    func hideLoadingScreen() {
        tableView.separatorStyle = .singleLine
        activityIndicator.stopAnimating()
        activityIndicator.isHidden = true
    }
    
    func showError(_ error: String) {
        errorLabel.text = error
        tableView.isHidden = true
        activityIndicator.stopAnimating()
        activityIndicator.isHidden = true
        errorLabel.isHidden = false
    }
}
