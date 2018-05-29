//
//  TweetListViewController.swift
//  Twimotion
//
//  Created by Antony on 27/05/18.
//  Copyright © 2018 Twimotion. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa
import RxDataSources

class TweetListViewController: UIViewController {

    var viewModel: TweetsListViewModelType?
    var disposeBag = DisposeBag()

    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    @IBOutlet weak var emptyAlertLabel: UILabel!

    // MARK: - Initializers

    init(viewModel: TweetsListViewModelType) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

    // MARK: - Lifecycler

    override func viewDidLoad() {
        super.viewDidLoad()
        setupViews()
        bindViews()
    }

}

// MARK: - Setups
extension TweetListViewController {
    private func setupViews() {
        if #available(iOS 11.0, *) {
            self.navigationController?.navigationBar.prefersLargeTitles = true
            self.navigationItem.largeTitleDisplayMode = .always
        }
        self.navigationController?.isNavigationBarHidden = false

        // tableview
        tableView.rowHeight = UITableViewAutomaticDimension
        tableView.estimatedRowHeight = 140
        tableView.tableFooterView = UIView()
        tableView.registerCellWithNib(TweetCell.self)

        // emptyAlert label
        // TODO: add button to retry
        emptyAlertLabel.text = L10n.Tweets.tweetsNotFound
        emptyAlertLabel.isHidden = true
    }
}

// MARK: - Rx
extension TweetListViewController {

    private func bindViews() {

        guard let viewModel = viewModel else { fatalError("viewModel should not be nil") }

        bindTableView()

        viewModel.username
            .map { "@\($0)" }
            .bind(to: self.rx.title)
            .disposed(by: disposeBag)

        viewModel.couldNotLoadTweets.bind { [weak emptyAlertLabel] in
            emptyAlertLabel?.isHidden = false
        }.disposed(by: disposeBag)

        viewModel.isLoadingTweets.bind { [weak self] isLoading in
            self?.activityIndicator.isHidden = !isLoading
            self?.tableView.isHidden = isLoading
        }.disposed(by: disposeBag)

    }

    private func bindTableView() {

        guard let viewModel = viewModel else { fatalError("viewModel should not be nil") }

        let dataSource = RxTableViewSectionedReloadDataSource<TweetsListSectionViewModel>(configureCell: { _, tableView, indexPath, vm -> TweetCell in
            let cell = tableView.dequeueReusableCell(type: TweetCell.self, indexPath: indexPath)
            cell.viewModel = vm
            return cell
        })

        dataSource.titleForHeaderInSection = { ds, index in
            return ds.sectionModels[index].model
        }

        tableView.dataSource = nil

        viewModel.cellViewModels
            .bind(to: tableView.rx.items(dataSource: dataSource))
            .disposed(by: disposeBag)

        tableView.rx.itemSelected
            .observeOn(MainScheduler.asyncInstance)
            .map { $0.row }
            .bind(to: viewModel.selectTweetEvent)
            .disposed(by: disposeBag)
    }
}
