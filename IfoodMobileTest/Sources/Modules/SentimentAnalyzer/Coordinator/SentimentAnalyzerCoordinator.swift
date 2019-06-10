//
//  SentimentAnalyzerCoordinator.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 10/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

final class SentimentAnalyzerCoordinator: BaseCoordinator<Void> {
    private var rootController: UIViewController
    private var free: PublishSubject<Void>
    private var bag = DisposeBag()
    
    init(rootController: UIViewController) {
        self.rootController = rootController
        free = PublishSubject<Void>()
    }
    
    override func start(parameters: [String: Any]) -> Observable<Void> {
        let text = (parameters["text"] as? String) ?? ""
        let controller = TweetAnalyzeController(viewModel: TweetAnalyzeViewModel(text: text))
        controller.onBack.bind(to: free).disposed(by: disposeBag)
        rootController.navigationController?.pushViewController(controller, animated: true)
        return free.do(onNext: {[weak self] _ in
            self?.rootController.navigationController?.popViewController(animated: true)
        })
    }
}
