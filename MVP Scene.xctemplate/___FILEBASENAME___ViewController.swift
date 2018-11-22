//
//  ___FILENAME___
//  ___PROJECTNAME___
//
//  Created by ___FULLUSERNAME___ on ___DATE___.
//  Copyright (c) ___YEAR___ ___ORGANIZATIONNAME___. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

protocol ___VARIABLE_sceneName:identifier___ViewProtocol: AnyObject, SceneView {
    //  func displaySomething(viewModel: GenericModels.ViewModel)
}

class ___VARIABLE_sceneName:identifier___ViewController: SceneViewController {
    //sourcery:begin: inject
    var presenter: ___VARIABLE_sceneName:identifier___PresenterProtocol!
    //sourcery:end
    
    //sourcery:begin: data
    //sourcery:end
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}

extension ___VARIABLE_sceneName:identifier___ViewController: ___VARIABLE_sceneName:identifier___ViewProtocol {

}
