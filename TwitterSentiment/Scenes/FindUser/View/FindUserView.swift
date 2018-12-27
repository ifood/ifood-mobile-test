//
//  FindUserView.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 24/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit
import Resources
import Utility
import RxCocoa

class FindUserView: View, LoadingViewProtocol {
    
    // MARK: Var
    
    lazy var logoImageView = ImageView(image: Asset.iconTwitter.image)
    lazy var inputTextView = InputView()
    lazy var titleLabel = Label(with: Customization().text(L10n.FindUser.titleLabel).font(.boldSystemFont(ofSize: 32)).titleColor(.black).numberOfLines(0))
    
    var shouldBecomeResponder: Binder<Bool> {
        return Binder(self.rx.base, binding: { (view, shouldBecomeResponder) in
            _ = shouldBecomeResponder ? view.inputTextView.textField.becomeFirstResponder() : view.inputTextView.textField.resignFirstResponder()
        })
    }
    
    // MARK: LoadingViewProtocol
    
    lazy var viewContainer: UIView = {
        return self
    }()
    lazy var loadingView: LoadingView = LoadingView()
    
    // MARK: Init
    
    override func initSubviews() {
        self.addSubview(logoImageView)
        self.addSubview(titleLabel)
        self.addSubview(inputTextView)
    }
    
    override func initConstraints() {
        self.logoImageView.snp.makeConstraints { (make) in
            make.top.equalToSuperview().inset(100)
            make.centerX.equalToSuperview()
        }
        self.titleLabel.snp.makeConstraints { (make) in
            make.top.equalTo(logoImageView.snp.bottom).offset(30)
            make.left.right.equalToSuperview().inset(30)
        }
        self.inputTextView.snp.makeConstraints { (make) in
            make.left.right.bottom.equalToSuperview()
        }
    }
}
