//
//  HomeVC.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 14/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

class HomeVC: UIViewController {

    // MARK: - Properties
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var btnSearch: UIButton!
    @IBOutlet weak var fieldNickName: UITextField!

    var viewModel: HomeVM?

    // MARK: - Initialization
    init(viewModel: HomeVM) {
        super.init(nibName: nil, bundle: nil)
        self.viewModel = viewModel
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

    // MARK: - View Life Cycle
    override func viewDidLoad() {
        super.viewDidLoad()

        self.title = "TH Twitter"
        btnSearch.isEnabled = false
        tableView.register(TwitterItemCell.self)

        viewModel?.authenticate()
        addObservers()
    }

    // MARK: - Private Methods
    private func addObservers() {

        viewModel?.tokenStatus.didChange = { [weak self] status in

            guard let self = self else {
                return
            }

            switch status {
            case .load:
                DispatchQueue.main.async {
                    self.btnSearch.isEnabled = true
                }
            case .loading:
                break
            case .errored(error: let error):
                break
            }

        }

        viewModel?.status.didChange = { [weak self] status in

            guard let self = self else {
                return
            }

            switch status {
            case .load:
                DispatchQueue.main.async {
                    self.tableView.reloadData()
                }
            case .loading:
                break
            case .errored(error: let error):
                break
            }

        }
    }

    @IBAction func searchUser(_ sender: UIButton) {

        if let text = fieldNickName.text, text != "" {
            viewModel?.listTweets(nickname: text)
        }
    }
}

// MARK: - UITableViewDataSource
extension HomeVC: UITableViewDataSource {

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel?.list.count ?? 0
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

        guard let list = viewModel?.list else {
            return UITableViewCell()
        }

        return tableView.dequeueReusableCell(of: TwitterItemCell.self, for: indexPath) { cell in
            cell.setup(tweet: list[indexPath.row])
        }
    }

    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 150.0
    }
}

// MARK: - UITableViewDelegate
extension HomeVC: UITableViewDelegate {

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {

        guard let viewModel = viewModel else {
            return
        }
        let tweet = viewModel.list[indexPath.row]
        viewModel.openDetail(tweet: tweet)
    }
}
