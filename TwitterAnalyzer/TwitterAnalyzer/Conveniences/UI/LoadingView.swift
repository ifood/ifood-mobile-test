//
//  LoadingView.swift
//  TwitterAnalyzer
//
//  Created by Bruno Vieira on 18/06/19.
//  Copyright © 2019 Bruno Vieira. All rights reserved.
//

import UIKit

class LoadingView: CodedView {
    private var images: [UIImage] = []
    private var counter = 0
    private var animationTimer: Timer?
    
    private lazy var animationContainerView: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.backgroundColor = .white
        view.layer.cornerRadius = 10
        return view
    }()
    
    private lazy var loadingImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.translatesAutoresizingMaskIntoConstraints = false
        imageView.image = UIImage(named: "twitter_blue")
        return imageView
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        configureView()
        loadImages()
        startAnimation()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        configureView()
        loadImages()
        startAnimation()
    }
    
    override func createSubviews() {
        backgroundColor = UIColor.black.withAlphaComponent(0.5)
        addSubview(animationContainerView)
        animationContainerView.addSubview(loadingImageView)
    }
    
    override func setupConstraints() {
        NSLayoutConstraint.activate([animationContainerView.centerXAnchor.constraint(equalTo: centerXAnchor),
                                     animationContainerView.centerYAnchor.constraint(equalTo: centerYAnchor),
                                     animationContainerView.heightAnchor.constraint(equalToConstant: 100),
                                     animationContainerView.widthAnchor.constraint(equalToConstant: 100)])
        
        NSLayoutConstraint.activate([loadingImageView.heightAnchor.constraint(equalTo: animationContainerView.heightAnchor, constant: -40),
                                     loadingImageView.widthAnchor.constraint(equalTo: animationContainerView.widthAnchor, constant: -40),
                                     loadingImageView.centerXAnchor.constraint(equalTo: animationContainerView.centerXAnchor),
                                     loadingImageView.centerYAnchor.constraint(equalTo: animationContainerView.centerYAnchor)])
    }
    
    private func loadImages() {
        guard let yellowTwitterImage = UIImage(named: "twitter_yellow"),
            let grayTwitterImage = UIImage(named: "twitter_gray"),
            let blueTwitterImage = UIImage(named: "twitter_blue") else {
                return
        }
        images = [yellowTwitterImage, grayTwitterImage, yellowTwitterImage, blueTwitterImage]
    }
    
    // MARK: Adding/Removing Loading View
    
    static func addToView(_ view: UIView) {
        let loading = LoadingView()
        loading.translatesAutoresizingMaskIntoConstraints = false
        loading.tag = -999
        view.addSubview(loading)
        NSLayoutConstraint.activate([loading.leadingAnchor.constraint(equalTo: view.leadingAnchor),
                                     loading.trailingAnchor.constraint(equalTo: view.trailingAnchor),
                                     loading.topAnchor.constraint(equalTo: view.topAnchor),
                                     loading.bottomAnchor.constraint(equalTo: view.bottomAnchor)])

    }
    
    static func removeFromView(_ view: UIView) {
        DispatchQueue.main.async {
            if let loading = view.viewWithTag(-999) as? LoadingView {
                loading.animationTimer?.invalidate()
                loading.removeFromSuperview()
            }
        }
    }
    
    // MARK: Load Animation
    
    func startAnimation() {
        animationTimer = Timer.scheduledTimer(withTimeInterval: TimeInterval(0.3), repeats: true, block: { (_) in
            self.loadingImageView.image = self.images[self.counter]
            self.counter = (self.counter + 1) % self.images.count
        })
    }
}
