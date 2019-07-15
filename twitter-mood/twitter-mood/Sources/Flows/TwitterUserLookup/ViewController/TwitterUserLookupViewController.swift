//
//  TwitterUserLookupViewController.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import UIKit
import RxCocoa
import RxSwift

class TwitterUserLookupViewController: BaseViewController, BindableType {

    @IBOutlet private weak var labelTwitterUserTitle: UILabel!
    @IBOutlet private weak var textFieldInput: UITextField!
    @IBOutlet private weak var buttonSeeTweets: UIButton!
    
    internal var viewModel: TwitterUserLookupViewModel!
    
    private(set) var seeTweetsAction = PublishSubject<Void>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bindViewModel()
    }
    
    override func setViewModel(viewModel: Any) {
        guard let viewModel = viewModel as? TwitterUserLookupViewModel else {
            fatalError("viewmodel of unexpected type")
        }
        self.viewModel = viewModel
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        viewModel.checkForAuthentication()
    }
    
    override func setLocalizations() {
        self.title = TwitterUserLookupLocalizable.twitterUser
        labelTwitterUserTitle.text = TwitterUserLookupLocalizable.twitterUser
        buttonSeeTweets.setTitle(TwitterUserLookupLocalizable.seeTweets, for: .normal)
        textFieldInput.placeholder = TwitterUserLookupLocalizable.twitterUserName
    }
    
    func bindViewModel() {
        textFieldInput.rx.text
            .orEmpty
            .map({ $0.trimmingCharacters(in: .whitespaces) })
            .bind(to: viewModel.userNameInput)
            .disposed(by: disposeBag)
        
        viewModel.isLoading.asDriver(onErrorJustReturn: false).drive(onNext: { [weak self] (isLoading) in
            isLoading ? self?.view.lockView() : self?.view.unlockView()
        }).disposed(by: disposeBag)
        
        viewModel.userNameValidated.asObservable().subscribe(onNext: { [weak self] (isEnabled) in
            self?.buttonSeeTweets.isEnabled = isEnabled
        }).disposed(by: disposeBag)
        
        viewModel.errorObservable.asObservable()
            .filter({ $0 != nil })
            .subscribe(onNext: { [weak self] (error) in
                let okAction: AlertAction = (title: BaseLocalizable.okTitle, style:.default, handler:({ self?.dismiss(animated: true, completion: nil) }))
                self?.showRequestErrorAlert(error, actions: [okAction])
            }).disposed(by: disposeBag)
        
        buttonSeeTweets.rx.tap.bind(to: seeTweetsAction).disposed(by: disposeBag)
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }

}
