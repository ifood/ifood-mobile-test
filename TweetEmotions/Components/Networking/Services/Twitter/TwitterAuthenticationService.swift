import Foundation

final class TwitterAuthenticationService: Service {
    // MARK: Service
    
    typealias Loadable = AuthenticationToken
    
    let endpoint = "oauth2/token"
    var url: URL { return TwitterAPIEnvironment.baseUrl.appendingPathComponent(endpoint) }
    let resourceLoader: ResourceLoading
    
    // MARK: Init/Deinit
    
    init(resourceLoader: ResourceLoading) {
        self.resourceLoader = resourceLoader
    }
    
    // MARK: Public methods
    
    func authenticate(completion: @escaping (Result<AuthenticationToken>) -> Void) {
        var request = URLRequest(url: url, cachePolicy: .reloadIgnoringLocalAndRemoteCacheData, timeoutInterval: 10)
        request.addValue("Basic \(TwitterAPIEnvironment.basicToken)", forHTTPHeaderField: "Authorization")
        request.addValue("application/x-www-form-urlencoded;charset=UTF-8", forHTTPHeaderField: "Content-Type")
        request.httpMethod = "POST"
        request.httpBody = "grant_type=client_credentials".data(using: .utf8)
        
        let authenticationResource = resource(with: request)
        resourceLoader.load(authenticationResource, completion: completion)
    }
}
