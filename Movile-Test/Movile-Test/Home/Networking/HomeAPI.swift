//
//  HomeAPI.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

class HomeAPI: Requester {

    func twitterAuthentication(success: @escaping CompletionWithSuccess<TwitterToken>,
                               failure: @escaping CompletionWithFailure) {

        let header = HeaderHandler().generate(header: .basicTwitter)
        let url = urlComposer(using: Endpoint.Twitter.Authentication)
        let requester = requestComposer(using: url as! (url: URL, method: String), headers: header)

        dataTask(using: requester) { data, error in

            let message = String(data: data!, encoding: String.Encoding.utf8)
            print(message)
            
            if error == nil, let data = data {

                let model = self.JSONDecode(to: TwitterToken.self, from: data)
                guard let resultModel = model else {
                    failure(self.getParseError(data: data))
                    return
                }
                success(resultModel)
            }
            //
        }
        
    }


    func list(token: String,
              nickname: String,
              success: @escaping CompletionWithSuccess<[Tweets]>,
              failure: @escaping CompletionWithFailure) {

        let header = HeaderHandler().generate(header: .twitter(token: token))
        let url = urlComposer(using: Endpoint.Twitter.List)
        let requester = requestComposer(using: url as! (url: URL, method: String), headers: header)

        dataTask(using: requester) { data, error in

            let message = String(data: data!, encoding: String.Encoding.utf8)
            print(message)
            
            if error == nil, let data = data {

                let model = self.JSONDecode(to: [Tweets].self, from: data)
                guard let resultModel = model else {
                    failure(self.getParseError(data: data))
                    return
                }
                success(resultModel)
            }
            //
        }
    }
}
