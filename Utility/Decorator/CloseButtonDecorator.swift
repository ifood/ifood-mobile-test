//
//  CloseButtonDecorator.swift
//  Utility
//
//  Created by Jean Vinge on 27/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa
import Resources

public class CloseButtonDecorator: NSObject {
    
    // MARK: Init
    
    public static func closeButton(_ viewController: UIViewController, image: UIImage = Asset.iconClose.image) -> NavigationController {
        viewController.navigationItem.leftBarButtonItem = UIBarButtonItem(image: image)
        viewController.navigationItem.leftBarButtonItem?.rx.tap.map { viewController }.bind { vc in
            vc.dismiss(animated: true)
        }.disposed(by: viewController.rx.disposeBag)
        return NavigationController(viewController)
    }
}
