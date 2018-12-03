//
//  AuthViewController.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 28/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import UIKit

class AuthViewController: UIViewController, ErrorDisplayer {

    var viewModel: AuthViewModel!
    
    init(viewModel: AuthViewModel) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        registerObserver()
        viewModel.authenticate()
    }
    
    func registerObserver() {
        viewModel.authState.onUpdate = { [weak self] state in
            if case .error(let error) = state {
                self?.show(error) {
                    self?.viewModel.authenticate()
                }
            }
        }
    }
}
