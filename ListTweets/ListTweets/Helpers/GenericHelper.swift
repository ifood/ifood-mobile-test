//
//  AlertHelper.swift
//  ListTweets
//
//  Created by Kaique de Souza Monteiro on 07/09/2018.
//

import Foundation
import UIKit

/// Classe auxiliar para funcionalidades do view em geral que são frequentemente reutilizadas
class GenericHelper {
    
    /// Funcao auxiliar para mostrar uma mensagem de erro ou sucesso num alerta do sistema.
    /// Possui apenas um botão para fechar o alerta
    /// - Parameters:
    ///     - alertTitle: o título do alerta. Valor padrão : Atenção
    ///     - alertMessage: a mensagem mostrada no alerta, sucesso ou tipo do erro
    ///     - target: o controller que deve exibir o alerta
    ///     - closeCallback: função executada no clique do botão fechar
    func showAlertMessage(alertTitle: String = NSLocalizedString("TITLE_MSG_WARNING", comment: ""),
                          alertMessage: String, target: UIViewController,
                          closeCallback: (() -> Void)? = nil) {
        
        // Cria o alert controller
        let alertController = UIAlertController(title: alertTitle, message: alertMessage, preferredStyle: .alert)
        
        // Cria o botão de fechar
        let okAction = UIAlertAction(title: NSLocalizedString("BTN_CLOSE", comment: ""),
                                     style: UIAlertActionStyle.default) {
                                        UIAlertAction in
                                        if closeCallback != nil {
                                            closeCallback!()
                                        }
        }
        
        // Adiciona a ação do botão fechar
        alertController.addAction(okAction)
        
        // Exibe o controller
        target.present(alertController, animated: true, completion: nil)
    }
    
    /// Funcao auxiliar que monta uma url com parametros
    /// - Parameters:
    ///     - base: inicio da url
    ///     - paramNames: nomes dos parametros que serao enviados
    ///     - params: parametros que serao enviados
    func formatUrl(base: String, paramNames: [String], params: [String]) -> String{
        if params.count != paramNames.count || params.count == 0{
            return base
        }
        var url = base + "?\(paramNames[0])=\(params[0])"
        for i in 1..<params.count {
            url = url + "\(paramNames[i])=\(params[i])"
        }
        return url
        
    }
    
    /// Alerta de Loading com spinner
    /// Parameters:
    ///     - alertTitle: Título do alerta
    ///     - target: viewController sobre o qual o alerta é exibido
    /// Returns: o alerta que foi criado, é necessário obtê-lo para dar dissmiss quando acabar o carregamento
    func showSpinningWaitingAlert(alertTitle: String, target: UIViewController) -> UIAlertController {
        
        let waitingAlert: UIAlertController = UIAlertController(title: NSLocalizedString(alertTitle, comment: ""),
                                                                message: nil, preferredStyle: .alert)
        let spinnerViewController = UIViewController()
        
        // monta alerta com o indicador
        let loadingIndicator: UIActivityIndicatorView = UIActivityIndicatorView(activityIndicatorStyle: .gray)
        loadingIndicator.startAnimating()
        spinnerViewController.view.addSubview(loadingIndicator)
        
        NSLayoutConstraint.activate([
            NSLayoutConstraint(
                item: loadingIndicator,
                attribute: NSLayoutAttribute.centerX,
                relatedBy: NSLayoutRelation.equal,
                toItem: spinnerViewController.view,
                attribute: NSLayoutAttribute.centerX,
                multiplier: 1.0,
                constant: 0.0),
            NSLayoutConstraint(
                item: loadingIndicator,
                attribute: NSLayoutAttribute.centerY,
                relatedBy: NSLayoutRelation.equal,
                toItem: spinnerViewController.view,
                attribute: NSLayoutAttribute.centerY,
                multiplier: 1.0,
                constant: 0.0),
            NSLayoutConstraint(
                item: spinnerViewController.view,
                attribute: NSLayoutAttribute.bottom,
                relatedBy: NSLayoutRelation.equal,
                toItem: loadingIndicator,
                attribute: NSLayoutAttribute.bottom,
                multiplier: 1.0,
                constant: 20.0)
            ]
        )
        waitingAlert.setValue(spinnerViewController, forKey: "contentViewController")
        
        target.present(waitingAlert, animated: true, completion: nil)
        
        return waitingAlert
    }

}
