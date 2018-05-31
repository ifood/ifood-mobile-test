import Foundation
@testable import TweetEmotions

extension Tweet {
    static func createRandomTweet() -> Tweet {
        let id = Int(arc4random_uniform(UInt32(100)))
        let text = "\(id)"
        return Tweet(id: id, text: text)
    }
}
