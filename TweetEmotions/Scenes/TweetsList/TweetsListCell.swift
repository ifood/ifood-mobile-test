import UIKit

final class TweetsListCell: UITableViewCell, ReusableView {
    
    // MARK: Public properties
    
    var tweet: Tweet? {
        didSet {
            userNameLabel.text = tweet?.userName
            userScreenNameLabel.text = "@\(tweet?.userScreenName ?? "")"
            tweetTextLabel.text = tweet?.text
        }
    }
    
    // MARK: UIView overrides
    
    override func didMoveToSuperview() {
        addSubviews()
        addConstraints()
    }
    
    // MARK: Private properties
    
    private lazy var profileImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.translatesAutoresizingMaskIntoConstraints = false
        return imageView
    }()
    
    private lazy var userNameLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.preferredFont(forTextStyle: .headline)
        label.adjustsFontForContentSizeCategory = true
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private lazy var userScreenNameLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.preferredFont(forTextStyle: .subheadline)
        label.adjustsFontForContentSizeCategory = true
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private lazy var tweetTextLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.preferredFont(forTextStyle: .body)
        label.adjustsFontForContentSizeCategory = true
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    // MARK: Private methods
    
    private func addSubviews() {
        contentView.addSubview(profileImageView)
        contentView.addSubview(userNameLabel)
        contentView.addSubview(userScreenNameLabel)
        contentView.addSubview(tweetTextLabel)
    }
    
    private func addConstraints() {
        let spacing: CGFloat = 8
        
        activateConstraints([
            profileImageView.topAnchor.constraint(equalTo: contentView.topAnchor, constant: spacing * 2),
            profileImageView.leadingAnchor.constraint(equalTo: contentView.readableContentGuide.leadingAnchor)
        ])
        
        profileImageView.constrainSize(to: 50)
        
        activateConstraints([
            userNameLabel.topAnchor.constraint(equalTo: profileImageView.topAnchor),
            userNameLabel.leadingAnchor.constraint(equalTo: profileImageView.trailingAnchor, constant: spacing)
        ])
        
        activateConstraints([
            userScreenNameLabel.firstBaselineAnchor.constraint(equalTo: userNameLabel.firstBaselineAnchor),
            userScreenNameLabel.leadingAnchor.constraint(equalTo: userNameLabel.trailingAnchor, constant: spacing / 2),
            userScreenNameLabel.trailingAnchor.constraint(equalTo: contentView.readableContentGuide.trailingAnchor)
        ])
        
        activateConstraints([
            tweetTextLabel.topAnchor.constraint(equalTo: userNameLabel.lastBaselineAnchor, constant: spacing),
            tweetTextLabel.leadingAnchor.constraint(equalTo: userNameLabel.leadingAnchor),
            tweetTextLabel.trailingAnchor.constraint(equalTo: userScreenNameLabel.trailingAnchor),
            tweetTextLabel.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -spacing * 2)
        ])
        
        userNameLabel.setContentHuggingPriority(UILayoutPriority.defaultHigh, for: .horizontal)
        userScreenNameLabel.setContentCompressionResistancePriority(UILayoutPriority.required, for: .horizontal)
    }
}
