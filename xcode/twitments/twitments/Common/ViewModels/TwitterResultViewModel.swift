//
//  TwitterResultViewModel.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation

public struct TwitterResultViewModel {

    fileprivate var model: TwitterResult

    init(model: TwitterResult) {
        self.model = model
    }

    var profileImageURL: String {
        guard let url = self.model.user?.profileImageUrlHttps else {
            return ""
        }
        return url
    }

    var username: String {
        guard let username = self.model.user?.screenName else {
            return ""
        }
        return username
    }

    var text: String {
        guard let text = self.model.text else {
            return ""
        }
        return text
    }

    var date: String {
        guard let date = self.model.createdAt?.formatted() else {
            return ""
        }
        return date
    }
}
