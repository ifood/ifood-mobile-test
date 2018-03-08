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
    
    init() {
        let backgroundColor:UIColor = UIColor.red
        let style: MessageView.Layout = .statusLine
        let textColor:UIColor = UIColor.white
        let message:String = "Loading ..."
        status = MessageView.viewFromNib(layout: style)
        status.button?.isHidden = true
        status.iconImageView?.isHidden = false
        status.titleLabel?.isHidden = true
        status.backgroundView.backgroundColor = backgroundColor
        status.bodyLabel?.textColor = textColor
        status.configureContent(body: NSLocalizedString(message, comment: ""))
    }
    
    func loading() {
        var statusConfig = SwiftMessages.defaultConfig
        statusConfig.duration = .forever
        statusConfig.presentationContext = .window(windowLevel: UIWindowLevelStatusBar)
        status.backgroundColor = UIColor.green
        SwiftMessages.show(config: statusConfig, view: status)
    }
    
    func showFinished() {
        var statusConfig = SwiftMessages.defaultConfig
        let message:String = "Finished!"
        statusConfig.duration = .seconds(seconds: 0.3)
        statusConfig.presentationContext = .window(windowLevel: UIWindowLevelStatusBar)
        status.backgroundColor = UIColor.blue
        status.configureContent(body: NSLocalizedString(message, comment: ""))
        SwiftMessages.show(config: statusConfig, view: status)
    }
    
    func stop() {
        SwiftMessages.hide()
    }
}
