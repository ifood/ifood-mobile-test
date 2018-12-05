//
//  AsyncOperation.swift
//  ifood-mobile-test
//
//  Created by Marcio Garcia on 04/12/18.
//  Copyright © 2018 oxltech.com. All rights reserved.
//

import Foundation

class AsyncOperation: Operation {
    override var isAsynchronous: Bool {
        return true
    }
    
    private var _executing = false {
        willSet {
            willChangeValue(forKey: "isExecuting")
        }
        didSet {
            didChangeValue(forKey: "isExecuting")
        }
    }
    
    override var isExecuting: Bool {
        return _executing
    }
    
    private var _finished = false {
        willSet {
            willChangeValue(forKey: "isFinished")
        }
        
        didSet {
            didChangeValue(forKey: "isFinished")
        }
    }
    
    override var isFinished: Bool {
        return _finished
    }
    
    override func start() {
        _executing = true
        execute()
    }
    
    func execute() {
    }
    
    func finish() {
        _executing = false
        _finished = true
    }
}

