//
//  HomeViewController.swift
//  my-tweets
//
//  Created by Gabriel Catice on 25/11/18.
//  Copyright (c) 2018 Gabriel Catice. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

protocol HomeViewProtocol: AnyObject, SceneView {
    //  func displaySomething(viewModel: GenericModels.ViewModel)
}

class HomeViewController: SceneViewController {
    //sourcery:begin: inject
    var presenter: HomePresenterProtocol!
    //sourcery:end
    
    //sourcery:begin: data
    //sourcery:end
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}

extension HomeViewController: HomeViewProtocol {

}
