//
//  TweetCell.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import UIKit

class TweetCell: UITableViewCell {

    @IBOutlet weak var textView: UITextView!
    @IBOutlet weak var dateLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }

    func setup(viewModel: TwitterResultViewModel) {
        textView.text = viewModel.text
        dateLabel.text = viewModel.date
    }

}
