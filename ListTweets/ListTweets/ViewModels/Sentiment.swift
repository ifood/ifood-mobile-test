//
//  Sentiment.swift
//  ListTweets
//
//  Created by Kaique de Souza Monteiro on 08/09/2018.
//


import UIKit

/// Enum com os sentimentos identificados pelo algoritmo de reconhecimento
enum Sentiment {
    
    /// Neutro
    case neutral
    
    /// Positivo
    case positive
    
    /// Negativo
    case negative
    
    /// Emoji associado ao sentimento
    var emoji: String {
        switch self {
        case .neutral:
            return "😐"
        case .positive:
            return "😃"
        case .negative:
            return "😔"
        }
    }
    
    /// Cor associado ao sentimento
    var color: UIColor? {
        switch self {
        case .neutral:
            return UIColor(named: "NeutralColor")
        case .positive:
            return UIColor(named: "PositiveColor")
        case .negative:
            return UIColor(named: "NegativeColor")
        }
    }
}
