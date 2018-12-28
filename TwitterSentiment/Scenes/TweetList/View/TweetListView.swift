//
//  TweetListView.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit
import Utility

class TweetListView: View {
    
    // MARK: Var
    
    lazy var tableView = TableView()

    // MARK: Init
    
    override func initSubviews() {
        self.addSubview(tableView)
        self.tableView.setupRefreshControl()
    }
    
    override func initConstraints() {
        tableView.snp.makeConstraints { (make) in
            make.top.left.right.bottom.equalToSuperview()
        }
    }
}
