import UIKit

protocol ReusableView {}

extension ReusableView where Self: UITableViewCell {
    static var cellIdentifier: String {
        return "\(Self.self)"
    }
    
    static func register(for tableView: UITableView) {
        tableView.register(self.classForCoder(), forCellReuseIdentifier: cellIdentifier)
    }
    
    static func dequeueCell(from tableView: UITableView, at indexPath: IndexPath) -> Self {
        if let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as? Self {
            return cell
        } else {
            return Self()
        }
    }
}
