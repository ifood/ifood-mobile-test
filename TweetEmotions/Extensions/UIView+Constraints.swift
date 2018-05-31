import UIKit

extension UIView {
    func constrainToSuperviewBounds(insettedBy insets: UIEdgeInsets = UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 0)) {
        guard let superview = superview else {
            fatalError("Constrain to superview bounds, but no superview?")
        }
        
        activateConstraints([
            topAnchor.constraint(equalTo: superview.topAnchor, constant: insets.top),
            leadingAnchor.constraint(equalTo: superview.leadingAnchor, constant: insets.left),
            trailingAnchor.constraint(equalTo: superview.trailingAnchor, constant: insets.right),
            bottomAnchor.constraint(equalTo: superview.bottomAnchor, constant: insets.bottom)
        ])
    }
    
    func constrainToSafeAndReadableGuides(insettedBy insets: UIEdgeInsets = UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 0)) {
        guard let superview = superview else {
            fatalError("Constrain to safe and readable guides, but no superview?")
        }
        
        activateConstraints([
            topAnchor.constraint(equalTo: superview.safeAreaLayoutGuide.topAnchor, constant: insets.top),
            leadingAnchor.constraint(equalTo: superview.readableContentGuide.leadingAnchor, constant: insets.left),
            trailingAnchor.constraint(equalTo: superview.readableContentGuide.trailingAnchor, constant: insets.right),
            bottomAnchor.constraint(equalTo: superview.safeAreaLayoutGuide.bottomAnchor, constant: insets.bottom)
        ])
    }
    
    func centerInSuperview() {
        guard let superview = superview else {
            fatalError("Center in superview, but no superview?")
        }
        
        activateConstraints([
            centerXAnchor.constraint(equalTo: superview.centerXAnchor),
            centerYAnchor.constraint(equalTo: superview.centerYAnchor)
        ])
    }
    
    func constrainSize(to dimension: CGFloat) {
        activateConstraints([
            widthAnchor.constraint(equalToConstant: dimension),
            heightAnchor.constraint(equalToConstant: dimension)
        ])
    }
    
    func activateConstraints(_ constraints: [NSLayoutConstraint]) {
        NSLayoutConstraint.activate(constraints)
    }
}
