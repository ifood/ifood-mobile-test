//
//  SceneTableViewAdapter.swift
//  my-tweets
//
//  Created by Gabriel Catice on 22/11/18.
//  Copyright © 2018 Gabriel Catice. All rights reserved.
//

import UIKit

protocol SceneTableViewAdapter: UITableViewDelegate, UITableViewDataSource {
    var tableView: UITableView { get set }
}
