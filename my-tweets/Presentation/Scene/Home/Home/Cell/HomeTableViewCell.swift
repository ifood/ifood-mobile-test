//
//  HomeTableViewCell.swift
//  my-tweets
//
//  Created by Gabriel Catice on 25/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import UIKit
import Kingfisher
import RxSwift
import RxCocoa

class HomeTableViewCell: UITableViewCell {
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    @IBOutlet var userImage: UIImageView!
    @IBOutlet var nameLabel: UILabel!
    @IBOutlet var usernameLabel: UILabel!
    @IBOutlet var tweetLabel: UILabel!
    
    @IBOutlet var dateLabel: UILabel!
    @IBOutlet var humorView: UIView!
    @IBOutlet var emojiHumourLabel: UILabel!
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func configure(viewModel: HomeVMs.Tweet) {
        userImage.kf.setImage(with: URL(string: viewModel.user.userImage))
        userImage.kf.indicatorType = .activity
        userImage.contentMode = .scaleAspectFit
        nameLabel.text = viewModel.user.name
        usernameLabel.text = "@" + viewModel.user.username
        tweetLabel.text = viewModel.text?.text
        humorView.backgroundColor = viewModel.text?.happinesColor
        emojiHumourLabel.text = viewModel.text?.emoji
        dateLabel.text = viewModel.date.formattedDate
    }
}
