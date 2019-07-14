//
//  Observable.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

class Observable<T> {
    fileprivate var _value: T?
    var didChange: ((T) -> Void)?
    var value: T {
        set {
            _value = newValue
            didChange?(_value!)
        }
        get {
            return _value!
        }
    }

    init(_ value: T) {
        self.value = value
    }

    deinit {
        _value = nil
        didChange = nil
    }
}
