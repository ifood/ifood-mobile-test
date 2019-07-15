//
//  HomeViewController.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

class HomeViewController: UIViewController {

    var viewModel: HomeViewModelType?
    var disposeBag = DisposeBag()
    
    
    @IBOutlet weak var usernameTextField: UITextField!
    @IBOutlet weak var listTweetsButton: UIButton!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    
    
    init(viewModel: HomeViewModelType) {
        self.viewModel = viewModel
        super.init(nibName: "HomeViewController", bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bindViews()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.isNavigationBarHidden = true
    }
}

extension HomeViewController {
    
    private func bindViews() {
        
        guard let viewModel = viewModel else { fatalError("viewModel should not be nil") }
        
        let usernameFormatted = usernameTextField.rx.text.orEmpty.changed
            .map { $0.trimmingCharacters(in: .whitespaces).replacingOccurrences(of: " ", with: "") }
            .distinctUntilChanged()
            .share()
        
        usernameFormatted.bind(to: viewModel.username).disposed(by: disposeBag)
        usernameFormatted.bind(to: usernameTextField.rx.text).disposed(by: disposeBag)
        
        viewModel.username
            .asObservable()
            .map { !$0.isEmpty }
            .bind(to: listTweetsButton.rx.isEnabled)
            .disposed(by: disposeBag)
        
        listTweetsButton.rx.tap
            .bind(to: viewModel.listTweets)
            .disposed(by: disposeBag)
        
        viewModel.couldNotLoadUser
            .bind(onNext: showCouldNotLoadUserAlert)
            .disposed(by: disposeBag)

        viewModel.isLoadingUser
            .bind(onNext: { [weak self] isLoading in
                self?.activityIndicator.isHidden = !isLoading
                self?.listTweetsButton.isHidden = isLoading
            })
            .disposed(by: disposeBag)
        
    }
    
    private func showCouldNotLoadUserAlert() {
        let alert = UIAlertController(
            title: LocalizedConstants.Home.CouldNotLoadUserAlert.title,
            message: LocalizedConstants.Home.CouldNotLoadUserAlert.message,
            preferredStyle: UIAlertController.Style.alert
        )
        alert.addAction(UIAlertAction(
            title: LocalizedConstants.Home.CouldNotLoadUserAlert.ok,
            style: .cancel,
            handler: nil
        ))
        present(alert, animated: true, completion: nil)
    }
    
    private func showLoading(isLoading: Bool) {
        print("isLoading: \(isLoading)")
    }

}
