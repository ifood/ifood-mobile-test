//
//  LoginViewModel.swift
//  ListTweets
//
//  Created by Kaique de Souza Monteiro on 06/09/2018.
//

import Foundation
import Alamofire
import SwiftyJSON

/// Prove os dados para a tela de insercao de usuario
class LoginViewModel {
    
    /// Classe que abstrai os metodos genericos
    let genericHelper = GenericHelper()
    
    /// Envia requisição para verificar se o usuario existe de fato
    ///
    /// - Parameters:
    ///     - user: nome de usuario informado
    ///     - success: callback chamada ao fim da requisição se bem sucedida
    ///     - failure: callback chamada se houver algum erro
    func verifyUsername(user: String, success: @escaping () -> Void, failure: @escaping () -> Void) {
        let paramName = ["username"]
        let param = [user]
        let url = genericHelper.formatUrl(base: Constants.TwitterBaseURL + Constants.URL.VerifyUser,
                                          paramNames: paramName, params: param)
        let request = Alamofire.request(url, method: .get)
        request.responseJSON { response in
            switch response.result {
            case .success(let data):
                let json = JSON(data)
                if json["valid"] == false {
                    success()
                } else {
                   failure()
                }
                
            case .failure(_):
                failure()
            }
        
        }
    }
}
