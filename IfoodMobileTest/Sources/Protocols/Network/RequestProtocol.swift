//
//  RequestProtocol.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation
import RxSwift

public protocol RequestProtocol {
    associatedtype Target: TargetType
    func requestArray<Model: Codable>(_ target: Target) -> Observable<[Model]>
    func requestObject<Model: Codable>(_ target: Target) -> Observable<Model>
    func requestJSON(_ target: Target) -> Observable<(response: URLResponse, data: Data)>
}
