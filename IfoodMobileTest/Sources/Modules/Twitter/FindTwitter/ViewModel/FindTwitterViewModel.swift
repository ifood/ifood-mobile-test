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
}

protocol FindTwitterViewModelInput {
    var userName: BehaviorSubject<String?> { get }
    func findTweets()
}

final class FindTwitterViewModel: FindTwitterViewModelOutput, FindTwitterViewModelInput {
    
    var isValidUser: BehaviorSubject<Bool>
    var userName: BehaviorSubject<String?>
    var tweets: BehaviorSubject<[TweetModel]>
    
    private let bag = DisposeBag()
    private var service: FindTwitterService
    
    init(service: FindTwitterService = FindTwitterServiceImpl()) {
        self.service = service
        userName = BehaviorSubject<String?>(value: nil)
        isValidUser = BehaviorSubject<Bool>(value: false)
        tweets = BehaviorSubject<[TweetModel]>(value: [])
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
        
        service
            .getTweets(screenName: screenName).do(onError: {[weak self] error in
                self?.handler(error: error)
            })
            .bind(to: tweets)
            .disposed(by: bag)
    }
    
    private func handler(error: Error) {
        guard let dataError = error as? DataError else {
            print("algo deu errado")
            return
        }
        switch dataError {
        case .statusCode(let code):
            if code == 404 {
                print("Usuario não encontrado")
            } else {
                print("algo deu errado")
            }
        default:
            print("algo deu errado")
        }
    }
}
