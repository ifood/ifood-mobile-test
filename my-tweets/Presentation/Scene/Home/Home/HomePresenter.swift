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
        var results = [Tweet]()
        getTimeline.execute(request: GetUserTimeline.Request(screenName: username.username)).subscribe(onSuccess: { (tweets) in
            self.view?.stopLoading()
            results = tweets
            if results.isEmpty {
                self.view?.displayNoResultsError(message: R.string.localizable.not_found())
            } else {
                self.view?.displayUserTimeline(viewModel: results.toViewModel())
            }
        }, onError: { error in
            self.view?.stopLoading()
            if results.isEmpty {
                self.view?.displayUserTimeline(viewModel: [])
                self.view?.displayNoResultsError(message: R.string.localizable.not_found())
            } else {
                self.view?.displayUserTimeline(viewModel: [])
                self.handleGenericError(error: error, isBlocking: true)
            }
        }).disposed(by: disposeBag)
    }
}
