//
//  LottieView.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 30/11/18.
//  Copyright © 2018 oxltech.com. All rights reserved.
//

import Lottie

class LottieView: UIView {
    var lottieView: LOTAnimationView?
    var lottieSize = CGSize.zero
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    init(lottieName: String, size: CGSize) {
        super.init(frame: CGRect.zero)
        self.lottieView = LOTAnimationView(name: lottieName)
        self.lottieSize = size
        
    }
    
    func startAnimating() {
        if let _superview = self.superview, let lottieView = self.lottieView {
            let frame = CGRect(x: 0, y: 0, width: _superview.frame.width, height: _superview.frame.height)
            self.frame = frame
            self.alpha = 0
            self.backgroundColor = UIColor(red: 0, green: 0, blue: 0, alpha: 0.2)
            self.addSubview(lottieView)
            
            lottieView.frame = CGRect(x: 0, y: 0, width: self.lottieSize.width, height: self.lottieSize.height)
            lottieView.center = self.center
            
            lottieView.loopAnimation = true
            lottieView.play()
            
            UIView.animate(withDuration: 0.3) {
                self.alpha = 1.0
            }
        }
    }
    
    func stopAnimating() {
        self.lottieView?.stop()
        UIView.animate(withDuration: 0.2) {
            self.alpha = 0
        }
    }
}



