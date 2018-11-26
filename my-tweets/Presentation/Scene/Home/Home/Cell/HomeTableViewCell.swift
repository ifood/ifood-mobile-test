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

class HomeTableViewCell: UITableViewCell, DisposableHolder {
    var disposeBag: DisposeBag = DisposeBag()
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        let contentViewTap = UITapGestureRecognizer()
        contentView.addGestureRecognizer(contentViewTap)
        
        contentViewTap.rx.event.asObservable().map({ _ in ()})
            .do(onNext: { [unowned self] _ in
                self.humorView.linearFadeIn(withDuration: 3)
                self.humorView.linearFadeOut(withDuration: 1.5)
            }).subscribe().disposed(by: disposeBag)
    }
    
    @IBOutlet var userImage: UIImageView!
    @IBOutlet var nameLabel: UILabel!
    @IBOutlet var usernameLabel: UILabel!
    @IBOutlet var tweetLabel: UILabel!
    
    @IBOutlet var humorView: UIView!
    @IBOutlet var emojiHumourLabel: UILabel!
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func configure(viewModel: HomeVMs.Tweet) {
        userImage.kf.setImage(with: URL(string: viewModel.userImage))
        nameLabel.text = viewModel.name
        usernameLabel.text = viewModel.username
        tweetLabel.text = viewModel.text
        humorView.backgroundColor = viewModel.happinesColor
        emojiHumourLabel.text = viewModel.emoji
    }
}
