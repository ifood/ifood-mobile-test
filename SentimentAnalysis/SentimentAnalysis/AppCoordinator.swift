//
//  AppCoordinator.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import UIKit
import RevealingSplashView

protocol Coordinator {
    func start()
}

class AppCoordinator: Coordinator {
    
    let window: UIWindow
    let tokenStore: AuthTokenStore
    let splashView = RevealingSplashView.makeSplash()
    
    let rootViewController = RootViewController()
    
    var authViewController: AuthViewController?
    
    init(window: UIWindow, tokenStore: AuthTokenStore = KeychainTokenStore()) {
        self.window = window
        self.window.rootViewController = rootViewController
        ApperanceProxyHelper.make()
        self.tokenStore = tokenStore
    }
    
    func start() {
        startSplash()
        if tokenStore.hasToken() {
            startSearch(with: tokenStore.retrieve()!)
        } else {
            startAuth()
        }
    }
    
    func startSplash() {
        window.addSubview(splashView)
        splashView.startAnimation()
    }
    
    func removeSplash() {
        DispatchQueue.main.asyncAfter(deadline: .now() + .seconds(2)) {
            self.splashView.heartAttack = true
        }
    }
    
    func startAuth() {
        let repository = AuthRepository()
        let viewModel = AuthViewModel(repository: repository)
        viewModel.delegate = self
        authViewController = AuthViewController(viewModel: viewModel)
        
        rootViewController.add(authViewController!)
    }
    
    func startSearch(with auth: Auth) {
        authViewController?.remove()
        
        let repository = SearchRepository(with: auth)
        let viewModel = SearchViewModel(repository: repository)
        viewModel.delegate = self
        let searchVC = SearchViewController(viewModel: viewModel)
        
        let navigationC = UINavigationController(rootViewController: searchVC)
        navigationC.navigationBar.prefersLargeTitles = true
        navigationC.navigationItem.largeTitleDisplayMode = .always
        navigationC.navigationBar.tintColor = .white
        
        rootViewController.add(navigationC)
        
        removeSplash()
    }
}

extension AppCoordinator: AuthViewModelDelegate {
    
    func errorOnAuthenticate() {
        removeSplash()
    }
    
    func authenticateDidComplete(_ auth: Auth) {
        tokenStore.save(auth: auth)
        startSearch(with: auth)
    }
}

extension AppCoordinator: SearchViewModelDelegate {
    
    func didStartProcessTweet(with tweet: Tweet) {
        
        let repository = SentimentRepository()
        let viewModel = SentimentViewModel(
            tweet: tweet,
            repository: repository
        )
        
        let sentimentVC = SentimentViewController(viewModel: viewModel)
        let navigationVC = UINavigationController(rootViewController: sentimentVC)
        
        rootViewController.present(navigationVC, animated: true, completion: nil)
    }
}
