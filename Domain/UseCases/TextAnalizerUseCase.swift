//
//  TextAnalizerCase.swift
//  Domain
//
//  Created by Jean Vinge on 26/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import Foundation
import RxSwift

public protocol TextAnalizerUseCase {
    func sentiment(text: String) -> Observable<Domain.Sentiment>
}
