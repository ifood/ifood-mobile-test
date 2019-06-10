//
//  TweetListView.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import UIKit

final class TweetListView: UIView {
    
    init() {
        super.init(frame: .zero)
        configureViews()
        self.backgroundColor = .red
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}

extension TweetListView: ViewConfiguration {
    func buildViewHierarchy() {}
    
    func setupConstraints() {}
}
