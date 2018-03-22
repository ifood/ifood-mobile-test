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
    func userTimeline(_ screenName: String, completion: @escaping Completion<[TwitterResult]>)
    func authentication(_ completion: @escaping Completion<Bool>)
}
