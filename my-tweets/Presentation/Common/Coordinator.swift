//
//  Coordinator.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import UIKit.UINavigationController
import RxSwift

protocol Coordinator: class {
    var children: [Coordinator] { get set }
    var navigationController: UINavigationController { get set }
    var onStopSubject: PublishSubject<Void> { get set }
    var onStop: Observable<Void> { get }
    
    func start(_ presentation: CoordinatorPresentation)
}

extension Coordinator {
    var onStop: Observable<Void> { return  onStopSubject }
}

enum CoordinatorPresentation {
    case push
    case modally
}
