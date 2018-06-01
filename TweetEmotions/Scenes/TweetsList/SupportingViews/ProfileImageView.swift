import UIKit

final class ProfileImageView: UIImageView, HasLoadingState {
    
    // MARK: HasLoadingState
    
    var loadingState: LoadingState = .idle {
        didSet {
            didChangeLoadingState()
        }
    }
    
    // MARK: UIView overrides
    
    override func didMoveToSuperview() {
        addSubviews()
        addConstraints()
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        layer.cornerRadius = bounds.width / 2
        layer.masksToBounds = true
    }
    
    // MARK: Private properties
    
    private lazy var spinner: UIActivityIndicatorView = {
        let spinner = UIActivityIndicatorView(activityIndicatorStyle: .gray)
        spinner.hidesWhenStopped = true
        spinner.translatesAutoresizingMaskIntoConstraints = false
        return spinner
    }()
    
    // MARK: Private methods
    
    private func addSubviews() {
        addSubview(spinner)
    }
    
    private func addConstraints() {
        spinner.centerInSuperview()
    }
    
    private func didChangeLoadingState() {
        switch loadingState {
        case .idle:
            spinner.stopAnimating()
            
        case .isLoading:
            spinner.startAnimating()
            
        case .hasError:
            spinner.stopAnimating()
        }
    }
}
