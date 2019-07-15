//
//  BaseLocalizable.swift
//  twitter-mood
//
//  Created by Dennis Merli on 7/6/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import UIKit

class BaseLocalizable: NSObject {
    
    static let okTitle: String = NSLocalizedString("Ok",
                                                   tableName: "BaseLocalizable",
                                                   bundle: Bundle.main,
                                                   value: "Ok",
                                                   comment: "Ok title")
    
    static let back: String = NSLocalizedString("back",
                                                tableName: "BaseLocalizable",
                                                bundle: Bundle.main,
                                                value: "back",
                                                comment: "Back title")
    
    static let cancel: String = NSLocalizedString("Cancel",
                                                  tableName: "BaseLocalizable",
                                                  bundle: Bundle.main,
                                                  value: "Cancel",
                                                  comment: "Cancel title")
    
    static let share: String = NSLocalizedString("Share",
                                                 tableName: "BaseLocalizable",
                                                 bundle: Bundle.main,
                                                 value: "Share",
                                                 comment: "Share title")
    
    static let close: String = NSLocalizedString("Close",
                                                 tableName: "BaseLocalizable",
                                                 bundle: Bundle.main,
                                                 value: "Close",
                                                 comment: "Close title")
    
    static let delete: String = NSLocalizedString("Delete",
                                                  tableName: "BaseLocalizable",
                                                  bundle: Bundle.main,
                                                  value: "Delete",
                                                  comment: "Delete title")
    
    static let openLibrary: String = NSLocalizedString("Open library",
                                                       tableName: "BaseLocalizable",
                                                       bundle: Bundle.main,
                                                       value: "Open library",
                                                       comment: "Open library")
    
    static let takePhoto: String = NSLocalizedString("Take photo",
                                                     tableName: "BaseLocalizable",
                                                     bundle: Bundle.main,
                                                     value: "Take photo",
                                                     comment: "Take photo")
    
    static let error: String = NSLocalizedString("Error",
                                                 tableName: "BaseLocalizable",
                                                 bundle: Bundle.main,
                                                 value: "Error",
                                                 comment: "Error title")
    
    static let unknownError: String = NSLocalizedString("Unknown Error",
                                                        tableName: "BaseLocalizable",
                                                        bundle: Bundle.main,
                                                        value: "Unknown Error",
                                                        comment: "Unknown Error title")
    
    static let defaultErrorDescription: String = NSLocalizedString("Default error",
                                                                   tableName: "BaseLocalizable",
                                                                   bundle: Bundle.main,
                                                                   value: "An error has occurred.",
                                                                   comment: "An error has occurred message")
    
    static let cannotParse: String = NSLocalizedString("Could not parse",
                                                       tableName: "BaseLocalizable",
                                                       bundle: Bundle.main,
                                                       value: "Could not get the response.",
                                                       comment: "Could not parse the response error")
    
    static let networkError: String = NSLocalizedString("Network Error",
                                                        tableName: "BaseLocalizable",
                                                        bundle: Bundle.main,
                                                        value: "Network error. Please check your network connection.",
                                                        comment: "Network error message")
    
    static let errorForbidden: String = NSLocalizedString("Network Error Forbidden",
                                                          tableName: "BaseLocalizable",
                                                          bundle: Bundle.main,
                                                          value: "Forbidden. Please check your credentials",
                                                          comment: "Network error forbidden message")
    
    static let errorNotFound: String = NSLocalizedString("Network Error Not found",
                                                         tableName: "BaseLocalizable",
                                                         bundle: Bundle.main,
                                                         value: "Not found. Please try again later",
                                                         comment: "Network error not found message")
    
    static let errorInternalServer: String = NSLocalizedString("Network Error Internal server error",
                                                               tableName: "BaseLocalizable",
                                                               bundle: Bundle.main,
                                                               value: "Internal server error. Please try again later",
                                                               comment: "Network error internal server error message")
    
    static let errorUnauthorized: String = NSLocalizedString("Network Error Unauthorized",
                                                             tableName: "BaseLocalizable",
                                                             bundle: Bundle.main,
                                                             value: "Unauthorized. Please check your credentials",
                                                             comment: "Network error unauthorized message")
    
    static let emptyTitleDefault: String = NSLocalizedString("Nothing to show",
                                                             tableName: "BaseLocalizable",
                                                             bundle: Bundle.main,
                                                             value: "Nothing to show",
                                                             comment: "Nothing to show")
    static func nilParameter(_ parameter: String) -> String {
        return String.localizedStringWithFormat(NSLocalizedString("The parameter: %@ is null", comment: "message for when a parameter is null"), parameter)
    }
    
}

