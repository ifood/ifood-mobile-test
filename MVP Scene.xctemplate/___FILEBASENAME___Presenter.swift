//
//  ___FILENAME___
//  ___PROJECTNAME___
//
//  Created by ___FULLUSERNAME___ on ___DATE___.
//  Copyright (c) ___YEAR___ ___ORGANIZATIONNAME___. All rights reserved.
//

import Foundation
import RxSwift

protocol ___VARIABLE_sceneName:identifier___PresenterProtocol {
    // func getSomething(request: GenericModels.Something.Request)
}

struct ___VARIABLE_sceneName:identifier___Presenter: ScenePresenter {
    var sceneView: SceneView? { return view }

    let disposeBag: DisposeBag = DisposeBag()
    weak var view: ___VARIABLE_sceneName:identifier___ViewProtocol?

    //let getSomethingUC: GetSomething
}

extension ___VARIABLE_sceneName:identifier___Presenter: ___VARIABLE_sceneName:identifier___PresenterProtocol {
    /*
     func getSomething(request: GenericModels.Something.Request) {
     someUseCase.execute()
     .toSomething()
     .bind(onNext: self.viewController.displaySomething)
     .disposed(by: disposeBag)
     }
     */
}
