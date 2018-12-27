//
//  MoyaEndpoints+Extensions.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 20/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import Moya

extension Moya.Endpoint {
    public convenience init(multiTarget: MultiTarget, sampleClosure: @escaping Moya.Endpoint.SampleResponseClosure) {
        self.init(url: multiTarget.target.path, sampleResponseClosure: sampleClosure, method: multiTarget.target.method, task: multiTarget.target.task, httpHeaderFields: multiTarget.target.headers)
    }
}
