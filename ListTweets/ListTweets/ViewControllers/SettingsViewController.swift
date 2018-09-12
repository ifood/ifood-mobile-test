//
//  SecondViewController.swift
//  ListTweets
//
//  Created by Kaique de Souza Monteiro on 05/09/2018.
//

import UIKit

/// Responsavel pela tela de configuracoes
class SettingsViewController: UIViewController {
    
    /// Botao de trocar usuario
    @IBOutlet weak var changeUsrButton: UIButton!
    
    override func viewDidLoad() {
        changeUsrButton.layer.cornerRadius = 5
    }

}

