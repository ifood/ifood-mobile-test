//
//  LoadingStateDisplayer.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import UIKit

protocol LoadingStateDisplayer {
    func startLoading()
    func stopLoading()
}

extension LoadingStateDisplayer where Self: UIViewController {
    func startLoading() {
        if let _ = view.viewWithTag(1234) { return }
        
        let loadingView = UIView()
        
        loadingView.backgroundColor = UIColor.black
        loadingView.alpha = 0.3
        
        let activityIndicator = UIActivityIndicatorView(style: .white)
        
        loadingView.isHidden = true
        
        view.addSubview(loadingView)
        loadingView.addSubview(activityIndicator)
        
        loadingView.translatesAutoresizingMaskIntoConstraints = false
        activityIndicator.translatesAutoresizingMaskIntoConstraints = false
        
        loadingView.translatesAutoresizingMaskIntoConstraints = false
        loadingView.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        loadingView.leftAnchor.constraint(equalTo: view.leftAnchor).isActive = true
        loadingView.rightAnchor.constraint(equalTo: view.rightAnchor).isActive = true
        loadingView.bottomAnchor.constraint(equalTo: view.bottomAnchor).isActive = true
        
        activityIndicator.centerXAnchor.constraint(equalTo: loadingView.centerXAnchor).isActive = true
        activityIndicator.centerYAnchor.constraint(equalTo: loadingView.centerYAnchor).isActive = true
        
        loadingView.tag = 1234
        
        activityIndicator.startAnimating()
        
        view.bringSubviewToFront(activityIndicator)
        loadingView.isHidden = false
    }
    
    func stopLoading() {
        if let activityIndicator = view.viewWithTag(1234) {
            activityIndicator.removeFromSuperview()
        }
    }
}

