import XCTest
@testable import TweetEmotions

final class TweetsListDataSourceTests: XCTestCase {
    
    func testIfDisplayTweetsIsCalledWhenTweetsAreLoaded() {
        let numberOfTweetsToLoad = 15
        let twitterServiceMock = TwitterServiceMock(numberOfTweetsToLoad: numberOfTweetsToLoad)
        let dataSource = TweetsListDataSource(twitterService: twitterServiceMock)
        
        let expectation = XCTestExpectation(description: "Should call display tweets and have the correct number of tweets loaded.")
        
        dataSource.displayTweets = {
            XCTAssertEqual(dataSource.tweets.count, numberOfTweetsToLoad)
            expectation.fulfill()
        }
        
        dataSource.displayError = { _ in
            XCTFail("Should not call `displayError`")
        }
        
        dataSource.loadTweets(for: username)
        
        wait(for: [expectation], timeout: 5)
    }
    
    func testIfDisplayErrorIsCalledWhenAnErrorHappens() {
        let twitterServiceMock = TwitterServiceMock(numberOfTweetsToLoad: nil)
        let dataSource = TweetsListDataSource(twitterService: twitterServiceMock)
        
        let expectation = XCTestExpectation(description: "Should call display error and have no tweets loaded.")
        
        dataSource.displayTweets = {
            XCTFail("Should not call `displayTweets`")
        }
        
        dataSource.displayError = { _ in
            XCTAssertEqual(dataSource.tweets.count, 0)
            expectation.fulfill()
        }
        
        dataSource.loadTweets(for: username)
        
        wait(for: [expectation], timeout: 5)
    }
    
    private let username = "Johnny Appleseed"
}

private final class TwitterServiceMock: TwitterServiceable {
    init(numberOfTweetsToLoad: Int?) {
        self.numberOfTweetsToLoad = numberOfTweetsToLoad
    }
    
    func getTweets(for username: String, completion: @escaping (Result<[Tweet]>) -> Void) {
        DispatchQueue.global().asyncAfter(deadline: .now() + 1) {
            if let numberOfTweetsToLoad = self.numberOfTweetsToLoad {
                let tweets = (0..<numberOfTweetsToLoad).map { _ in self.createRandomTweet() }
                let result: Result<[Tweet]> = Result.success(tweets)
                completion(result)
                
            } else {
                let error = NetworkError.noData
                let result: Result<[Tweet]> = Result.failure(error)
                completion(result)
            }
        }
    }
    
    private func createRandomTweet() -> Tweet {
        let id = Int(arc4random_uniform(UInt32(numberOfTweetsToLoad ?? 100)))
        let text = "\(id)"
        return Tweet(id: id, text: text)
    }
    
    private let numberOfTweetsToLoad: Int?
}
