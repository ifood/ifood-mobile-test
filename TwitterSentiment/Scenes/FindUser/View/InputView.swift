//
//  InputView.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit
import RxCocoa
import Resources
import Utility
import RxKeyboard

class InputView: View {
    
    // MARK: Var
    
    lazy var textField = TextField(with: Customization().backgroundColor(.clear).titleColor(.white).font(.systemFont(ofSize: 16)).placeholder(L10n.FindUser.textfieldPlaceholder).autocorrectionType(.no).keyboardType(.asciiCapable).returnKeyType(.search).autocapitalizationType(.none))
    lazy var sendButton = Button(with: Customization().image(Asset.iconSearch.image))
    
    var onKeyboardAnimation: Binder<CGFloat> {
        return Binder(self.rx.base, binding: { (view, height) in
            guard view.superview != nil else { return }
            view.snp.updateConstraints { (make) in
                make.bottom.equalToSuperview().inset(height)
            }
        })
    }
    
    // MARK: Init
    
    override func initSubviews() {
        self.backgroundColor = .twitterBlue
        self.addSubview(textField)
        self.addSubview(sendButton)
    
        RxKeyboard.instance.visibleHeight.drive(self.onKeyboardAnimation).disposed(by: self.rx.disposeBag)
    }

    override func initConstraints() {
        self.textField.snp.makeConstraints { (make) in
            make.centerY.equalTo(sendButton.snp.centerY)
            make.left.equalToSuperview().inset(20)
            make.right.equalToSuperview().inset(40)
        }
        self.sendButton.snp.makeConstraints { (make) in
            make.top.bottom.equalToSuperview()
            make.height.equalTo(70)
            make.right.equalToSuperview().inset(20)
        }
    }
}
