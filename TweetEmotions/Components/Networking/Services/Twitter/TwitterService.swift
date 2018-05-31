import Foundation

protocol TwitterServiceable {
    func getTweets(for username: String, completion: @escaping(Result<[Tweet]>) -> Void)
}

final class TwitterService: TwitterServiceable {
    
    // MARK: Init/Deinit
    
    init(authenticationService: TwitterAuthenticationServiceable, userTimelineService: TwitterUserTimelineServiceable, tokenProvider: TwitterAuthenticationTokenProviding) {
        self.authenticationService = authenticationService
        self.userTimelineService = userTimelineService
        self.tokenProvider = tokenProvider
    }
    
    // MARK: Public methods
    
    func getTweets(for username: String, completion: @escaping(Result<[Tweet]>) -> Void) {
        if let authenticationToken = tokenProvider.authenticationToken {
            getTimeline(for: username, with: authenticationToken, completion: completion)
        } else {
            authenticateThenGetTimeline(for: username, completion: completion)
        }
    }
    
    // MARK: Private properties
    
    private let authenticationService: TwitterAuthenticationServiceable
    private let userTimelineService: TwitterUserTimelineServiceable
    private let tokenProvider: TwitterAuthenticationTokenProviding
    
    // MARK: Private methods
    
    private func getTimeline(for username: String, with authenticationToken: AuthenticationToken, completion: @escaping(Result<[Tweet]>) -> Void) {
        userTimelineService.getTimeline(for: username, with: authenticationToken, completion: completion)
    }
    
    private func authenticateThenGetTimeline(for username: String, completion: @escaping (Result<[Tweet]>) -> Void) {
        authenticationService.authenticate { [weak self] result in
            guard let strongSelf = self else { return }
            
            switch result {
            case .success(let authenticationToken):
                strongSelf.tokenProvider.authenticationToken = authenticationToken
                strongSelf.getTimeline(for: username, with: authenticationToken, completion: completion)
                
            case .failure(let error):
                let result: Result<[Tweet]> = Result.failure(error)
                completion(result)
            }
        }
    }
}
