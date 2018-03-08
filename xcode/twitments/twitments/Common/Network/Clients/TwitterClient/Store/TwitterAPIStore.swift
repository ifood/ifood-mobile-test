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
    
    fileprivate var grantType: Data {
        let grant = "grant_type=client_credentials"
        guard let data = grant.data(using: .utf8) else {
            return Data()
        }
        return data
    }
    
    fileprivate var headers: [String: String] {
        let key = Configurations.shared.twitterKey()
        let secret = Configurations.shared.twitterSecret()
        guard let data = "\(key):\(secret)".data(using: .utf8) else {
            return [:]
        }
        let encoded = data.base64EncodedString()
        return ["Authorization": "Basic \(encoded)",
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"]
    }
    
    func authentication(_ completion: @escaping (Bool, NSError?) -> Void) {
        do {
            let urlRequest = try TwitterRouter.authentication(headers: headers, grantType: grantType).asURLRequest()
            print(urlRequest)
            Requester.shared.alamofire.request(urlRequest)
                .responseJSON(completionHandler: { (response) in
                    
                    guard let responseLoad = response.response else {
                        completion(false, nil)
                        return
                    }
                    
                    let error = NSError(domain: "Erro Code:\(responseLoad.statusCode)", code: responseLoad.statusCode, userInfo: nil)
                    
                    switch responseLoad.statusCode {
                        
                    case 200:
                        guard let json = response.result.value as? [String: Any] else {
                            completion(false, self.error)
                            return
                        }
                        let result = Mapper<Authentication>().map(JSON: json)
                        AccessTokenDAO.saveToken(token: result?.accessToken)
                        completion(true, nil)
                    default:
                        completion(false, error)
                    }
                })
        } catch {}
    }
    
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
                    case 400, 403:
                        self.authentication({ (authorization, error) in
                            if authorization {
                                self.userTimeline(screenName, completion: completion)
                            }
                        })
                    default:
                        completion([], error)
                    }
                    
                })
            
            throw AbstractStoreError.FoundNil("userTimeline")
            
        } catch {}
    }
}
