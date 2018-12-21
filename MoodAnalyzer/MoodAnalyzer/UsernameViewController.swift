//
//  ViewController.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 14/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import UIKit

class UsernameViewController: UIViewController {
    
    // MARK: - Variables
    var viewModel = UsernameViewModel()
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    // MARK: - Constraints
    @IBOutlet weak var textFieldCenterYConstraint: NSLayoutConstraint!
    @IBOutlet weak var textFieldBottomConstraint: NSLayoutConstraint!
    @IBOutlet weak var analyzeButtonBottomConstraint: NSLayoutConstraint!
    
    // MARK: - IBOUtlets
    @IBOutlet weak var moodAnalyzerLabel: UILabel! {
        didSet {
            self.moodAnalyzerLabel.layer.shadowColor = UIColor.charcoalGray.withAlphaComponent(0.2).cgColor
            self.moodAnalyzerLabel.layer.shadowOffset = CGSize(width: 0, height: 1.0)
            self.moodAnalyzerLabel.layer.shadowRadius = 6.0
            self.moodAnalyzerLabel.font = FontUtils.getScaledFont(forFont: "Montserrat-Bold", textStyle: .largeTitle)
            self.moodAnalyzerLabel.textColor = .white
            self.moodAnalyzerLabel.adjustsFontForContentSizeCategory = true
        }
    }
    @IBOutlet weak var decorativeView: UIView! {
        didSet {
            self.decorativeView.backgroundColor = .cornflowerBlue
        }
    }
    @IBOutlet weak var emojiLabel: UILabel!
    @IBOutlet weak var usernameTextField: UITextField! {
        didSet {
            self.usernameTextField.placeholder = "type username here"
            self.usernameTextField.textColor = .downriverBlue
            self.usernameTextField.font = UIFont.preferredFont(forTextStyle: .title2)
            self.usernameTextField.textAlignment = .center
            self.usernameTextField.tintColor = .cornflowerBlue
            self.usernameTextField.autocorrectionType = .no
            self.usernameTextField.autocapitalizationType = .none
            self.usernameTextField.adjustsFontForContentSizeCategory = true
        }
    }
    @IBOutlet weak var analyzeButton: UIButton! {
        didSet {
            self.analyzeButton.imageView?.contentMode = .scaleAspectFit
            self.analyzeButton.isHidden = true
        }
    }
    
    // MARK: - VC Functions
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.setupView()
    }
    
    override func viewWillLayoutSubviews() {
        super.viewWillLayoutSubviews()
        
        
        // Setting up the Decorative View
        let layer = CAShapeLayer()
        let path = UIBezierPath()
        path.move(to: CGPoint(x: 0, y: 0))
        path.addLine(to: CGPoint(x: self.view.frame.width, y: 0))
        path.addLine(to: CGPoint(x: self.view.frame.width, y: self.view.frame.height*0.2))
        path.addLine(to: CGPoint(x: 0, y: self.view.frame.height/2))
        path.close()
        
        layer.path = path.cgPath
        layer.fillColor = UIColor.cornflowerBlue.cgColor
        
        self.decorativeView.layer.mask = layer
    }
    
    func setupView() {
        self.navigationController?.isNavigationBarHidden = true
        
        // MARK: - Animate Button
        let animation = CABasicAnimation()
        animation.toValue = 10
        animation.duration = 0.5
        animation.repeatCount = Float.infinity
        animation.autoreverses = true
        animation.isRemovedOnCompletion = false
        self.analyzeButton.imageView?.layer.add(animation, forKey: "transform.translation.y")
        
        self.navigationController?.isNavigationBarHidden = true
        
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillShow), name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillHide), name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    // MARK: - IBActions
    @IBAction func didTapAnalyzeButton(_ sender: Any) {
        if let nav = self.navigationController {
            self.viewModel.didTapAnalyzeButton(textField: self.usernameTextField, navigationController: nav)
        }
    }
    
    @IBAction func dismissKeyboard(_ sender: Any) {
        view.endEditing(true)
    }
    
    @IBAction func textFieldDidchange(_ textField: UITextField) {
        if let text = textField.text {
            self.analyzeButton.isHidden = text.isEmpty ? true : false
        }
    }
}

