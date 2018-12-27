//
//  CollectionView.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 18/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

//
//  BaseCollectionView.swift
//  ProtocolExample
//
//  Created by Jean Vinge on 04/07/17.
//  Copyright © 2017 Worfit. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

open class DefaultLayout: UICollectionViewFlowLayout {
    public override init() {
        super.init()
        self.sectionInset = .zero
    }
    required public init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

open class CollectionView: UICollectionView, ConfigurableView {
    
    // MARK: Var
    
    public var refresh: RefreshControl
    
    // MARK: Init
    
    public convenience init(layout: UICollectionViewFlowLayout = DefaultLayout(), refreshControl: RefreshControl = RefreshControl(), scrollDirection: UICollectionView.ScrollDirection = .vertical, sectionHeadersPinToVisibleBounds: Bool = false, backgroundColor: UIColor = .clear, alwaysBounceVertical: Bool = true) {
        self.init(layout: layout)
        self.refresh = refreshControl
        layout.scrollDirection = scrollDirection
        layout.sectionHeadersPinToVisibleBounds = sectionHeadersPinToVisibleBounds
        self.backgroundColor = backgroundColor
        self.alwaysBounceVertical = alwaysBounceVertical
    }
    
    public init(layout: UICollectionViewLayout) {
        self.refresh = RefreshControl()
        super.init(frame: .zero, collectionViewLayout: layout)
        self.initLayout()
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
