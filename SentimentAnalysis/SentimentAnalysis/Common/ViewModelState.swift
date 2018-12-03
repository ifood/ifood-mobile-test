//
//  ViewModelState.swift
//  SentimentAnalysis
//
//  Created by Thales Frigo on 01/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import Foundation

enum ViewModelState<Data, Error> {
    case loading
    case load(Data)
    case empty
    case error(Error)
}

extension ViewModelState: Equatable {
    static func == (lhs: ViewModelState<Data, Error>, rhs: ViewModelState<Data, Error>) -> Bool {
        switch (lhs, rhs) {
        case (.loading, .loading), (.empty, .empty):
            return true
        default:
            return false
        }
    }
}
