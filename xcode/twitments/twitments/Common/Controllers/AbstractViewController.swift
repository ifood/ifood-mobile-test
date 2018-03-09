//
//  AbstractViewController.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import EFInternetIndicator
import Foundation
import UIKit

class AbstractViewController: UIViewController, InternetStatusIndicable {
    
    var internetConnectionIndicator: InternetViewIndicator?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.startMonitoringInternet(backgroundColor: UIColor.darkGray, message: "Por favor, verifique sua conexão com a internet", remoteHostName: "apple.com")
        GoogleAPIStore().sentimentsOf(tweet: "I hate this API") { (sentiment, error) in
            print(sentiment)
            print(error)
        }
    }
}
