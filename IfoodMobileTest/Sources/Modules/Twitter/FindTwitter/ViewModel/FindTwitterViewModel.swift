//
//  FindTwitterViewModel.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

protocol FindTwitterViewModelOutput {
    var isValidUser: BehaviorSubject<Bool> { get }
    var tweets: BehaviorSubject<[TweetModel]> { get }
    var errorMessage: BehaviorSubject<String?> { get }
    var showLoad: BehaviorSubject<Bool> { get }
}

protocol FindTwitterViewModelInput {
    var userName: BehaviorSubject<String?> { get }
    func findTweets()
}

final class FindTwitterViewModel: FindTwitterViewModelOutput, FindTwitterViewModelInput {
    
    var isValidUser: BehaviorSubject<Bool>
    var userName: BehaviorSubject<String?>
    var tweets: BehaviorSubject<[TweetModel]>
    var errorMessage: BehaviorSubject<String?>
    var showLoad: BehaviorSubject<Bool>
    
    private let bag = DisposeBag()
    private var service: FindTwitterService
    
    init(service: FindTwitterService = FindTwitterServiceImpl()) {
        self.service = service
        userName = BehaviorSubject<String?>(value: nil)
        isValidUser = BehaviorSubject<Bool>(value: false)
        tweets = BehaviorSubject<[TweetModel]>(value: [])
        errorMessage = BehaviorSubject<String?>(value: nil)
        showLoad = BehaviorSubject<Bool>(value: false)
        validateUserName()
    }
    
    private func validateUserName() {
        userName
            .debounce(RxTimeInterval.milliseconds(300), scheduler: MainScheduler.instance)
            .subscribe(onNext: {[weak self] userName in
                if (userName?.count ?? 0) > 0 {
                    self?.isValidUser.onNext(true)
                } else {
                    self?.isValidUser.onNext(false)
                }
            }).disposed(by: bag)
    }
    
    func findTweets() {
        guard let screenName = try? userName.value() else {
            return
        }
        showLoad.onNext(true)
        service
            .getTweets(screenName: screenName)
            .subscribe(onNext: {[weak self] tweets  in
                self?.tweets.onNext(tweets)
                }, onError: {[weak self] error  in
                    self?.handler(error: error)
                }, onCompleted: {[weak self] in
                    self?.showLoad.onNext(false)
            }).disposed(by: bag)
    }
    
    private func handler(error: Error) {
        guard let dataError = error as? DataError else {
            errorMessage.onNext(L10n.DefaultText.genericError)
            return
        }
        switch dataError {
        case .statusCode(let code):
            guard code == 404 else {
                errorMessage.onNext(L10n.DefaultText.genericError)
                return
            }
            errorMessage.onNext(L10n.FindUser.userNotFound)
        case .withoutInternet:
            errorMessage.onNext(L10n.DefaultText.withoutInternet)
        default:
            errorMessage.onNext(L10n.DefaultText.genericError)
        }
    }
}
