//
//  AlertMessageView.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation
import SwiftMessages

public class AlertMessageView {
    
    private var status:MessageView
    static let shared: AlertMessageView = {
        let instance = AlertMessageView()
        return instance
    }()
    
    init() {
        let backgroundColor:UIColor = UIColor.red
        let style: MessageView.Layout = .statusLine
        let textColor:UIColor = UIColor.white
        status = MessageView.viewFromNib(layout: style)
        status.button?.isHidden = true
        status.iconImageView?.isHidden = false
        status.titleLabel?.isHidden = true
        status.backgroundView.backgroundColor = backgroundColor
        status.bodyLabel?.textColor = textColor
        let message:String = "Loading ..."
        status.configureContent(body: NSLocalizedString(message, comment: ""))
    }
    
    func loading(bottom:Bool = false) {
        var statusConfig = SwiftMessages.defaultConfig
        statusConfig.duration = .forever
        statusConfig.presentationContext = .window(windowLevel: UIWindowLevelStatusBar)
        status.backgroundColor = "26C281".hexColor
        //if bottom { statusConfig.presentationStyle = .bottom }
        let message:String = "Loading ..."
        status.configureContent(body: NSLocalizedString(message, comment: ""))
        SwiftMessages.show(config: statusConfig, view: status)
    }
    
    func showFinished() {
        var statusConfig = SwiftMessages.defaultConfig
        let message:String = "Authenticated"
        statusConfig.duration = .seconds(seconds: 1.0)
        statusConfig.presentationContext = .window(windowLevel: UIWindowLevelStatusBar)
        status.backgroundColor = "269AFD".hexColor
        status.configureContent(body: NSLocalizedString(message, comment: ""))
        SwiftMessages.show(config: statusConfig, view: status)
    }
    
    func stop() {
        SwiftMessages.hide()
    }
}
