//
//  FindUserViewController.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 24/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa
import Utility
import Network
import RxKeyboard

final class FindUserViewController: ViewController<FindUserView>, BindableType {

    // MARK: Var

    typealias ViewModelType = FindUserViewModel
    var viewModel: FindUserViewModel!

    // MARK: Init

    func bindViewModel() {

        let inputTextView = self.baseView.inputTextView.textField.rx.controlEvent(.editingDidEndOnExit).mapToVoid()
        let inputButton = self.baseView.inputTextView.sendButton.rx.controlEvent(.touchUpInside).mapToVoid()
        let observeInput = Observable<Void>.merge([inputTextView, inputButton]).map { _ in self.baseView.inputTextView.textField.text ?? "" }.asDriver(onErrorJustReturn: "").filter { !$0.isEmpty }
        
        let input = FindUserViewModel.Input(searchUserName: observeInput)
        let output = viewModel.transform(input: input)
        
        Driver.merge([self.rx.viewDidAppear, self.rx.viewWillDisappear, output.fetching.filter { _ in true }.map { !$0 }]).drive(self.baseView.shouldBecomeResponder).disposed(by: self.rx.disposeBag)
        
        output.fetching.drive(self.baseView.showLoading).disposed(by: self.rx.disposeBag)
        output.toUserTweetList.drive().disposed(by: self.rx.disposeBag)
    }
}
