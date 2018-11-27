//
//  UserController.swift
//  my-tweets
//
//  Created by Gabriel Catice on 27/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation
import RxSwift

protocol UserController {
    func getTimeline(screenName: String) -> Single<[Tweet]>
}
