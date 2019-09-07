//
//  TwitterSentiment.swift
//

//
//  Created by Jean Vinge on 21/08/2018.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit
import RxSwift
import NSObject_Rx

public protocol CoordinatorProvider {
    func start(window: UIWindow)
}

extension CoordinatorProvider {
    public func rootViewController(in window: UIWindow, with viewController: UIViewController) {
        window.rootViewController = viewController
        window.makeKeyAndVisible()
    }
}

public protocol Router {
    func present() -> Transition
}

public protocol RouterProvider: class {
    var rootViewController: UIViewController { get set }
    func transition(with router: Router) -> Observable<Void>
}

open class ScreenRouter: NSObject, RouterProvider, Presenter {

    // MARK: Var

    public var rootViewController: UIViewController

    // MARK: Init

    public init(_ rootViewController: UIViewController) {
        self.rootViewController = rootViewController
    }

    @discardableResult
    public func transition(with router: Router) -> Observable<Void> {
        return self.makeTransition(with: self, transition: router.present())
    }
}

public enum Transition {
    case push(viewController: UIViewController)
    case present(viewController: UIViewController, animated: Bool)
    case dismiss(animated: Bool)
    case pop(animated: Bool)
}

protocol Presenter {
    func makeTransition(with coordinator: RouterProvider, transition: Transition) -> Observable<Void>
}

extension Presenter where Self: NSObject {
    @discardableResult
    func makeTransition(with coordinator: RouterProvider, transition: Transition) -> Observable<Void> {

        let subject = PublishSubject<Void>()

        switch transition {
        case .push(let vc):
            coordinator.rootViewController.navigationController?.show(vc, sender: coordinator.rootViewController)
            coordinator.rootViewController.navigationController?.rx.didShow.map { _ in }.bind(to: subject).disposed(by: self.rx.disposeBag)
        case .present(let vc, let animated):
            coordinator.rootViewController.present(vc, animated: animated) {
                subject.onCompleted()
            }
        case .dismiss(let animated):
            coordinator.rootViewController.dismiss(animated: animated) {
                subject.onCompleted()
            }
        case .pop(let animated):
            coordinator.rootViewController.navigationController?.popViewController(animated: animated)
            coordinator.rootViewController.navigationController?.rx.deallocating.map { _ in }.bind(to: subject).disposed(by: self.rx.disposeBag)
        }

        return subject.asObserver().take(1)
    }
}
