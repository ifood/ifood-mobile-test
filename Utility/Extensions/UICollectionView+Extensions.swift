//
//  UICollectionView+Extensions.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 18/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import UIKit

extension UICollectionView {
    
    public func registerClass<T: UICollectionViewCell>(_ `class`: T.Type) {
        self.register(`class`, forCellWithReuseIdentifier: `class`.className)
    }
    
    public func registerClass<T: UICollectionViewCell>(_ `class`: T.Type, of kind: String) {
        self.register(`class`, forSupplementaryViewOfKind: kind, withReuseIdentifier: `class`.className)
    }
    
    private func dequeueReusableCell<T: UICollectionViewCell>(_ `class`: T.Type, at indexPath: IndexPath) throws -> T {
        guard let cell = self.dequeueReusableCell(withReuseIdentifier: `class`.className, for: indexPath) as? T else {
            throw Errors.cellCantBeNil
        }
        return cell
    }
    
    private func dequeueReusableCell<T: UICollectionViewCell>(_ `class`: T.Type, of kind: String, at indexPath: IndexPath) throws -> T {
        guard let cell = self.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: `class`.className, for: indexPath) as? T else {
            throw Errors.cellCantBeNil
        }
        return cell
    }
    
    private func dequeueReusableClass<T: UICollectionViewCell>(_ `class`: T.Type, of kind: String, at indexPath: IndexPath, object: Any? = nil) throws -> T {
        self.registerClass(`class`, of: kind)
        let cell = try dequeueReusableCell(`class`, of: kind, at: indexPath)
        cell.configure(at: indexPath, with: object)
        return cell
    }
    
    public func dequeueReusableClass<T: UICollectionViewCell>(_ `class`: T.Type, at indexPath: IndexPath, object: Any? = nil) throws -> T {
        self.registerClass(`class`)
        let cell = try self.dequeueReusableCell(`class`, at: indexPath)
        cell.configure(at: indexPath, with: object)
        return cell
    }
    
    public func dequeueReusableCell<T: UICollectionViewCell>(footer `class`: T.Type, at indexPath: IndexPath, object: Any? = nil) throws -> T {
        return try dequeueReusableClass(`class`, of: UICollectionView.elementKindSectionFooter, at: indexPath, object: object)
    }
    
    public func dequeueReusableCell<T: UICollectionViewCell>(header `class`: T.Type, at indexPath: IndexPath, object: Any? = nil) throws -> T {
        return try dequeueReusableClass(`class`, of: UICollectionView.elementKindSectionHeader, at: indexPath, object: object)
    }
    
    public func size<T: UICollectionViewCell>(cell `class`: T.Type, with object: Any? = nil) -> CGSize {
        let cell = `class`.init()
        return cell.size(object)
    }
    
    public func size<T: UICollectionViewCell>(cell `class`: T.Type, at indexPath: IndexPath, with object: Any? = nil) -> CGSize {
        let cell = `class`.init()
        return cell.size(at: indexPath, object: object)
    }
    
    public func move(to indexPath: IndexPath, at position: UICollectionView.ScrollPosition = .centeredHorizontally, animated: Bool = true) {
        self.scrollToItem(at: indexPath, at: position, animated: animated)
    }
}
