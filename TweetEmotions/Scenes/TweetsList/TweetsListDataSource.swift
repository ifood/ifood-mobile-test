import Foundation

final class TweetsListDataSource {
    
    // MARK: Public properties
    
    var displayTweets: () -> Void = {}
    var displayError: (Error) -> Void = { _ in }
    
    private(set) var tweets = [Tweet]()
    
    // MARK: Init/Deinit
    
    init(twitterService: TwitterServiceable) {
        self.twitterService = twitterService
    }
    
    // MARK: Public methods
    
    func loadTweets(for username: String) {
        twitterService.getTweets(for: username) { [weak self] result in
            guard let strongSelf = self else { return }
            
            switch result {
            case .success(let tweets):
                strongSelf.tweets = tweets
                strongSelf.displayTweets()
                
            case .failure(let error):
                strongSelf.tweets = []
                strongSelf.displayError(error)
            }
        }
    }
    
    // MARK: Private properties
    
    private let twitterService: TwitterServiceable
}
