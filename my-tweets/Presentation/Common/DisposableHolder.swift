//
//  DisposableHolder.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import RxSwift

protocol DisposableHolder {
    var disposeBag: DisposeBag {get}
}
