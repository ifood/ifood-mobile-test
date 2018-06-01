import Foundation

//swiftlint:disable force_unwrapping
struct TwitterAPIEnvironment {
    static let baseUrl = URL(string: "https://api.twitter.com")!
    static let key = "s5p9SCS5AkxIrhKnQURJaEtFY"
    static let secret = "LS7FLCkktahqF6v98YAQRbrJkul46jyHoLZCdF5GmwNIR601Tl"
    static var basicToken: String {
        return "\(key):\(secret)".data(using: .utf8)!.base64EncodedString()
    }
}
//swiftlint:enable force_unwrapping
