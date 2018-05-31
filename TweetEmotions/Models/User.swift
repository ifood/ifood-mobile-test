import Foundation

struct User: Decodable {
    let id: Int
    let name: String
    let screenName: String
    let profileImageUrl: URL
}
