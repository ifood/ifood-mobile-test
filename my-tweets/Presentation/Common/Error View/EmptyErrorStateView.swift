//
//  EmptyErrorStateView.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import Foundation
import UIKit
import RxSwift
import RxCocoa

class EmptyErrorStateView: UIView {
    let disposebag = DisposeBag()
    
    @IBOutlet var descriptionLabel: UILabel!
    @IBOutlet var tryAgainButton: UIButton!
    
    private var onDidTryAgainSubject: PublishSubject<Void> = PublishSubject<Void>()
    
    var onDidTryAgain: Observable<Void> { return onDidTryAgainSubject }
    
    var message = ""
    var shouldHideButton = false
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setupView()
    }
    
    override func prepareForInterfaceBuilder() {
        super.prepareForInterfaceBuilder()
        setupView()
    }
    
    private func setupView() {
        loadNibContent()
    }
    
    func configure(message: String, shouldHideButton: Bool = true, centerOffset: CGFloat = 0) {
        self.message = message
        self.shouldHideButton = shouldHideButton
        
        tryAgainButton.rx.tap.asObservable().do(onNext: { [unowned self] _ in
            self.removeFromSuperview()
        }).bind(to: onDidTryAgainSubject).disposed(by: disposebag)
        
        descriptionLabel.text = message
        tryAgainButton.isHidden = shouldHideButton
    }
}

