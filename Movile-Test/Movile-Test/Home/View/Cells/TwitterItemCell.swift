//
//  TwitterItemCell.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 17/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

class TwitterItemCell: UITableViewCell {

    @IBOutlet weak var lblText: UILabel!

    func setup(tweet: Tweet) {
        lblText.text = tweet.text
    }
    
}
