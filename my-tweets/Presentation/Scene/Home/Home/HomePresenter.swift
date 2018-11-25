//
//  HomePresenter.swift
//  my-tweets
//
//  Created by Gabriel Catice on 25/11/18.
//  Copyright (c) 2018 Gabriel Catice. All rights reserved.
//

import Foundation
import RxSwift

protocol HomePresenterProtocol {
    // func getSomething(request: GenericModels.Something.Request)
}

struct HomePresenter: ScenePresenter {
    var sceneView: SceneView? { return view }

    let disposeBag: DisposeBag = DisposeBag()
    weak var view: HomeViewProtocol?

    //let getSomethingUC: GetSomething
}

extension HomePresenter: HomePresenterProtocol {
    /*
     func getSomething(request: GenericModels.Something.Request) {
     someUseCase.execute()
     .toSomething()
     .bind(onNext: self.viewController.displaySomething)
     .disposed(by: disposeBag)
     }
     */
}
