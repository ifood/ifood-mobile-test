//
//  TweetTableViewCell.swift
//  ListTweets
//
//  Created by Kaique de Souza Monteiro on 06/09/2018.
//

import UIKit

/// Celula da tabela de tweets
class TweetTableViewCell: UITableViewCell {

    @IBOutlet weak var contentTweet: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }

}
