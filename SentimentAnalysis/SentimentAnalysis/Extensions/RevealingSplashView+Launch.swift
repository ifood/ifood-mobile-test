//
//  RevealingSplashView+Launch.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation
import RevealingSplashView

extension RevealingSplashView {
    
    static func makeSplash() -> RevealingSplashView {
        let revealingSplashView = RevealingSplashView(
            iconImage: UIImage(named: "feelings")!,
            iconInitialSize: CGSize(width: 200, height: 200),
            backgroundColor: .primary
        )
        revealingSplashView.backgroundImageView?.contentMode = .scaleAspectFit
        revealingSplashView.animationType = .heartBeat
        return revealingSplashView
    }
}
