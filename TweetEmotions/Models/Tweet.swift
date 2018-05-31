import Foundation

struct Tweet: Decodable {
    let id: Int
    let text: String
    let user: User
    
    var userName: String {
        return user.name
    }
    
    var userScreenName: String {
        return user.screenName
    }
}
