//
//  TweetDetailViewController.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/7/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import UIKit

class TweetDetailViewController: BaseViewController, BindableType {
    
    @IBOutlet private weak var labelMood: UILabel!
    @IBOutlet private weak var viewMoodBg: UIView!
    @IBOutlet private weak var labelMoodTitle: UILabel!
    @IBOutlet private weak var textViewTweet: UITextView!
    
    internal var viewModel: TweetDetailViewModel!
    
    override func setViewModel(viewModel: Any) {
        guard let viewModel = viewModel as? TweetDetailViewModel else {
            fatalError("viewmodel of unexpected type")
        }
        self.viewModel = viewModel
    }
    
    override func setLocalizations() {
        self.title = TweetDetailLocalizable.tweetDetail
        textViewTweet.text = BaseLocalizable.emptyTitleDefault
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupStyle()
        bindViewModel()
        viewModel.loadMoodFromTweet()
    }
    
    private func setupStyle() {
        resetViewState()
        viewMoodBg.layer.cornerRadius = 5
        viewMoodBg.clipsToBounds = true
    }
    
    func bindViewModel() {
        viewModel.isLoading.asDriver(onErrorJustReturn: false).drive(onNext: { [weak self] (isLoading) in
            isLoading ? self?.view.lockView() : self?.view.unlockView()
        }).disposed(by: disposeBag)
        
        viewModel.errorObservable.asObservable()
            .filter({ $0 != nil })
            .subscribe(onNext: { [weak self] (error) in
                let okAction: AlertAction = (title: BaseLocalizable.okTitle, style:.default, handler:({ self?.dismiss(animated: true, completion: nil) }))
                self?.showRequestErrorAlert(error, actions: [okAction])
            }).disposed(by: disposeBag)
        
        viewModel.tweetMood.asObservable().subscribe(onNext: { [weak self] (tweetViewModel) in
            self?.setViewState(moodViewModel: tweetViewModel)
        }).disposed(by: disposeBag)
        
        textViewTweet.text = viewModel.tweetContent
    }
    
    private func setViewState(moodViewModel: TweetMoodViewModel?) {
        guard let moodViewModel = moodViewModel else {
            resetViewState()
            return
        }
        labelMood.text = moodViewModel.emojiForMood
        labelMoodTitle.text = TweetDetailLocalizable.tweetMood
        viewMoodBg.backgroundColor = moodViewModel.colorForMood
    }
    
    private func resetViewState() {
        labelMood.text = "🤔"
        labelMoodTitle.text = TweetDetailLocalizable.tweetMood
        viewMoodBg.backgroundColor = UIColor.clear
    }
}
