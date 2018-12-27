//
//  LoadingView.swift
//  Utility
//
//  Created by Jean Vinge on 27/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit
import SnapKit
import RxCocoa
import RxSwift

public protocol LoadingViewProtocol: class {
    
    var viewContainer: UIView { get set }
    var loadingView: LoadingView { get set }
    
    func showLoader()
    func hideLoader()
}

extension LoadingViewProtocol where Self: NSObject {
    
    public var showLoading: Binder<Bool> {
        return Binder(self.rx.base, binding: { (view, show) in
            _ = show ? view.showLoader() : view.hideLoader()
        })
    }
    
    public func configureLoader(with view: UIView) {
        view.addSubview(self.loadingView)
        self.loadingView.snp.makeConstraints { (make) in
            make.left.right.bottom.top.equalToSuperview()
        }
    }
    
    private func addLoader() {
        self.viewContainer.addSubview(self.loadingView)
        self.loadingView.snp.makeConstraints { (make) in
            make.left.right.bottom.top.equalToSuperview()
        }
    }
    
    public func showLoader() {
        self.addLoader()
        self.loadingView.show()
    }
    
    public func hideLoader() {
        self.loadingView.hide()
    }
}

public class LoadingView: View {
    
    // MARK: Var
    
    var activity: UIActivityIndicatorView
    lazy var blurredView = BlurredView()
    var isLoading = false
    
    private var onRemoveFromSuperView: Binder<Bool> {
        return Binder(self.rx.base, binding: { (view, isFinished) in
            if isFinished {
                view.removeFromSuperview()
            }
        })
    }
    
    // MARK: Init
    
    public init(with style: UIActivityIndicatorView.Style = .white) {
        self.activity = UIActivityIndicatorView(style: style)
        super.init()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override public func initSubviews() {
        self.addSubview(blurredView)
        self.addSubview(activity)
        
        self.activity.hidesWhenStopped = true
    }
    
    override public func initConstraints() {
        self.blurredView.snp.makeConstraints { (make) in
            make.top.left.right.bottom.equalToSuperview()
        }
        self.activity.snp.makeConstraints { (make) in
            make.centerX.equalToSuperview()
            make.centerY.equalToSuperview()
        }
    }
    
    func show() {
        self.isLoading = true
        self.activity.startAnimating()
        self.blurredView.show()
    }
    
    func hide() {
        self.isLoading = false
        self.activity.stopAnimating()
        self.blurredView.hide().bind(to: self.onRemoveFromSuperView).disposed(by: self.rx.disposeBag)
    }
}
