//
//  LoginViewController.swift
//  ListTweets
//
//  Created by Kaique de Souza Monteiro on 06/09/2018.
//

import UIKit

/// Controla os elementos visuais da tela de login
class LoginViewController: UIViewController {

    /// Campo de texto onde o usuario insere o username para obter os tweets
    @IBOutlet weak var userText: UITextField!
    
    /// Botao de buscar
    @IBOutlet weak var searchButton: UIButton!
    
    /// ViewModel que traz as informacoes para a tela
    let loginViewModel = LoginViewModel()
    
    /// Classe que abstrai os metodos de criacao de alertas
    let alertHelper = GenericHelper()
    
    /// Nome de usuario que sera usado para enviar para a proxima tela
    var username = ""
    
    override func viewDidLoad() {
        searchButton.layer.cornerRadius = 5
        super.viewDidLoad()

    }

    /// Chamada quando o usuario seleciona o botao de entrar
    @IBAction func loginTap(_ sender: Any) {
        // verifica se o usuario informado existe
        guard let text = userText.text, text != "" else {
            alertHelper.showAlertMessage(alertMessage: NSLocalizedString("USER_NOT_INFORMED", comment: ""),
                                         target: self)
            return
        }
        // mostra alerta de busca em andamento
        let spinningAlert = alertHelper.showSpinningWaitingAlert(
            alertTitle: NSLocalizedString("MSG_SEARCHING_USER", comment: ""), target: self)
        loginViewModel.verifyUsername(user: text, success: {
            spinningAlert.dismiss(animated: false, completion: {
                self.username = text
                self.performSegue(withIdentifier: Constants.Segue.ShowListTweets, sender: nil)
            })
        }, failure: {
            spinningAlert.dismiss(animated: false, completion: {
                self.alertHelper.showAlertMessage(alertMessage: NSLocalizedString("USER_NOT_FOUND", comment: ""),
                                                  target: self)
            })
            
        })

    }
    
    // MARK: - Navigation
     
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if Constants.Segue.ShowListTweets == segue.identifier {
            guard let tabBarController = segue.destination as? UITabBarController else { return }
            guard let listTweetsVC = tabBarController.viewControllers![0] as? ListTweetsViewController else { return }
            listTweetsVC.username = self.username
        }
    }

}
