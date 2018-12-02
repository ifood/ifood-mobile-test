//
//  AppCoordinator.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import UIKit
import Keychain
import RevealingSplashView

protocol AuthTokenStore {
    func save(auth: Auth)
    func retrieve() -> Auth?
    
    func hasToken() -> Bool
}

extension AuthTokenStore {
    func hasToken() -> Bool {
        return retrieve() != nil
    }
}

struct KeychainTokenStore: AuthTokenStore {
    func save(auth: Auth) {
        Keychain.set(password: auth.token, account: "twitter", service: "api")
    }
    
    func retrieve() -> Auth? {
        guard let token = Keychain.get(account: "twitter", service: "api") else {
            return nil
        }
        
        return Auth(token: token)
    }
}

protocol Coordinator {
    func start()
}

class AppCoordinator: Coordinator {
    
    let window: UIWindow
    let tokenStore: AuthTokenStore = KeychainTokenStore()
    let splashView = RevealingSplashView.makeSplash()
    
    let rootViewController = UIViewController()
    
    var authViewController: AuthViewController?
    
    init(window: UIWindow) {
        self.window = window
        self.window.rootViewController = rootViewController
    }
    
    func start() {
        startSplash()
//        if tokenStore.hasToken() {
//
//        } else {
//
//        }
    }
    
    func startSplash() {
        window.addSubview(splashView)
        splashView.startAnimation()
        
        DispatchQueue.main.asyncAfter(deadline: .now() + .seconds(1)) {
            self.startAuth()
        }
    }
    
    func removeSplash() {
        DispatchQueue.main.asyncAfter(deadline: .now() + .seconds(1)) {
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
        navigationC.navigationItem.largeTitleDisplayMode = .always
        rootViewController.add(navigationC)
        
        removeSplash()
    }
}

extension AppCoordinator: AuthViewModelDelegate {
    
    func errorOnAuthenticate() {
        removeSplash()
    }
    
    func authenticateDidComplete(_ auth: Auth) {
        startSearch(with: auth)
    }
}

extension AppCoordinator: SearchViewModelDelegate {
    
    func didStartProcessTweet(with tweet: Tweet) {
        
        let sentimentVC = UIStoryboard(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "SentimentViewController") as! SentimentViewController
        
        sentimentVC.tweet = tweet
        
        let navigationVC = UINavigationController(rootViewController: sentimentVC)
        
        rootViewController.present(navigationVC, animated: true, completion: nil)
    }
}
