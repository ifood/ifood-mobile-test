//
//  HomeVC.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

class HomeVC: UIViewController {

    // MARK: - Properties
    var viewModel: HomeVM?

    // MARK: - Initialization
    init(viewModel: HomeVM) {
        super.init(nibName: nil, bundle: nil)
        self.viewModel = viewModel
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

    // MARK: - View Life Cycle
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}
