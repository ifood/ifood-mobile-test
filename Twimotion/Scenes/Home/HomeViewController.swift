//
//  HomeViewController.swift
//  Twimotion
//
//  Created by Antony on 27/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

class HomeViewController: UIViewController {

    var viewModel: HomeViewModelType?
    var disposeBag = DisposeBag()

    // MARK: - Outlets

    @IBOutlet weak var usernameTextField: UITextField!
    @IBOutlet weak var listTweetsButton: UIButton!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!

    // MARK: - Initializers

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

// MARK: - Rx
extension HomeViewController {

    private func bindViews() {

        guard let viewModel = viewModel else { fatalError("viewModel should not be nil") }

        let usernameFormatted = usernameTextField.rx.text.orEmpty.changed
            .map { $0.trimmingCharacters(in: .whitespaces).replacingOccurrences(of: " ", with: "") }
            .distinctUntilChanged()
            .share()

        usernameFormatted.bind(to: viewModel.username).disposed(by: disposeBag)
        usernameFormatted.bind(to: usernameTextField.rx.text).disposed(by: disposeBag)

        // enable ListTweets button when username is not empty
        viewModel.username
            .asObservable()
            .map { !$0.isEmpty }
            .bind(to: listTweetsButton.rx.isEnabled)
            .disposed(by: disposeBag)

        // request viewmodel to list tweets
        listTweetsButton.rx.tap
            .bind(to: viewModel.listTweets)
            .disposed(by: disposeBag)

        // showAlert couldNotloadUser
        viewModel.couldNotLoadUser
            .bind(onNext: showCouldNotLoadUserAlert)
            .disposed(by: disposeBag)

        // show/hide loading
        viewModel.isLoadingUser
            .bind(onNext: { [weak self] isLoading in
                self?.activityIndicator.isHidden = !isLoading
                self?.listTweetsButton.isHidden = isLoading
            })
            .disposed(by: disposeBag)

    }

    private func showCouldNotLoadUserAlert() {
        let alert = UIAlertController(
            title: L10n.Home.CouldNotLoadUserAlert.title,
            message: L10n.Home.CouldNotLoadUserAlert.message,
            preferredStyle: UIAlertControllerStyle.alert
        )
        alert.addAction(UIAlertAction(
            title: L10n.Home.CouldNotLoadUserAlert.ok,
            style: .cancel,
            handler: nil
        ))
        present(alert, animated: true, completion: nil)
    }

    private func showLoading(isLoading: Bool) {
        print("isLoading: \(isLoading)")
    }
}
