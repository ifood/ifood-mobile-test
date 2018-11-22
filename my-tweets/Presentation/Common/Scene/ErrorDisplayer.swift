//
//  ErrorDisplayer.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import UIKit
import RxSwift
import SnapKit

protocol ErrorDisplayer {
    func displayNonBlockingGenericError(title: String?, message: String?)
    func displayNonBlockingGenericError(message: String?)
    func displayBlockingGenericError(message: String?)
    func displayNoResultsError(message: String?)
    func displayBlockingNoInternetError()
    func displayNonBlockingNoInternetError()
    
    var onTryAgain: Observable<Void> { get }
    var emptyErrorViewContainer: UIView { get }
}

extension ErrorDisplayer where Self: SceneViewController {
    var emptyErrorViewContainer: UIView {
        return view
    }
    
    func displayNonBlockingGenericError(message: String?) {
        displayNonBlockingGenericError(title: nil, message: message)
    }
    
    func displayNonBlockingGenericError(title: String?, message: String?) {
        let alert = UIAlertController(title: title ?? R.string.localizable.generic_error_title(),
                                      message: message ?? R.string.localizable.generic_error(),
                                      preferredStyle: UIAlertController.Style.alert)

        alert.addAction(UIAlertAction(title: R.string.localizable.ok(), style: UIAlertAction.Style.cancel, handler: nil))

        self.present(alert, animated: true, completion: nil)
    }
    
    func displayNonBlockingNoInternetError() {
        let alert = UIAlertController(title: R.string.localizable.generic_error_title(),
                                      message: R.string.localizable.generic_error(),
                                      preferredStyle: UIAlertController.Style.alert)

        alert.addAction(UIAlertAction(title: R.string.localizable.ok(), style: UIAlertAction.Style.cancel, handler: nil))

        self.present(alert, animated: true, completion: nil)
    }
    
    func displayBlockingGenericError(message: String?) {
        let emptyView = EmptyErrorStateView()
        emptyErrorViewContainer.addSubview(emptyView)
        emptyView.snp.makeConstraints { $0.edges.equalToSuperview() }
        emptyView.configure(message: message ?? "", shouldHideButton: false)
        emptyErrorViewContainer.bringSubviewToFront(emptyView)

        emptyView.onDidTryAgain.bind(to: onTryAgainSubject).disposed(by: emptyView.disposebag)
    }
    
    func displayBlockingNoInternetError() {
        let emptyView = EmptyErrorStateView()
        emptyErrorViewContainer.addSubview(emptyView)
        emptyView.snp.makeConstraints { $0.edges.equalToSuperview() }
        emptyView.configure(message: R.string.localizable.check_your_internet_connection(), shouldHideButton: false)
        emptyErrorViewContainer.bringSubviewToFront(emptyView)
        emptyView.onDidTryAgain.bind(to: onTryAgainSubject).disposed(by: emptyView.disposebag)
    }
    
    func displayNoResultsError(message: String?) {
        let emptyView = EmptyErrorStateView()
        emptyErrorViewContainer.addSubview(emptyView)
        emptyView.snp.makeConstraints { $0.edges.equalToSuperview() }
        emptyView.configure(message: message ?? R.string.localizable.generic_error(), shouldHideButton: true)
        emptyErrorViewContainer.bringSubviewToFront(emptyView)
        emptyView.onDidTryAgain.bind(to: onTryAgainSubject).disposed(by: emptyView.disposebag)
    }
}

