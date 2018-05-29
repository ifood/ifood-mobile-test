//
//  UITableView+Ext.swift
//  Twimotion
//
//  Created by Antony on 27/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import UIKit

extension UITableView {

    func deselectRows(_ animated: Bool = false) {
        indexPathsForSelectedRows?.forEach {
            deselectRow(at: $0, animated: animated)
        }
    }

    func registerCell(_ cellClass: UITableViewCell.Type) {
        self.register(cellClass, forCellReuseIdentifier: String(describing: cellClass))
    }

    func registerCellWithNib(_ cellClass: UITableViewCell.Type) {
        let nib = UINib(nibName: String(describing: cellClass.self), bundle: nil)
        self.register(nib, forCellReuseIdentifier: String(describing: cellClass.self))
    }

    func dequeueReusableCell<T: UITableViewCell>(type: T.Type, index: Int) -> T {
        return dequeueReusableCell(type: type, indexPath: IndexPath(row: index, section: 0))
    }

    func dequeueReusableCell<T: UITableViewCell>(type: T.Type, indexPath: IndexPath) -> T {
        return dequeueReusableCell(withIdentifier: String(describing: T.self), for: indexPath) as? T ?? T()
    }

}
