//
//  HomeAPI.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

class HomeAPI: Requester {

    func characterList(limit: Int = 20,
                       offset: Int,
                       success: @escaping CompletionWithSuccess<Tweets>,
                       failure: @escaping CompletionWithFailure) {

        let header = HeaderHandler().generate(header: .basic)
        let url = urlComposer(using: Endpoint.Home.list)
        let requester = requestComposer(using: url as! (url: URL, method: String), headers: header)

        dataTask(using: requester) { data, error in

            if error == nil, let data = data {

                let model = self.JSONDecode(to: Tweets.self, from: data)
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
