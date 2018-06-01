import UIKit

final class AppCoordinator {
    // MARK: Public properties
    
    var rootViewController: UIViewController {
        return UINavigationController(rootViewController: tweetsListViewController)
    }
    
    // MARK: Init/Deinit
    
    init() {
        let networkActivityIndicatorController = NetworkActivityIndicatorController()
        let networkClient = NetworkClient(networkActivityIndicatorController: networkActivityIndicatorController)
        let resourceLoader = ResourceLoader(networkClient: networkClient)
        let authenticationService = TwitterAuthenticationService(resourceLoader: resourceLoader)
        let userTimelineService = TwitterUserTimelineService(resourceLoader: resourceLoader)
        let tokenProvider = TwitterAuthenticationTokenProvider()
        let twitterService = TwitterService(authenticationService: authenticationService, userTimelineService: userTimelineService, tokenProvider: tokenProvider)
        let imageLoader = ImageLoader(networkClient: networkClient)
        let dataSource = TweetsListDataSource(twitterService: twitterService, imageLoader: imageLoader)
        
        self.tweetsListViewController = TweetsListViewController(dataSource: dataSource)
    }
    
    // MARK: Private properties
    
    private let tweetsListViewController: TweetsListViewController
}
