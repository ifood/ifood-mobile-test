import Foundation

protocol TwitterServiceable {
    func getTweets(for username: String, completion: @escaping(Result<[Tweet]>) -> Void)
}

final class TwitterService: TwitterServiceable {
    
    // MARK: Init/Deinit
    
    init(authenticationService: TwitterAuthenticationService, userTimelineService: TwitterUserTimelineService) {
        self.authenticationService = authenticationService
        self.userTimelineService = userTimelineService
    }
    
    // MARK: Public methods
    
    func getTweets(for username: String, completion: @escaping(Result<[Tweet]>) -> Void) {
        if let bearerToken = bearerToken {
            getTimeline(for: username, with: bearerToken, completion: completion)
        } else {
            authenticateThenGetTimeline(for: username, completion: completion)
        }
    }
    
    // MARK: Private properties
    
    private let authenticationService: TwitterAuthenticationService
    private let userTimelineService: TwitterUserTimelineService
    
    private var bearerToken: String?
    
    // MARK: Private methods
    
    private func getTimeline(for username: String, with bearerToken: String, completion: @escaping(Result<[Tweet]>) -> Void) {
        let authenticationToken = AuthenticationToken(accessToken: bearerToken, tokenType: .bearer)
        userTimelineService.getTimeline(for: username, with: authenticationToken, completion: completion)
    }
    
    private func authenticateThenGetTimeline(for username: String, completion: @escaping (Result<[Tweet]>) -> Void) {
        authenticationService.authenticate { [weak self] result in
            guard let strongSelf = self else { return }
            
            switch result {
            case .success(let authenticationToken):
                strongSelf.bearerToken = authenticationToken.accessToken
                strongSelf.getTimeline(for: username, with: authenticationToken.accessToken, completion: completion)
                
            case .failure(let error):
                let result: Result<[Tweet]> = Result.failure(error)
                completion(result)
            }
        }
    }
}
