//
//  SentimentAPI.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 17/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

class SentimentAPI: Requester {

    func analyze(text: String,
                 success: @escaping CompletionWithSuccess<Sentiment>,
                 failure: @escaping CompletionWithFailure) {

        let header = HeaderHandler().generate(header: .basic)
        let url = urlComposer(using: Endpoint.Google.Analyze)

        let body = ["encodingType": "UTF8",
                    "document": [
                        "type": "PLAIN_TEXT",
                        "content": text
                    ]] as [String : Any]
        let requester = requestComposer(using: url as! (url: URL, method: String), headers: header, body: body)

        dataTask(using: requester) { data, error in

            let message = String(data: data!, encoding: String.Encoding.utf8)
            print(message)

            if error == nil, let data = data {

                let model = self.JSONDecode(to: Sentiment.self, from: data)
                guard let resultModel = model else {
                    failure(self.getParseError(data: data))
                    return
                }
                success(resultModel)
            }

        }
    }

}
