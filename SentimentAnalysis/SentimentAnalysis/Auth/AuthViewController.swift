//
//  AuthViewController.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 28/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import UIKit
import Moya

//protocol AuthViewControllerDelegate: class {
//    func authViewController(didReceive accessToken: AuthModel)
//    func authViewController(didReceive error: Error)
//}

class AuthViewController: UIViewController {
    
    let provider = MoyaProvider<TwitterAPI>(
        plugins: [
            AccessTokenPlugin { TwitterAPIConfig.makeBasicAuthToken() }
        ]
    )
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        provider.request(.auth) { [weak self] (result) in
            switch result {
            case .success(let response):
                let data = response.data
                
                do {
                    let auth = try JSONDecoder().decode(AuthModel.self, from: data)
                    self?.goToSearch(with: auth)
                } catch {
                    print(error)
                }
            case .failure(let error):
                print(error)
            }
        }
    }
    
    func goToSearch(with authModel: AuthModel) {
        self.performSegue(withIdentifier: "goToSearch", sender: authModel)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "goToSearch" {
            let auth = sender as! AuthModel
            let navigationC = segue.destination as! UINavigationController
            let searchVC = navigationC.topViewController as! SearchViewController
            searchVC.authModel = auth
        }
    }
}
