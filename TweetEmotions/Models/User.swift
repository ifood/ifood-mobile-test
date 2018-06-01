import Foundation

struct User: Decodable {
    enum CodingKeys: String, CodingKey {
        case id, name, screenName, profileImageUrl = "profileImageUrlHttps"
    }
    
    let id: Int
    let name: String
    let screenName: String
    let profileImageUrl: URL
}
