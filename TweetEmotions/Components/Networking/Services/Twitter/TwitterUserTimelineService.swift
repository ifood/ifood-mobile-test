import Foundation

final class TwitterUserTimelineService: Service {
    // MARK: Service
    
    typealias Loadable = [Tweet]
    
    let endpoint = "1.1/statuses/user_timeline.json"
    var url: URL { return TwitterAPIEnvironment.baseUrl.appendingPathComponent(endpoint) }
    let resourceLoader: ResourceLoading
    
    // MARK: Init/Deinit
    
    init(resourceLoader: ResourceLoading) {
        self.resourceLoader = resourceLoader
    }
    
    // MARK: Public methods
    
    func getTimeline(for username: String, with authenticationToken: AuthenticationToken, completion: @escaping (Result<[Tweet]>) -> Void) {
        
        let queryItems = [
            URLQueryItem(name: "screen_name", value: username),
            URLQueryItem(name: "count", value: "20")
        ]
        
        var request = URLRequest(url: url.addingQueryItems(queryItems), cachePolicy: .useProtocolCachePolicy, timeoutInterval: 10)
        request.addValue("Bearer \(authenticationToken.accessToken)", forHTTPHeaderField: "Authorization")
        
        let tweetsResource = resource(with: request)
        resourceLoader.load(tweetsResource, completion: completion)
    }
}
