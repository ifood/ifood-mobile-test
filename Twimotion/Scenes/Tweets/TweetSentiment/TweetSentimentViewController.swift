//
//  TweetSentimentViewController.swift
//  Twimotion
//
//  Created by Antony on 28/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

class TweetSentimentViewController: UIViewController {

    var viewModel: TweetSentimentViewModelType?
    var disposeBag = DisposeBag()

    // MARK: - Outlets

    @IBOutlet weak var closeButton: UIButton!
    @IBOutlet weak var emojiLabel: UILabel!
    @IBOutlet weak var messageLabel: UILabel!
    @IBOutlet weak var wrapperView: UIView!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    @IBOutlet weak var contentWrapper: UIStackView!
    @IBOutlet weak var wrapperCenterYConstraint: NSLayoutConstraint!
    @IBOutlet weak var errorLabel: UILabel!
    @IBOutlet weak var tweetLabel: UILabel!

    // MARK: - Initializers

    init(viewModel: TweetSentimentViewModelType) {
        super.init(nibName: "TweetSentimentViewController", bundle: nil)
        self.viewModel = viewModel
        providesPresentationContextTransitionStyle = true
        definesPresentationContext = true
        modalPresentationStyle = .overCurrentContext
        modalTransitionStyle = .crossDissolve
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

    // MARK: - Lifecycler

    override func viewDidLoad() {
        super.viewDidLoad()
        setupViews()
        bindViews()
    }

    override func loadView() {
        super.loadView()
        self.wrapperCenterYConstraint.constant = 600.0
        self.view.layoutIfNeeded()
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)

        UIView.animate(withDuration: 0.3,
                       delay: 0,
                       usingSpringWithDamping: 0.7,
                       initialSpringVelocity: 0.5,
                       options: .curveLinear,
                       animations: {
                        self.wrapperCenterYConstraint.constant = 0
                        self.view.updateConstraints()
                        self.view.layoutIfNeeded()
                    })

    }

}

// MARK: - Setups
extension TweetSentimentViewController {
    private func setupViews() {
        //setup errorLabel
        errorLabel.isHidden = true
        errorLabel.text = L10n.TweetSentiment.errorMessage

        // enable clicks at outside of popup wrapper
        view.isUserInteractionEnabled = true

        wrapperView.layer.cornerRadius = 20
    }
}

extension TweetSentimentViewController {
    private func bindViews() {

        guard let viewModel = viewModel else { fatalError("viewModel shoul not be nil") }

        /// Inputs

        // dismiss when tap outsite of popup
        let tapGesture = UITapGestureRecognizer()
        view.addGestureRecognizer(tapGesture)
        tapGesture.rx.event
            .bind { [weak self] _ in self?.viewModel?.dismissEvent.onNext(()) }
            .disposed(by: disposeBag)

        // dismiss when tap in closeButton
        closeButton.rx.tap
            .bind(to: viewModel.dismissEvent)
            .disposed(by: disposeBag)

        /// Outputs

        viewModel.tweetDescription
            .bind(to: tweetLabel.rx.text)
            .disposed(by: disposeBag)

        //isLoading sentiment
        viewModel.isLoadingSentiment
            .map { !$0 }
            .bind(to: activityIndicator.rx.isHidden)
            .disposed(by: disposeBag)

        // when could not load sentiment should show errorLabel
        viewModel.errorLoadingSentiment
            .map { !$0 }
            .bind(to: errorLabel.rx.isHidden)
            .disposed(by: disposeBag)

        // when isLoading or did occured any errors, then contentWrapper should be hide
        Observable
            .combineLatest(
                viewModel.isLoadingSentiment,
                viewModel.errorLoadingSentiment,
                resultSelector: { (isLoading: $0, errorLoading: $1)}
            )
            .flatMap { Observable<Bool>.just($0.isLoading || $0.errorLoading) }
            .bind(to: contentWrapper.rx.isHidden)
            .disposed(by: disposeBag)

        //emoji
        viewModel.sentimentEmoji
            .bind(to: emojiLabel.rx.text)
            .disposed(by: disposeBag)
        //description
        viewModel.sentimentDescription
            .bind(to: messageLabel.rx.text)
            .disposed(by: disposeBag)
        //backgroundColor
        viewModel.sentimentColor
            .observeOn(MainScheduler.asyncInstance)
            .bind { [weak self] sentimentColor in
                UIView.animate(withDuration: 0.3) {
                    self?.wrapperView.backgroundColor = sentimentColor
                }
            }
            .disposed(by: disposeBag)

        // request load sentiment
        viewModel.loadSentiment.onNext(())
    }
}
