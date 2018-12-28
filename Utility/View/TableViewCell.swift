//
//  TableViewCell.swift
//  Coordinator
//
//  Created by Jean Vinge on 27/11/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit

open class TableViewCell: UITableViewCell, ConfigurableView {
    
    // MARK: Init
    
    public override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        self.initLayout()
    }
    
    public required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    open func initSubviews() {
    }
    
    open func initConstraints() {
    }
}
