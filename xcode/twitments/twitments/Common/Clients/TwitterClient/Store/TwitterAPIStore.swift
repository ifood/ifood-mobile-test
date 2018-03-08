//
//  TwitterAPIStore.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation
import ObjectMapper

class TwitterAPIStore: AbstractAPIStore, TwitterStore {
    
    func userTimeline(_ screenName: String, completion: @escaping ([TwitterResult], NSError?) -> Void) {
        
        do {
            
            let urlRequest = try TwitterRouter.userTimeline(screenName: screenName).asURLRequest()
            Requester.shared.alamofire.request(urlRequest)
                
                .responseJSON(completionHandler: { response in
                    
                    guard let responseLoad = response.response else { return }
                    
                    let error = NSError(domain: "\(responseLoad.statusCode)", code: responseLoad.statusCode, userInfo: nil)
                    
                    switch responseLoad.statusCode {
                        
                    case 200:
                        guard let json = response.result.value as? [[String: Any]] else { completion([], self.error); return }
                        let twittes = Mapper<TwitterResult>().mapArray(JSONArray: json)
                        completion(twittes, nil)
                    default:
                        completion([], error)
                    }
                    
                })
            
            throw AbstractStoreError.FoundNil("userTimeline")
            
        } catch {}
    }
}
