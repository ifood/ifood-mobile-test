//
//  ResultPresenter.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 01/12/18.
//  Copyright (c) 2018 oxltech.com. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//

import UIKit

protocol ResultPresentationLogic {
    func presentSentiment(response: Result.AnalyzeSentiment.Response)
    func presentError(response: Result.Error.Response)
}

class ResultPresenter: ResultPresentationLogic {
    
    weak var viewController: ResultDisplayLogic?

    // MARK: Do something

    func presentSentiment(response: Result.AnalyzeSentiment.Response) {
        let viewModel = Result.AnalyzeSentiment.ViewModel(score: response.analyzedSentiment.documentSentiment.score)
        viewController?.displaySentiment(viewModel: viewModel)
    }
    
    func presentError(response: Result.Error.Response) {
        let viewModel = Result.Error.ViewModel(code: response.code, message: response.message)
        viewController?.displayError(viewModel: viewModel)
    }
}
