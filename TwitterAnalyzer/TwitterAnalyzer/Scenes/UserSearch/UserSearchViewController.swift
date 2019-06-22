//
//  UserSearchViewController.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

protocol UserSearchDisplayLogic: class {
    func displayAuthenticatTwitter(viewModel: UserSearch.AuthenticateTwitter.ViewModel)
    func displaySearchUser(viewModel: UserSearch.SearchUser.ViewModel)
    func displayError(viewModel: UserSearch.Error.ViewModel)
}

class UserSearchViewController: UIViewController, UserSearchDisplayLogic {
    var interactor: UserSearchBusinessLogic?
    var router: (NSObjectProtocol & UserSearchRoutingLogic & UserSearchDataPassing)?
    private lazy var userSearchView = UserSearchView()
    
    // MARK: Object lifecycle
    
    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
        setup()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setup()
    }
    
    override func loadView() {
        super.loadView()
        view = userSearchView
    }
    
    // MARK: Setup
    
    private func setup() {
        let viewController = self
        let interactor = UserSearchInteractor(presenter: UserSearchPresenter(viewController: self), worker: UserSearchWorker(service: TwitterAnalyzerService()))
        let router = UserSearchRouter()
        viewController.interactor = interactor
        viewController.router = router
        router.viewController = viewController
        router.dataStore = interactor
    }
    
    // MARK: View lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        title = "User Search"
        setupInteractions()
        authenticateTwitter()
    }
    
    private func setupInteractions() {
        userSearchView.userTextField.delegate = self
        userSearchView.searchButton.addTarget(self, action: #selector(searchButtonDidReceiveTouchUpInside), for: .touchUpInside)
    }
    
    @objc private func searchButtonDidReceiveTouchUpInside() {
        searchUser()
    }

    // MARK: Authenticate Twitter
    
    func authenticateTwitter() {
        LoadingView.addToView(view)
        interactor?.authenticateTwitter(request: UserSearch.AuthenticateTwitter.Request())
    }
    
    func displayAuthenticatTwitter(viewModel: UserSearch.AuthenticateTwitter.ViewModel) {
        LoadingView.removeFromView(view)
    }
    
    // MARK: Search User
    
    func searchUser() {
        if let userAccount = userSearchView.userTextField.text, userAccount != "" {
            LoadingView.addToView(view)
            let request = UserSearch.SearchUser.Request(userAccount: userAccount)
            interactor?.searchUser(request: request)
        }
    }
    
    func displaySearchUser(viewModel: UserSearch.SearchUser.ViewModel) {
        LoadingView.removeFromView(view)
        router?.routeToTweetList()
    }
    
    // MARK: Error
    
    func displayError(viewModel: UserSearch.Error.ViewModel) {
        LoadingView.removeFromView(view)
        UIAlertController.showAlert(in: self, withTitle: viewModel.errorTitle, message: viewModel.errorMessage)
    }
}

extension UserSearchViewController: UITextFieldDelegate {
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
}
