//
//  TwitterUseCase.swift
//  Domain
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation
import RxSwift

public protocol TwitterUseCase {
    func ensureAuthentication() -> Observable<String>
    func authenticate() -> Observable<String>
    func user(_ username: String) -> Observable<TwitterUser>
    func latestTweets(from user: TwitterUser) -> Observable<[Tweet]>
}
