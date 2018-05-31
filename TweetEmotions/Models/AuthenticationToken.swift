import Foundation

struct AuthenticationToken: Decodable {
    let accessToken: String
    let tokenType: AuthenticationTokenType
}

enum AuthenticationTokenType: String, Decodable {
    case bearer
}
