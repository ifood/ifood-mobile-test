import Foundation

//swiftlint:disable force_unwrapping
extension URL {
    func addingQueryItems(_ queryItemsToAdd: [URLQueryItem]) -> URL {
        var components = URLComponents(url: self, resolvingAgainstBaseURL: false)!
        
        if let queryItems = components.queryItems {
            components.queryItems = queryItems + queryItemsToAdd
            
        } else {
            components.queryItems = queryItemsToAdd
        }
        
        return components.url!
    }
}
//swiftlint:enable force_unwrapping
