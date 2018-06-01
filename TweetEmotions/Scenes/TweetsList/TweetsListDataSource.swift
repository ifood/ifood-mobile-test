import UIKit

final class TweetsListDataSource {
    
    // MARK: Public properties
    
    private(set) var tweets = [Tweet]()
    
    // MARK: Init/Deinit
    
    init(twitterService: TwitterServiceable, imageLoader: ImageLoading) {
        self.twitterService = twitterService
        self.imageLoader = imageLoader
    }
    
    // MARK: Public methods
    
    func loadTweets(for username: String, completion: @escaping (Result<[Tweet]>) -> Void) {
        twitterService.getTweets(for: username) { [weak self] result in
            guard let strongSelf = self else { return }
            
            switch result {
            case .success(let tweets):
                strongSelf.tweets = tweets
                completion(result)
                
            case .failure:
                strongSelf.tweets = []
                completion(result)
            }
        }
    }
    
    func loadProfileImage(for tweet: Tweet, completion: @escaping (Result<UIImage>) -> Void) {
        imageLoader.loadImage(at: tweet.profileImageUrl, completion: completion)
    }
    
    // MARK: Private properties
    
    private let twitterService: TwitterServiceable
    private let imageLoader: ImageLoading
}
