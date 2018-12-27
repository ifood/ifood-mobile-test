//
//  UIRefreshControl+Extensions.swift
//  TwitterSentiment
//
//  Created by Jean Vinge on 29/11/18.
//  Copyright © 2018 Jean Vinge. All rights reserved.
//

import UIKit

extension UIRefreshControl {

    var scrollview: UIScrollView {
        guard let scrollview = self.superview as? UIScrollView else {
            fatalError("Refresh control is not in a UIScrollView")
        }
        return scrollview
    }

    func beginRefreshingProgrammatically(_ afterDelay: TimeInterval = 0) {
        if !self.isRefreshing {
            self.perform(#selector(do_beginRefreshingProgrammatically), with: nil, afterDelay: afterDelay)
        }
    }

    @objc func do_beginRefreshingProgrammatically(sendActions: Bool = true) {
        self.do_beginRefreshingProgrammaticallyWith(-self.scrollview.contentInset.top, sendActions: sendActions)
    }

    func do_beginRefreshingProgrammaticallyWith(_ offsetY: CGFloat, animated: Bool = true, sendActions: Bool = true) {
        if sendActions {
            self.sendActions(for: .valueChanged)
        }
        CATransaction.begin()
        self.beginRefreshing()
        CATransaction.commit()
        self.scrollview.setContentOffset(CGPoint(x: 0, y: offsetY), animated: animated)
    }
}
