//
//  ViewModel.swift
//  matchAcesso
//
//  Created by Jean Vinge on 20/08/2018.
//  Copyright © 2018 Atlas Politico. All rights reserved.
//

import Foundation

public protocol ViewModelProtocol {
    associatedtype Input
    associatedtype Output

    var coordinator: CoordinatorProvider { get set }

    func transform(input: Input) -> Output
}
