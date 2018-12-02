//
//  ResultViewController.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 01/12/18.
//  Copyright (c) 2018 oxltech.com. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//
//AIzaSyDnrjzs20I2q68ufSfcgsBKrMur8Iegbmo
//https://language.googleapis.com/v1/documents:analyzeSentiment?key=AIzaSyAA2hddhiijl5Kb7HuMM8fmzLpOJTBguF4
//{
//"document": {
//    "type": "PLAIN_TEXT",
//    "content": "I'm very sad today"
//},
//"encodingType": "UTF8"
//}

import UIKit

protocol ResultDisplayLogic: class {
    func displaySentiment(viewModel: Result.AnalyzeSentiment.ViewModel)
    func displayError(viewModel: Result.Error.ViewModel)
}

class ResultViewController: UIViewController, ResultDisplayLogic {
  
    var interactor: ResultBusinessLogic?
    var router: (NSObjectProtocol & ResultRoutingLogic & ResultDataPassing)?

    // MARK: Object lifecycle

    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
        setup()
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setup()
    }

    // MARK: Setup

    private func setup() {
        let viewController = self
        let configuration = Configuration()
        let interactor = ResultInteractor(configuration: configuration)
        let presenter = ResultPresenter()
        let router = ResultRouter()
        viewController.interactor = interactor
        viewController.router = router
        interactor.presenter = presenter
        presenter.viewController = viewController
        router.viewController = viewController
        router.dataStore = interactor
    }

    // MARK: Routing

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let scene = segue.identifier {
            let selector = NSSelectorFromString("routeTo\(scene)WithSegue:")
            if let router = router, router.responds(to: selector) {
                router.perform(selector, with: segue)
            }
        }
    }

    let lottieView = LottieView(lottieName: "pulse", size: CGSize(width: 200, height: 200))
    
    // MARK: View lifecycle

    override func viewDidLoad() {
        super.viewDidLoad()
        setupLayout()
        analyzeSentiment()
    }

    // MARK: Layout
    
    func setupLayout() {
        view.backgroundColor = UIColor(white: 0, alpha: 0.4)
        self.view.addSubview(self.lottieView)
        setupTapGestureRecognizer()
    }
    
    func setupTapGestureRecognizer() {
        let tap = UITapGestureRecognizer(target: self, action: #selector(tapHandler))
        view.addGestureRecognizer(tap)
    }
    
    func createContainerView(withBackground color: UIColor) -> UIView {
        let containerView = UIView(frame: CGRect(origin: CGPoint.zero,
                                                 size: CGSize(width: view.bounds.width * 0.8, height: view.bounds.height * 0.3)))
        containerView.center = view.center
        containerView.backgroundColor = color
        containerView.layer.cornerRadius = 8.0
        containerView.translatesAutoresizingMaskIntoConstraints = false
        return containerView
    }
    
    func createEmojiView(emoji: String) -> UILabel {
        let label = UILabel()
        label.text = emoji
        label.font = UIFont(name: "AppleColorEmoji", size: 90.0)
        label.sizeToFit()
        return label
    }
    
    // MARK: Actions
    
    @objc func tapHandler(_ gesture: UITapGestureRecognizer) {
        router?.routeToParent()
    }
    
    // MARK: Handle Data

    func analyzeSentiment() {
        self.lottieView.startAnimating()
        interactor?.analyzeSentiment()
    }
    
    // MARK: Display

    func displaySentiment(viewModel: Result.AnalyzeSentiment.ViewModel) {
        self.lottieView.stopAnimating()
        let containerView = createContainerView(withBackground: viewModel.tweetSentiment.viewBackGroundColor)
        view.addSubview(containerView)
        
        let label = createEmojiView(emoji: viewModel.tweetSentiment.emojiFace.rawValue)
        label.center = containerView.center
        view.addSubview(label)
        
        containerView.center.y = view.bounds.height + containerView.bounds.height
        label.center = containerView.center
        UIView.animate(withDuration: 0.3, delay: 0, usingSpringWithDamping: 0.7, initialSpringVelocity: 1.0, options: .curveEaseOut, animations: { [unowned self] in
            containerView.center.y = self.view.center.y
            label.center = containerView.center
        })
    }
    
    func displayError(viewModel: Result.Error.ViewModel) {
        self.lottieView.stopAnimating()
        let alert = UIAlertController(title: "Ops!", message: viewModel.message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: { [weak self] (action) in
            self?.router?.routeToParent()
        }))
        self.present(alert, animated: true, completion: nil)
    }
}

