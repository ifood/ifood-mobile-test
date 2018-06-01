import UIKit

protocol ErrorViewDelegate: AnyObject {
    func retry()
}

final class ErrorView: UIView {
    
    // MARK: Public properties
    
    weak var delegate: ErrorViewDelegate?
    
    // MARK: UIView overrides
    
    override func didMoveToSuperview() {
        addSubviews()
        addConstraints()
    }
    
    // MARK: Private properties
    
    private lazy var titleLabel: UILabel = {
        let label = UILabel()
        label.text = Localization.errorViewFailedToLoadDataMessage
        label.font = UIFont.preferredFont(forTextStyle: .body)
        label.textAlignment = .center
        label.numberOfLines = 0
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private lazy var retryButton: UIButton = {
        let button = UIButton()
        button.setTitle(Localization.errorViewRetryButtonTitle, for: .normal)
        button.setTitleColor(.blue, for: .normal)
        button.addTarget(self, action: #selector(retry), for: .touchUpInside)
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()
    
    // MARK: Private methods
    
    private func addSubviews() {
        addSubview(titleLabel)
        addSubview(retryButton)
    }
    
    private func addConstraints() {
        let spacing = SizeRatios.defaultSpacing
        
        activateConstraints([
            titleLabel.leadingAnchor.constraint(equalTo: readableContentGuide.leadingAnchor),
            titleLabel.trailingAnchor.constraint(equalTo: readableContentGuide.trailingAnchor),
            titleLabel.centerYAnchor.constraint(equalTo: centerYAnchor, constant: -spacing * 2)
        ])
        
        activateConstraints([
            retryButton.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: spacing),
            retryButton.leadingAnchor.constraint(equalTo: leadingAnchor),
            retryButton.trailingAnchor.constraint(equalTo: trailingAnchor)
        ])
    }
    
    @objc
    private func retry() {
        delegate?.retry()
    }
}

private extension ErrorView {
    private struct SizeRatios {
        static let defaultSpacingToScreenWidthRatio: CGFloat = 0.042
        
        static var defaultSpacing: CGFloat {
            return UIScreen.main.bounds.width * defaultSpacingToScreenWidthRatio
        }
        
        private init() {}
    }
}
