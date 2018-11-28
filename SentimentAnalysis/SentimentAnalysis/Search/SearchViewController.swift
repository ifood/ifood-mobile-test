//
//  ViewController.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 27/11/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import UIKit
import Moya

class SearchViewController: UITableViewController {

    var authModel: AuthModel! {
        didSet {
            let tuple: (String) = (authModel.token)
            let accessTokenPlugin = AccessTokenPlugin { () -> String in
                return tuple
            }
            
            provider = MoyaProvider<TwitterAPI>(plugins: [accessTokenPlugin])
        }
    }
    var provider: MoyaProvider<TwitterAPI>!
    
    init(authModel: AuthModel) {
        self.authModel = authModel
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        provider.request(.search(username: "johnsundell")) { (result) in
            switch result {
            case .success(let response):
                let data = response.data
                print(String(data: data, encoding: String.Encoding.utf8) ?? "Not available")                
                break
            case .failure(let error):
                print(error)
                break
            }
        }
    }
}

