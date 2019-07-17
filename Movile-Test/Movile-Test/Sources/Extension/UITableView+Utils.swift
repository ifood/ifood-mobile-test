//
//  UITableView+Utils.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 17/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

public extension UITableView {
    public func register(_ cell: UITableViewCell.Type) {
        let nib = UINib(nibName: cell.identifier, bundle: nil)
        register(nib, forCellReuseIdentifier: cell.identifier)
    }

    public func dequeueReusableCell<T: UITableViewCell>(of class: T.Type,
                                                        for indexPath: IndexPath,
                                                        configure: @escaping ((T) -> Void) = { _ in }) -> UITableViewCell {
        let cell = dequeueReusableCell(withIdentifier: T.identifier, for: indexPath)
        if let typedCell = cell as? T {
            configure(typedCell)
        }
        return cell
    }
}
