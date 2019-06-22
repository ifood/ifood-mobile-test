//
//  AnalyzeTweetRequest.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 19/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

struct AnalyzeTweetRequest: RequestProtocol {
    
    let text: String
    let googleApiKey: String
    
    var endpoint: String {
        return "https://language.googleapis.com/v1/documents:analyzeSentiment?key=\(googleApiKey)"
    }
    
    var method: HttpMethod {
        return HttpMethod.POST
    }
    
    var headerFields: [String : String]? {
        return ["Content-Type": "application/json"]
    }
    
    var body: [String : Any]? {
        return ["encodingType": "UTF8",
                "document": [
                    "type": "PLAIN_TEXT",
                    "content": text
            ]]
    }
}
