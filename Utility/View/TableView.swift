//
//  TableView.swift
//  Coordinator
//
//  Created by Jean Vinge on 27/11/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit

open class TableView: UITableView, ConfigurableView {
    
    public var refresh: RefreshControl
    
    public init(_ backgroundColor: UIColor = .clear, refreshControl: RefreshControl = RefreshControl()) {
        self.refresh = refreshControl
        super.init(frame: .zero, style: .plain)
        self.backgroundColor = backgroundColor
        self.separatorStyle = .none
        self.separatorInset = .zero
        self.separatorColor = .clear
        
        self.estimatedRowHeight = 1
        self.contentInset = .zero
    }
    
    public required init?(coder aDecoder: NSCoder) {
        self.refresh = RefreshControl()
        super.init(coder: aDecoder)
        self.initLayout()
    }
    
    public func setupRefreshControl() {
        if #available(iOS 10.0, *) {
            self.refreshControl = self.refresh
        } else {
            self.addSubview(self.refresh)
        }
    }
    
    open func initSubviews() {
        self.backgroundColor = .clear
    }
    
    open func initConstraints() {
        
    }
}
