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
