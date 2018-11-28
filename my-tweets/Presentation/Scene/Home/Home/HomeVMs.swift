//
//  HomeVMs.swift
//  my-tweets
//
//  Created by Gabriel Catice on 25/11/18.
//  Copyright (c) 2018 Gabriel Catice. All rights reserved.
//

import UIKit

struct HomeVMs {
    struct Tweet {
        let user: User
        let date: String
        let text: Text?
    }
    
    struct User {
        let name: String
        let username: String
        let userImage: String
    }
    
    struct Text {
        let text: String
        let emoji: String
        let happinesColor: UIColor
    }
}

extension User {
    func toViewModel() -> HomeVMs.User {
        return HomeVMs.User(name: name ?? "", username: screenName ?? "", userImage: profileImageUrl ?? "")
    }
}

extension Array where Element == Tweet {
    func toViewModel() -> [HomeVMs.Tweet] {
        return map {$0.toViewModel() }
    }
}


extension Tweet {
    func toViewModel() -> HomeVMs.Tweet {
        return HomeVMs.Tweet(user: user.toViewModel(), date: createdDate ?? "", text: sentence?.toViewModel())
    }
}

extension Sentence {
    func toViewModel() -> HomeVMs.Text {
        guard let score = score else {return HomeVMs.Text(text: text ?? "", emoji: "", happinesColor: .clear)}
        switch score {
        case ..<(-0.1):
            return HomeVMs.Text(text: text ?? "", emoji: "😔", happinesColor: .sadBlue)
        case -0.1..<0.1:
            return HomeVMs.Text(text: text ?? "", emoji: "😐", happinesColor: .neutralGray)
        default:
            return HomeVMs.Text(text: text ?? "", emoji: "😃", happinesColor: .briliantYellow)
        }
    }
}
