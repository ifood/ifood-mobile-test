import XCTest
@testable import TweetEmotions

final class TweetsListDataSourceTests: XCTestCase {
    
    func testIfSuccesfullyLoadingTweetsReturnsCorrectly() {
        let numberOfTweetsToLoad = 15
        let twitterServiceMock = TwitterServiceMock(numberOfTweetsToLoad: numberOfTweetsToLoad)
        let imageLoaderMock = ImageLoaderMock(shouldLoadImage: false)
        let dataSource = TweetsListDataSource(twitterService: twitterServiceMock, imageLoader: imageLoaderMock)
        
        let expectation = XCTestExpectation(description: "Should load the correct number of tweets.")
        
        dataSource.loadTweets(for: username) { result in
            switch result {
            case .success(let tweets):
                XCTAssertEqual(tweets.count, numberOfTweetsToLoad)
                XCTAssertEqual(dataSource.tweets.count, numberOfTweetsToLoad)
                expectation.fulfill()
                
            case .failure:
                XCTFail("Loading tweets should not fail")
            }
        }
        
        wait(for: [expectation], timeout: 5)
    }
    
    func testIfFailingToLoadTweetsReturnsCorrectly() {
        let twitterServiceMock = TwitterServiceMock(numberOfTweetsToLoad: nil)
        let imageLoaderMock = ImageLoaderMock(shouldLoadImage: false)
        let dataSource = TweetsListDataSource(twitterService: twitterServiceMock, imageLoader: imageLoaderMock)
        
        let expectation = XCTestExpectation(description: "Should fail to load tweets and have zero tweets stored.")
        
        dataSource.loadTweets(for: username) { result in
            switch result {
            case .success:
                XCTFail("Loading tweets should not succeed")
                
            case .failure:
                XCTAssertEqual(dataSource.tweets.count, 0)
                expectation.fulfill()
            }
        }
        
        wait(for: [expectation], timeout: 5)
    }
    
    func testIfSuccesfullyLoadingAProfileImageReturnsCorrectly() {
        let twitterServiceMock = TwitterServiceMock(numberOfTweetsToLoad: nil)
        let imageLoaderMock = ImageLoaderMock(shouldLoadImage: true)
        let dataSource = TweetsListDataSource(twitterService: twitterServiceMock, imageLoader: imageLoaderMock)
        let tweet = Tweet.createRandomTweet()
        
        let expectation = XCTestExpectation(description: "Should load an image.")
        
        dataSource.loadProfileImage(for: tweet) { result in
            switch result {
            case .success:
                expectation.fulfill()
                
            case .failure:
                XCTFail("Loading an image should not fail")
            }
        }
    }
    
    func testIfFailingToLoadAProfileImageReturnsCorrectly() {
        let twitterServiceMock = TwitterServiceMock(numberOfTweetsToLoad: nil)
        let imageLoaderMock = ImageLoaderMock(shouldLoadImage: false)
        let dataSource = TweetsListDataSource(twitterService: twitterServiceMock, imageLoader: imageLoaderMock)
        let tweet = Tweet.createRandomTweet()
        
        let expectation = XCTestExpectation(description: "Should fail to load an image.")
        
        dataSource.loadProfileImage(for: tweet) { result in
            switch result {
            case .success:
                XCTFail("Loading an image should not succeed")
                
            case .failure:
                expectation.fulfill()
            }
        }
    }
    
    private let username = "siracusa"
}

private final class TwitterServiceMock: TwitterServiceable {
    init(numberOfTweetsToLoad: Int?) {
        self.numberOfTweetsToLoad = numberOfTweetsToLoad
    }
    
    func getTweets(for username: String, completion: @escaping (Result<[Tweet]>) -> Void) {
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

private final class ImageLoaderMock: ImageLoading {
    init(shouldLoadImage: Bool) {
        self.shouldLoadImage = shouldLoadImage
    }
    
    func loadImage(at url: URL, completion: @escaping (Result<UIImage>) -> Void) {
        if shouldLoadImage {
            let result: Result<UIImage> = Result.success(UIImage())
            completion(result)
        } else {
            let error = NetworkError.noData
            let result: Result<UIImage> = Result.failure(error)
            completion(result)
        }
    }
    
    func cancel() {}
    
    private let shouldLoadImage: Bool
}
