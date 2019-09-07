//
//  UIScrollView+Extensions.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 03/12/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit
import RxCocoa
import RxSwift

extension UIScrollView {
    var currentPage: Int {
        return Int((self.contentOffset.x + (0.5 * self.frame.size.width)) / self.frame.width) + 1
    }
    var pages: Int {
        return Int(self.contentSize.width * self.frame.size.width)
    }
    func scrollToPage(_ page: Int, animated: Bool) {
        self.setContentOffset(CGPoint(x: self.frame.size.width * CGFloat(page), y: 0), animated: animated)
    }
    func scrollToTop(animated: Bool = true) {
        DispatchQueue.main.async { [weak self] in
            guard let self = self else { return }
            self.setContentOffset(CGPoint(x: 0, y: -self.contentInset.top), animated: animated)
        }
    }
}
