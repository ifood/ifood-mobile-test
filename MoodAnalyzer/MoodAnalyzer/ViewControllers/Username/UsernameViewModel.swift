//
//  UsernameViewModel.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 19/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import UIKit

class UsernameViewModel : NSObject {
    
    
    func didTapAnalyzeButton(textField: UITextField, navigationController: UINavigationController) {
        if let text = textField.text, !text.isEmpty {
            self.getTweets(username: text, count: 200) { (response, error) in
                if let tweets = response, tweets.count > 0 {
                    DispatchQueue.main.async {
                        let vc = TweetsViewController(tweets: tweets)
                        navigationController.present(vc, animated: true, completion: nil)
                    }
                } else {
                    // Show alert
                }
            }
        } else {
            // Show alert
        }
    }
    
    func getTweets(username: String, count: Int, completion: @escaping (_ tweets: [Tweet]?, _ error: Error?)->()) {
        
        let params : [String : String] = ["screen_name": username,
                      "count": "\(count)"]
        
        APIManager.getFromTwitterAPI(service: Constants.kTwitterGetTimeline, params: params, headers: APIManager.getDefaultHeaders()) { (data,response, error) in
            if let _ = response, let data = data {
                
                let responseJSON = try? JSONSerialization.jsonObject(with: data, options: [])
                if let _ = responseJSON as? [[String: Any]] {
                    let tweets = try? JSONDecoder().decode([Tweet].self, from: data)
                    completion(tweets, nil)
                }
                
            } else {
                completion(nil, error)
            }
        }
    }
    
}

