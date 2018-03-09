//
//  TwitterSentimentViewModel.swift
//  twitments
//
//  Created by Ezequiel on 08/03/18.
//  Copyright © 2018 Ezequiel. All rights reserved.
//

import Foundation

public struct TwitterSentimentViewModel {
    
    fileprivate var model:TwitterResultViewModel
    
    init(model:TwitterResultViewModel) {
        self.model = model
    }
}
