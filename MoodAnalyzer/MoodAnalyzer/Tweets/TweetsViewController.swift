//
//  TweetsViewController.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 18/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import UIKit

class TweetsViewController: UIViewController {
    
    // MARK: - Variables
    var viewModel = TweetsViewModel()
    var tweets : [Tweet]!
    
    // MARK: - Constraints
    @IBOutlet weak var heightConstraint: NSLayoutConstraint!
    
    // MARK: - IBOutlets
    @IBOutlet weak var goBackButton: UIButton! {
        didSet {
            self.goBackButton.imageView?.tintColor = .cornflowerBlue
            self.goBackButton.imageView?.contentMode = .scaleAspectFit
            self.goBackButton.imageView?.image = UIImage(named: "goBackIcon")
        }
    }
    @IBOutlet weak var headerView: UIView! {
        didSet {
            CALayer.setDropShadowBottom(layer: self.headerView.layer, opacity: 1.0, color: .white)
        }
    }
    @IBOutlet weak var profileImageView: UIImageView!
    @IBOutlet weak var nameLabel: UILabel! {
        didSet {
            self.nameLabel.textColor = .downriverBlue
            self.nameLabel.font = FontUtils.getScaledFont(forFont: "Montserrat-Medium", textStyle: .headline)
        }
    }
    @IBOutlet weak var bioLabel: UILabel! {
        didSet {
            self.bioLabel.textColor = UIColor.charcoalGray.withAlphaComponent(0.7)
            self.bioLabel.font = UIFont.preferredFont(forTextStyle: .caption1)
            self.bioLabel.numberOfLines = 0
        }
    }
    @IBOutlet weak var tweetsTableView: UITableView! {
        didSet {
            self.tweetsTableView.delegate = self
            self.tweetsTableView.dataSource = self
            self.tweetsTableView.separatorStyle = .none
            
            self.tweetsTableView.register(UINib(nibName: String(describing: TweetTableViewCell.self), bundle: nil), forCellReuseIdentifier: String(describing: TweetTableViewCell.self))
        }
    }
    
    // MARK: - VC Functions
    init(tweets: [Tweet]) {
        super.init(nibName: String(describing: TweetsViewController.self), bundle: nil)
        
        self.tweets = tweets
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tweetsTableView.estimatedRowHeight = 50.0
        self.tweetsTableView.rowHeight = UITableView.automaticDimension
    }
    
    override func viewDidLayoutSubviews() {
        self.viewModel.setupView(nameLabel: self.nameLabel, bioLabel: self.bioLabel, profileImage: self.profileImageView,  user: self.tweets[0].user)
        
        self.setupConstraints()
    }
    
    func setupConstraints() {
        self.heightConstraint.constant = self.view.frame.width*0.3
        
        self.updateViewConstraints()
        self.view.layoutIfNeeded()
    }
    
    // MARK: - IBActions
    @IBAction func didTapGoBack(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
}
