//
//  SceneView.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation
import RxSwift
import UIKit

protocol SceneView: LoadingStateDisplayer, ErrorDisplayer, DisposableHolder { }

// sourcery: skipDependencyInjection
class SceneViewController: UIViewController {
    let disposeBag = DisposeBag()
    
    var onTryAgainSubject: PublishSubject<Void> = PublishSubject<Void>()
    var onTryAgain: Observable<Void> { return onTryAgainSubject }
}

