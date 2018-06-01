import XCTest
@testable import TweetEmotions

final class TwitterServiceTests: XCTestCase {
    
    func testIfAuthenticateServiceIsCalledWhenAuthenticationTokenIsNotProvided() {
        let numberOfTweetsToLoad = 20
        let authenticationToken = AuthenticationToken(accessToken: "", tokenType: .bearer)
        let authenticationServiceMock = TwitterAuthenticationServiceMock(authenticationToken: authenticationToken)
        let userTimelineServiceMock = TwitterUserTimelineServiceMock(numberOfTweetsToLoad: numberOfTweetsToLoad)
        let authenticationTokenProviderMock = TwitterAuthenticationTokenProviderMock()
        
        let twitterService = TwitterService(
            authenticationService: authenticationServiceMock,
            userTimelineService: userTimelineServiceMock,
            tokenProvider: authenticationTokenProviderMock
        )
        
        twitterService.getTweets(for: username) { result in
            switch result {
            case .success(let tweets):
                XCTAssertEqual(tweets.count, numberOfTweetsToLoad)
                
                if let storedAuthenticationToken = authenticationTokenProviderMock.authenticationToken {
                    XCTAssertEqual(storedAuthenticationToken, authenticationToken)
                } else {
                    XCTFail("The authenticationToken has not been stored in the token provider")
                }
                
            case .failure:
                XCTFail("GetTweets should not fail")
            }
        }
    }
    
    func testIfAuthenticateServiceIsNotCalledWhenAuthenticationTokenIsProvided() {
        let numberOfTweetsToLoad = 30
        let authenticationToken = AuthenticationToken(accessToken: "", tokenType: .bearer)
        let authenticationServiceMock = TwitterAuthenticationServiceMock(authenticationToken: nil, failIfCalled: true)
        let userTimelineServiceMock = TwitterUserTimelineServiceMock(numberOfTweetsToLoad: numberOfTweetsToLoad)
        let authenticationTokenProviderMock = TwitterAuthenticationTokenProviderMock()
        authenticationTokenProviderMock.authenticationToken = authenticationToken
        
        let twitterService = TwitterService(
            authenticationService: authenticationServiceMock,
            userTimelineService: userTimelineServiceMock,
            tokenProvider: authenticationTokenProviderMock
        )
        
        twitterService.getTweets(for: username) { result in
            switch result {
            case .success(let tweets):
                XCTAssertEqual(tweets.count, numberOfTweetsToLoad)
                
                if let storedAuthenticationToken = authenticationTokenProviderMock.authenticationToken {
                    XCTAssertEqual(storedAuthenticationToken, authenticationToken)
                } else {
                    XCTFail("The authenticationToken has not been stored in the token provider")
                }
                
            case .failure:
                XCTFail("GetTweets should not fail")
            }
        }
    }
    
    private let username = "johnny_appleseed"
}

private final class TwitterAuthenticationServiceMock: TwitterAuthenticationServiceable {
    init(authenticationToken: AuthenticationToken?, failIfCalled: Bool = false) {
        self.authenticationToken = authenticationToken
        self.failIfCalled = failIfCalled
    }
    
    func authenticate(completion: @escaping (Result<AuthenticationToken>) -> Void) {
        if failIfCalled {
            XCTFail("This method should not be called")
            return
        }
        
        if let authenticationToken = authenticationToken {
            let result: Result<AuthenticationToken> = Result.success(authenticationToken)
            completion(result)
        } else {
            let error = NetworkError.noData
            let result: Result<AuthenticationToken> = Result.failure(error)
            completion(result)
        }
    }
    
    private let authenticationToken: AuthenticationToken?
    private let failIfCalled: Bool
}

private final class TwitterUserTimelineServiceMock: TwitterUserTimelineServiceable {
    
    init(numberOfTweetsToLoad: Int?) {
        self.numberOfTweetsToLoad = numberOfTweetsToLoad
    }
    
    func getTimeline(for username: String, with authenticationToken: AuthenticationToken, completion: @escaping (Result<[Tweet]>) -> Void) {
        if let numberOfTweetsToLoad = self.numberOfTweetsToLoad {
            let tweets = (0..<numberOfTweetsToLoad).map { _ in Tweet.createRandomTweet() }
            let result: Result<[Tweet]> = Result.success(tweets)
            completion(result)
            
        } else {
            let error = NetworkError.noData
            let result: Result<[Tweet]> = Result.failure(error)
            completion(result)
        }
    }
    
    private let numberOfTweetsToLoad: Int?
}

private final class TwitterAuthenticationTokenProviderMock: TwitterAuthenticationTokenProviding {
    var authenticationToken: AuthenticationToken?
}
