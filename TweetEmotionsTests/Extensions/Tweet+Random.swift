import Foundation
@testable import TweetEmotions

extension Tweet {
    //swiftlint:disable force_unwrapping
    static func createRandomTweet() -> Tweet {
        let id = Int(arc4random_uniform(UInt32(100)))
        let text = "\(id)"
        let url = URL(string: "https://www.apple.com")!
        let user = User(id: id, name: "Johnny Appleseed", screenName: "johnny_appleseed", profileImageUrl: url)
        return Tweet(id: id, text: text, user: user)
    }
    //swiftlint:enable force_unwrapping
}
