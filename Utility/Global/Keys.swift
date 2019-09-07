//
//  Enviroment.swift
//  Utility
//
//  Created by Jean Vinge on 29/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit

public struct TwitterKeys {
    private var clientKey: String { return "mrbecy5yseOOyu2yjp6SIb4po" }
    private var clientSecret: String { return "tU2y3LdBXOnSShuwOPedrLzAVkpr5b7DTNarZSggOeGRX6Ncjb" }
    
    public static func encoded() -> String {
        return "\(TwitterKeys().clientKey):\(TwitterKeys().clientSecret)".data(using: .utf8)?.base64EncodedString() ?? ""
    }
}

public struct GoogleAPIKey {
    public static var apiKey: String { return "AIzaSyDUQA28O_fRnLj8QLTKAAriJX-bprlq8wM" }
}
