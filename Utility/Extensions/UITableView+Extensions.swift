//
//  UITableView+Extensions.swift
//  Coordinator
//
//  Created by Jean Vinge on 27/11/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit

extension UITableView {
    
    public func registerClass<T: UITableViewCell>(_ cellType: T.Type) {
        self.register(cellType, forCellReuseIdentifier: cellType.className)
    }
    
    private func dequeueReusableCell<T: UITableViewCell>(_ `class`: T.Type, at indexPath: IndexPath) throws -> T {
        guard let cell = self.dequeueReusableCell(withIdentifier: `class`.className, for: indexPath) as? T else {
            throw Errors.cellCantBeNil
        }
        cell.accessibilityIdentifier = "\(`class`.className)_\(indexPath.row)"
        return cell
    }
    
    public func dequeueReusableClass<T: UITableViewCell>(_ cellType: T.Type, at indexPath: IndexPath, object: Any? = nil) throws -> T {
        self.registerClass(cellType)
        let cell = try self.dequeueReusableCell(cellType, at: indexPath)
        cell.configure(at: indexPath, with: object)
        return cell
    }
}
