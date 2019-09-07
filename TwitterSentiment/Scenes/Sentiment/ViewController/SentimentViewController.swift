//
//  SentimentViewController.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit
import Utility

final class SentimentViewController: ViewController<SentimentView>, BindableType {
    
    // MARK: Var
    
    typealias ViewModelType = SentimentViewModel
    var viewModel: SentimentViewModel!
    
    // MARK: Init
    
    func bindViewModel() {
        
        let input = SentimentViewModel.Input(trigger: self.rx.viewWillAppear.mapToVoid(), onDismiss: self.baseView.closeButton.rx.controlEvent(.touchUpInside).asDriver())
        let output = viewModel.transform(input: input)
        
        output.fetching.drive(self.baseView.indicator.rx.isAnimating).disposed(by: self.rx.disposeBag)
        output.onDismiss.drive().disposed(by: self.rx.disposeBag)
        
        output.showSentiment.do(onNext: self.baseView.configure).drive().disposed(by: self.rx.disposeBag)
    }
}
