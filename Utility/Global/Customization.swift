//
//  Customization.swift
//  matchAcesso
//
//  Created by Jean Vinge on 31/08/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import UIKit

public struct ImageControlState {
    let image: UIImage
    let state: UIControl.State
}

public class Customization: NSObject {

    // MARK: Var

    var text: String = ""
    var placeholder: String = ""
    var titleColor: UIColor = .systemBlue
    var backgroundColor: UIColor = .clear
    var font: UIFont = .systemFont(ofSize: 12)
    var image: UIImage?
    var alignment: NSTextAlignment = .left
    var numberOfLines: Int = 1
    var adjustsFontSizeToFitWidth: Bool = false
    var keyboardType: UIKeyboardType = .asciiCapable
    var isSecureTextEntry: Bool = false
    var returnKeyType: UIReturnKeyType = .send
    var autocorrectionType: UITextAutocorrectionType = .default
    var autocapitalizationType: UITextAutocapitalizationType = .allCharacters
    var lineSpacing: CGFloat = 0
    var spaceBetweenLetters: CGFloat = 0
    var imageControls: [ImageControlState] = []

    var cornerRadius: CGFloat = 0
    var masksToBounds: Bool = false

    // MARK: Init

    @discardableResult
    public func text(_ text: String) -> Customization {
        self.text = text
        return self
    }
    
    @discardableResult
    public func placeholder(_ placeholder: String) -> Customization {
        self.placeholder = placeholder
        return self
    }

    @discardableResult
    public func titleColor(_ titleColor: UIColor) -> Customization {
        self.titleColor = titleColor
        return self
    }

    @discardableResult
    public func backgroundColor(_ backgroundColor: UIColor) -> Customization {
        self.backgroundColor = backgroundColor
        return self
    }

    @discardableResult
    public func font(_ font: UIFont) -> Customization {
        self.font = font
        return self
    }

    @discardableResult
    public func image(_ image: UIImage) -> Customization {
        self.image = image
        return self
    }

    @discardableResult
    public func alignment(_ alignment: NSTextAlignment) -> Customization {
        self.alignment = alignment
        return self
    }

    @discardableResult
    public func numberOfLines(_ numberOfLines: Int) -> Customization {
        self.numberOfLines = numberOfLines
        return self
    }

    @discardableResult
    public func adjustsFontSizeToFitWidth(_ adjustsFontSizeToFitWidth: Bool) -> Customization {
        self.adjustsFontSizeToFitWidth = adjustsFontSizeToFitWidth
        return self
    }

    @discardableResult
    public func keyboardType(_ keyboardType: UIKeyboardType) -> Customization {
        self.keyboardType = keyboardType
        return self
    }

    @discardableResult
    public func isSecureTextEntry(_ isSecureTextEntry: Bool) -> Customization {
        self.isSecureTextEntry = isSecureTextEntry
        return self
    }

    @discardableResult
    public func returnKeyType(_ returnKeyType: UIReturnKeyType) -> Customization {
        self.returnKeyType = returnKeyType
        return self
    }

    @discardableResult
    public func autocorrectionType(_ autocorrectionType: UITextAutocorrectionType) -> Customization {
        self.autocorrectionType = autocorrectionType
        return self
    }
    
    @discardableResult
    public func autocapitalizationType(_ autocapitalizationType: UITextAutocapitalizationType) -> Customization {
        self.autocapitalizationType = autocapitalizationType
        return self
    }

    @discardableResult
    public func lineSpacing(_ lineSpacing: CGFloat) -> Customization {
        self.lineSpacing = lineSpacing
        return self
    }

    @discardableResult
    public func spaceBetweenLetters(_ spaceBetweenLetters: CGFloat) -> Customization {
        self.spaceBetweenLetters = spaceBetweenLetters
        return self
    }

    @discardableResult
    public func cornerRadius(_ cornerRadius: CGFloat) -> Customization {
        self.cornerRadius = cornerRadius
        return self
    }

    @discardableResult
    public func masksToBounds(_ masksToBounds: Bool) -> Customization {
        self.masksToBounds = masksToBounds
        return self
    }

    @discardableResult
    public func imageControls(_ imageControls: [ImageControlState]) -> Customization {
        self.imageControls = imageControls
        return self
    }
}
