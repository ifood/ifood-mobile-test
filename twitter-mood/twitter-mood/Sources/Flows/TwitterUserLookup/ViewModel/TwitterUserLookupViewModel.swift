//
//  TwitterUserLookupViewModel.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation
import RxSwift
import RxCocoa

internal protocol TwitterUserLookupViewModelOutput {
    var userNameValidated: Driver<Bool> { get }
    var isLoading: BehaviorSubject<Bool> { get }
    var errorObservable: PublishSubject<Error?> { get }
}

internal protocol TwitterUserLookupViewModelInput {
    var userNameInput: BehaviorSubject<String> { get }
    var twitterService: TwitterServiceType { get }
    var tokenService: AccessTokenService { get }
    func checkForAuthentication()
}

typealias TwitterUserLookupViewModelType = TwitterUserLookupViewModelOutput  & TwitterUserLookupViewModelInput

class TwitterUserLookupViewModel: BaseViewModel, TwitterUserLookupViewModelType {
    var tokenService: AccessTokenService
    
    var twitterService: TwitterServiceType
    
    var userNameInput: BehaviorSubject<String>
    
    var isLoading: BehaviorSubject<Bool>
    
    var errorObservable: PublishSubject<Error?>
    
    var userNameValidated: Driver<Bool>
    
    init(twitterService: TwitterServiceType, tokenService: AccessTokenService) {
        self.twitterService = twitterService
        self.tokenService = tokenService
        self.userNameInput = BehaviorSubject<String>(value: "")
        self.errorObservable = PublishSubject<Error?>()
        self.isLoading = BehaviorSubject<Bool>(value: false)
        
        userNameValidated = userNameInput
            .debounce(0.15, scheduler: MainScheduler.instance)
            .map({ (name) -> Bool in
                return InputValidator.validateName(name, minCharacters: 2).isValid
            }).asDriver(onErrorJustReturn: false)
    }
    
    func checkForAuthentication() {
        if NetworkManager.shared.twitterAuthToken() == nil {
            isLoading.onNext(true)
            twitterService.authorize()
                .subscribe(onSuccess: { [weak self] (token) in
                    self?.isLoading.onNext(false)
                    self?.handleTwitterTokenStore(token)
                 }, onError: { (error) in
                    self.isLoading.onNext(false)
                    self.errorObservable.onNext(error)
                    print("error on request twitter auth token: \(error)")
                }).disposed(by: disposeBag)
        }
    }
    
    private func handleTwitterTokenStore(_ token: TwitterTokenResponse) {
        do {
            let accessToken = AccessToken(tokenType: .other, accessToken: token.accessToken ?? "", scope: nil, refreshToken: nil)
            try tokenService.saveAccessToken(accessToken)
        } catch {
            self.errorObservable.onNext(error)
            print("error on store token: \(error.localizedDescription)")
        }
    }
}
