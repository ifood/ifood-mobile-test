//
//  BlurredView.swift
//  ProtocolExample
//
//  Created by Jean Vinge on 10/07/17.
//  Copyright © 2017 Worfit. All rights reserved.
//

import UIKit
import RxSwift

class BlurredView: View {
    
    // MARK: Var
    
    lazy var blurLayer = UIVisualEffectView(backgroundColor: .white, alpha: 0.8)
    
    // MARK: Init
    
    override func initSubviews() {
        self.backgroundColor = .clear
        self.addSubview(blurLayer)
    }
    
    override func initConstraints() {
        self.blurLayer.snp.makeConstraints { (make) in
            make.top.left.right.bottom.equalToSuperview()
        }
    }
    
    @discardableResult
    func show() -> Observable<Bool> {
        let observable = PublishSubject<Bool>()
        self.blurLayer.isHidden = true
        self.layer.removeAllAnimations()
        UIView.animate(withDuration: 0.2, animations: { [weak self] in
            guard let self = self else { return }
            self.blurLayer.isHidden = false
            self.blurLayer.effect = UIBlurEffect(style: .light)
        }, completion: { completed in
            observable.onNext(completed)
        })
        return observable.asObserver()
    }
    
    @discardableResult
    func hide() -> Observable<Bool> {
        self.layer.removeAllAnimations()
        let observable = PublishSubject<Bool>()
        UIView.animate(withDuration: 0.3, animations: { [weak self] in
            guard let self = self else { return }
            self.blurLayer.isHidden = true
        }, completion: { isFinished in
            observable.onNext(isFinished)
        })
        return observable.asObserver()
    }
}
