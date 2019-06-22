//
//  TweetListView.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

class TweetListView: CodedView {
    
    lazy var tweetsTableView: UITableView = {
        let tableView = UITableView(frame: .zero, style: .grouped)
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.backgroundColor = .white
        tableView.estimatedRowHeight = 100
        tableView.rowHeight = UITableView.automaticDimension
        tableView.estimatedSectionHeaderHeight = 100
        tableView.sectionHeaderHeight = UITableView.automaticDimension
        tableView.estimatedSectionFooterHeight = 0
        tableView.sectionFooterHeight = 0
        return tableView
    }()
    
    override func configureView() {
        super.configureView()
        backgroundColor = .white
    }
    
    override func createSubviews() {
        addSubview(tweetsTableView)
    }
    
    override func setupConstraints() {
        NSLayoutConstraint.activate([tweetsTableView.leadingAnchor.constraint(equalTo: viewAnchor.leadingAnchor),
                                     tweetsTableView.topAnchor.constraint(equalTo: viewAnchor.topAnchor),
                                     tweetsTableView.bottomAnchor.constraint(equalTo: viewAnchor.bottomAnchor),
                                     tweetsTableView.trailingAnchor.constraint(equalTo: viewAnchor.trailingAnchor)])
    }
}
