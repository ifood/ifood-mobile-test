//
//  TweetsViewController+TableView.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 19/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import UIKit

extension TweetsViewController : UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if self.tweets[indexPath.row].mood == nil {
            self.tweets[indexPath.row].mood =  self.viewModel.tableView(tableView, didSelectRowAt: indexPath, tweets: self.tweets)
        }
    }
}

extension TweetsViewController : UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.tweets.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        return self.viewModel.tableView(tableView, cellForRowAt: indexPath, tweets: self.tweets)
    }
}
