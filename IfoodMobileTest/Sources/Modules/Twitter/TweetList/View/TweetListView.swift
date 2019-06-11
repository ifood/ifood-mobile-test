//
//  TweetListView.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import UIKit

final class TweetListView: UITableView {
    
    convenience init() {
        self.init(frame: .zero, style: .plain)
        self.backgroundColor = .white
        self.separatorStyle = .singleLine
        self.contentInset = .zero
        self.backgroundColor = .white
        self.rowHeight = UITableView.automaticDimension
        self.register(TweetListCell.self, forCellReuseIdentifier: "TweetListCell")
    }
    
    override init(frame: CGRect, style: UITableView.Style) {
        super.init(frame: frame, style: style)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
