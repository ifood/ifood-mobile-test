//
//  GoogleCloudAPIProvider.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper

class GoogleAPIStore: AbstractAPIStore, GoogleStore {

    override init() {
        Requester.shared.alamofire.adapter = GoogleAdapter(Configurations.shared.googleToken())
    }

    func sentimentsOf(tweet: String, completion: @escaping (DocumentSentiment?, NSError?) -> Void) {

        do {
        let urlRequest = try GoogleRouter.sentimentOf(tweet: tweet).asURLRequest()
        Requester.shared.alamofire.request(urlRequest)
            .responseJSON(completionHandler: { response in
                guard let responseLoad = response.response else { return }
                let error = NSError(domain: "\(responseLoad.statusCode)", code: responseLoad.statusCode, userInfo: nil)
                switch responseLoad.statusCode {
                case 200:
                    guard let json = response.result.value as? [String: Any] else { completion(nil, self.genericError); return }
                    let sentiments = Mapper<DocumentSentiment>().map(JSON: json["documentSentiment"] as! [String: Any])
                    completion(sentiments, nil)
                default:
                    completion(nil, error)
                }
            })
            throw AbstractStoreError.FoundNil("sentimentAnalysis")
        } catch {
            return
        }
    }
}
