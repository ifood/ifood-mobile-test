//
//  NotificationCenter+Extensions.swift
//  Coordinator
//
//  Created by Jean Vinge on 05/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit

protocol NotificationName {
    var name: Notification.Name { get }
}

extension RawRepresentable where RawValue == String, Self: NotificationName {
    var name: Notification.Name {
        return Notification.Name(self.rawValue)
    }
}

enum NotificationKey: String, NotificationName {
    case viewWillAppear
}

extension NotificationCenter {
    func post(notification: NotificationKey, with object: Any? = nil) {
        self.post(name: notification.name, object: object)
    }
    func addObserver(observe: Any, selector: Selector, notification: NotificationKey, object: Any? = nil) {
        self.addObserver(observe, selector: selector, name: notification.name, object: object)
    }
}
