//
//  Coordinator.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import Foundation
import UIKit

protocol Coordinator {
    var childCoordinators: [Coordinator] { get set }
    var rootViewController: UIViewController { get }
    
    func start()
}
