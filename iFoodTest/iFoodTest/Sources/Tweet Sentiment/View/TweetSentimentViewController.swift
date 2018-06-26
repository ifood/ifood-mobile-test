//
//  TweetSentimentViewController.swift
//  iFoodTest
//
//  Created by Mauricio Cesar Maniglia Junior on 09/06/18.
//  Copyright © 2018 Mauricio Cesar Maniglia Junior. All rights reserved.
//

import UIKit

final class TweetSentimentViewController: UIViewController, TweetSentimentViewProtocol {
    
    var presenter: TweetSentimentPresenterProtocol!
    var mainView: TweetSentimentView {
        guard let mainView = self.view as? TweetSentimentView else { fatalError("ControllerWithMainViewFatalErrorMainView") }
        return mainView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.viewDidLoad()                
    }
    
    @IBAction func didSelectClose(_ sender: Any) {
        presenter.didSelectClose()
    }
    
    func showTweetSentiment(_ tweetSentiment: TweetSentiment) {
        mainView.setupTweetSentiment(tweetSentiment)
    }
    
    func displayErrorMessage(_ error: String) {
        mainView.showError(error)
    }
}
