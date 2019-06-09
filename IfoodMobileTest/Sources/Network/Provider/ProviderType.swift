//
//  ProviderType.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

class ProviderType<Target: TargetType>: RequestProtocol {
    func requestArray<Model>(_ target: Target) -> Observable<[Model]> where Model: Codable {
        fatalError("You have called a default implementation, call you implementation")
    }
    
    func requestObject<Model>(_ target: Target) -> Observable<Model> where Model: Codable {
        fatalError("You have called a default implementation, call you implementation")
    }
    
    func requestJSON(_ target: Target) -> Observable<(response: URLResponse, data: Data)> {
        fatalError("You have called a default implementation, call you implementation")
    }
}
