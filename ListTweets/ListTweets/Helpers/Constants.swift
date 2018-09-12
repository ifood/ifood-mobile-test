//
//  Constants.swift
//  ListTweets
//
//  Created by Kaique de Souza Monteiro on 06/09/2018.
//

import Foundation

/// Contem as chaves para strings que sao usadas no app
struct Constants {
    
    /// URL base para chamadas para o site do Twitter
    static let TwitterBaseURL = "http://twitter.com/"
    
    /// URL base para as chamadas para a API do Twitter
    static let TwitterApiBaseURL = "https://api.twitter.com/1.1/"
    
    /// Chaves com os caminhos das URLs do servidor web, após a URL base.
    /// Concatenar com a constante ServerBaseURL
    struct URL {
        
        /// requisição de verificacao se o usuario existe
        static let VerifyUser = "users/username_available"
        
        /// requisicao de busca dos tweets
        static let GetTweets = "statuses/user_timeline.json"
    }
    
    /// Chaves com os valores dos Segues que forem usados entre ViewControllers
    struct Segue {
        
        /// Segue para exibir a tela que lista os tweets
        static let ShowListTweets = "ShowListTweetsSegue"
    }
    
    /// Chaves com os valores dos identificadores das celulas das tabelas
    struct Identifier {
        
        /// Chave para a celula da tabela de tweets
        static let TweetCell = "TweetCellIdentifier"
    }
}
