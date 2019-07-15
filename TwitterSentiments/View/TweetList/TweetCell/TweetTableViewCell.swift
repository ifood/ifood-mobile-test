//
//  TweetTableViewCell.swift
//  TwitterSentiments
//
//  Created by Marina Azevedo on 14/07/19.
//  Copyright © 2019 Marina Azevedo. All rights reserved.
//

import UIKit
import PINRemoteImage

class TweetCell: UITableViewCell {
    
    var viewModel: TweetItemViewModel! {
        didSet {
            bindUI()
        }
    }
    
    @IBOutlet weak var thumbImageView: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var usernameLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var contentLabel: UILabel!
    
    // MARK: - Initialize
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        selectionStyle = UITableViewCell.SelectionStyle.none
        
        // setup Thumb
        thumbImageView.layer.masksToBounds = false
        thumbImageView.layer.borderColor = UIColor.white.cgColor
        thumbImageView.layer.cornerRadius = thumbImageView.bounds.width / 2
        thumbImageView.clipsToBounds = true
        thumbImageView.backgroundColor = UIColor.gray
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        thumbImageView.pin_cancelImageDownload()
    }
    
    private func bindUI() {
        thumbImageView.pin_setImage(from: viewModel.profileImageUrl)
        nameLabel.text = viewModel.name
        usernameLabel.text = viewModel.username
        contentLabel.text = viewModel.text
        dateLabel.text = viewModel.formattedDate
    }
    
}
