import UIKit

protocol NetworkActivityIndicatorVisibilityControlling: AnyObject {
    var isNetworkActivityIndicatorVisible: Bool { get set }
}

extension UIApplication: NetworkActivityIndicatorVisibilityControlling {}

protocol NetworkActivityIndicatorControlling {
    func incrementNetworkActivity()
    func decrementNetworkActivity()
}

final class NetworkActivityIndicatorController: NetworkActivityIndicatorControlling {
    // MARK: Init/Deinit
    
    init(visibilityController: NetworkActivityIndicatorVisibilityControlling = UIApplication.shared) {
        self.visibilityController = visibilityController
    }
    
    deinit {
        DispatchQueue.main.async {
            self.isNetworkActivityIndicatorVisible = false
        }
    }
    
    // MARK: Public methods
    
    func incrementNetworkActivity() {
        DispatchQueue.main.async {
            if !self.isNetworkActivityIndicatorVisible {
                self.isNetworkActivityIndicatorVisible = true
            }
        
            self.numberOfInflightNetworkActivities += 1
        }
    }
    
    func decrementNetworkActivity() {
        DispatchQueue.main.async {
            if self.numberOfInflightNetworkActivities > 0 {
                self.numberOfInflightNetworkActivities -= 1
            }
            
            if self.numberOfInflightNetworkActivities == 0, self.isNetworkActivityIndicatorVisible {
                self.isNetworkActivityIndicatorVisible = false
            }
        }
    }
    
    // MARK: Private properties
    
    private let visibilityController: NetworkActivityIndicatorVisibilityControlling
    private var numberOfInflightNetworkActivities = 0
    
    private var isNetworkActivityIndicatorVisible: Bool {
        get {
            return visibilityController.isNetworkActivityIndicatorVisible
        }
        
        set {
            visibilityController.isNetworkActivityIndicatorVisible = newValue
        }
    }
}
