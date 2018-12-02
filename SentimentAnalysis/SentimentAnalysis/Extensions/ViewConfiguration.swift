//
//  ViewCode.swift
//  tecnonutri
//
//  Created by Thales Frigo on 09/04/18.
//  Copyright © 2018 Grupo Minha Vida. All rights reserved.
//

import UIKit

protocol ViewConfiguration {
    func buildViewHierarchy()
    func setupConstraints()
    func configureViews()
}

extension ViewConfiguration {
    
    func setupViewConfiguration(){
        buildViewHierarchy()
        setupConstraints()
        configureViews()
    }
}

class ViewConfigurationController<View: UIView & ViewConfiguration>: UIViewController {
    
    internal var contentView: View {
        return view as! View
    }
    
    public init() {
        super.init(nibName: nil, bundle: nil)
    }
    
    public required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    public override func loadView() {
        view = View()
    }
}
