//
//  TwitterStore.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation
import UIKit
import Alamofire

protocol TwitterStore: AbstractStore {
    func getUserTweets(_ completion: @escaping completion<Bool>)
}
