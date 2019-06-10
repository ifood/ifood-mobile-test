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
}

protocol FindTwitterViewModelInput {
    var userName: BehaviorSubject<String?> { get }
    func findTweets()
}

final class FindTwitterViewModel: FindTwitterViewModelOutput, FindTwitterViewModelInput {
    
    var isValidUser: BehaviorSubject<Bool>
    var userName: BehaviorSubject<String?>
    
    private let bag = DisposeBag()
    private var service: FindTwitterService
    
    init(service: FindTwitterService = FindTwitterServiceImpl()) {
        self.service = service
        userName = BehaviorSubject<String?>(value: nil)
        isValidUser = BehaviorSubject<Bool>(value: false)
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
        
    }
}
