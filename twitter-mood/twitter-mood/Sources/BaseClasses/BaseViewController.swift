//
//  BaseViewController.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/6/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

protocol BaseViewControllerType {
    func setViewModel(viewModel: Any)
    func setLocalizations()
}

class BaseViewController: UIViewController, BaseViewControllerType {
    
    var disposeBag: DisposeBag = DisposeBag()
    var navigationControllerPopped: PublishSubject<Void> = PublishSubject<Void>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setLocalizations()
        self.navigationController?.delegate = self
    }
    
    init(viewModel: Any, nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle? = Bundle.main) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
        setViewModel(viewModel: viewModel)
    }
    
    convenience init(viewModel: Any) {
        self.init(viewModel: viewModel, nibName: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    // MARK: Methods to be overriden
    
    /// Set the viewModel to viewController
    func setViewModel(viewModel: Any) {
        
    }
    
    /// Set localizations
    func setLocalizations() {
        
    }
}

extension BaseViewController: UINavigationControllerDelegate {
    /// To handle interactivepopgesture and back button pressing
    override func didMove(toParent parent: UIViewController?) {
        super.didMove(toParent: parent)
        if parent == nil {
            navigationControllerPopped.onNext(())
        }
    }
}

