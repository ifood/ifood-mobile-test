//
//  SentimentViewController.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import UIKit
import Moya
import Result

class SentimentViewController: UIViewController {
    
    @IBOutlet weak var content: UILabel!
    @IBOutlet weak var result: UILabel!
    
    var provider = MoyaProvider<SentimentAPI>(plugins: [SentimentAuthPlugin(key: SentimentAPIConfig.makeApiKey())])
    
    var tweet: Tweet!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupNavigationItem()
        content.text = tweet.text
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        analyzeDocument(with: tweet.text) { [weak self] (result) in
            let emotion = result.value?.emotion
            self?.result.text = emotion?.emoji
            self?.view.backgroundColor = emotion?.color
        }
    }
    
    func setupNavigationItem() {
        navigationItem.leftBarButtonItem =
            UIBarButtonItem(
                barButtonSystemItem: .stop,
                target: self,
                action: #selector(SentimentViewController.close)
        )
    }
    
    @objc func close(){
        self.dismiss(animated: true, completion: nil)
    }
}

extension SentimentViewController {
    typealias Handler = (Result<Sentiment, AnyError>) -> Void
    
    func analyzeDocument(with content: String, then handler: @escaping Handler) {
        provider.request(.analyzeDocument(content: content)) {(result) in
            do {
                let sentiment = try result.dematerialize().map(SentimentAnalyzerResponse.self).document
                handler(.success(sentiment))
            } catch {
                handler(.failure(AnyError(error)))
            }
        }
    }
}
