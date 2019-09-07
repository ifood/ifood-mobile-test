//
//  Errors.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 18/06/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import UIKit
import Resources

public enum Errors: Error {
    case cellCantBeNil
    case baseURLCantBeNil
    case codableError
    case requestError
    case couldNotLoad
}

extension Errors: LocalizedError {
    public var errorDescription: String? {
        switch self {
        case .cellCantBeNil:
            return L10n.CellError.cantBeNil
        case .baseURLCantBeNil:
            return L10n.ErrorBaseUrl.cantBeNil
        case .codableError:
            return L10n.CodableError.unknownError
        case .requestError:
            return L10n.RequestError.unknownError
        case .couldNotLoad:
            return L10n.RequestError.couldNotLoad
        }
    }
}
