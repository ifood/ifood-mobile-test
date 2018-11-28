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
    func getUserTimeline(username: String)
}

struct HomePresenter: ScenePresenter {
    var sceneView: SceneView? { return view }

    let disposeBag: DisposeBag = DisposeBag()
    weak var view: HomeViewProtocol?

    let getTimeline: GetUserTimeline
}

extension HomePresenter: HomePresenterProtocol {
    func getUserTimeline(username: String) {
        self.view?.startLoading()
        getTimeline.execute(request: GetUserTimeline.Request(screenName: username)).subscribe(onSuccess: { (tweets) in
            self.view?.stopLoading()
            self.view?.displayUserTimeline(viewModel: tweets.toViewModel())
        }, onError: { error in
            self.view?.stopLoading()
            self.handleGenericError(error: error, isBlocking: true)
        }).disposed(by: disposeBag)
    }
}
