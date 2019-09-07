//
//  UIAlertActionController+Extensions.swift
//  MatchPolitico
//
//  Created by Jean Vinge on 12/07/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import UIKit
import Resources

public typealias AlertActionCompletion = (UIAlertAction) -> Void

extension UIAlertController {
    
    public convenience init(alert title: String = "", message: String = "", confirmCompletion: AlertActionCompletion? = nil, cancelCompletion: AlertActionCompletion? = nil) {
        let action = [UIAlertAction(title: L10n.DefaultText.cancel, style: .cancel, handler: cancelCompletion), UIAlertAction(title: L10n.DefaultText.ok, style: .default, handler: confirmCompletion)]
        self.init(title, message: message, preferredStyle: .alert, actions: action)
    }
    
    public convenience init(_ title: String? = nil, message: String? = nil, preferredStyle: UIAlertController.Style, actions: [UIAlertAction] = []) {
        self.init(title: title, message: message, preferredStyle: preferredStyle)
        actions.forEach { action in
            self.addAction(action)
        }
    }
    
    public convenience init(alerWithError error: Error) {
        self.init(errorWithMessage: error.localizedDescription)
    }
    
    public convenience init(errorWithMessage message: String) {
        self.init(L10n.DefaultText.error, message: message, preferredStyle: .alert, actions: [UIAlertAction(title: L10n.DefaultText.ok, style: .default)])
    }
}
