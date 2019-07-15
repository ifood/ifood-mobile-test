//
//  TweetTableViewCell.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/8/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import UIKit

class TweetTableViewCell: UITableViewCell, CellIdentifiable, BindableType {
    
    internal var viewModel: UserTweetCellViewModel! {
        didSet {
            if viewModel != nil {
                bindViewModel()
            }
        }
    }
    
    static let cellSize: CGFloat = 150
    
    @IBOutlet private weak var labelName: UILabel!
    @IBOutlet private weak var imageViewProfile: UIImageView!
    @IBOutlet private weak var labelDate: UILabel!
    @IBOutlet weak var labelTweetText: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
        resetToInitialStatus()
    }
    
    func bindViewModel() {
        labelName.text = viewModel.tweetUserName
        labelTweetText.text = viewModel.tweetContent
        if let imgUserURL = viewModel.tweetUserImageURL {
            imageViewProfile.loadImageFromURL(imgUserURL, placeholder: UIImage(named: "user-placeholder"), showActivityIndicator: true)
        }
    }
    
    private func resetToInitialStatus() {
        labelName.text = "--"
        labelDate.text = ""
        labelTweetText.text = BaseLocalizable.emptyTitleDefault
        imageViewProfile.image = UIImage(named: "user-placeholder")
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        resetToInitialStatus()
    }
}
