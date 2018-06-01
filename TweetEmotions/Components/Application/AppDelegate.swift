import UIKit

@UIApplicationMain
final class AppDelegate: UIResponder, UIApplicationDelegate {
    
    // MARK: UIApplicationDelegate properties

    var window: UIWindow?
    
    // MARK: UIApplicationDelegate methods

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {
        
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = coordinator.rootViewController
        window?.makeKeyAndVisible()
        window?.backgroundColor = .white
        
        return true
    }
    
    // MARK: Private properties
    
    private let coordinator = AppCoordinator()
}
