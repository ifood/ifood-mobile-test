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
    @IBOutlet weak var tryAgainButton: UIButton!
    @IBOutlet weak var emptyAlertView: UIStackView!

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
        emptyAlertLabel.text = L10n.Tweets.tweetsNotFound
    }
}

// MARK: - Rx
extension TweetListViewController {

    private func bindViews() {

        guard let viewModel = viewModel else { fatalError("viewModel should not be nil") }

        bindTableView()

        // Inputs
        tryAgainButton.rx.tap
            .bind(to: viewModel.loadTweets)
            .disposed(by: disposeBag)

        // Outpts
        viewModel.username
            .map { "@\($0)" }
            .bind(to: self.rx.title)
            .disposed(by: disposeBag)

        viewModel.isLoadingTweets
            .asDriver(onErrorJustReturn: true)
            .map { !$0 }
            .drive(activityIndicator.rx.isHidden)
            .disposed(by: disposeBag)

        // when could not load tweets should show emptyAlertView
        viewModel.couldNotLoadTweets
            .asDriver(onErrorJustReturn: true)
            .map { !$0 }
            .drive(emptyAlertView.rx.isHidden)
            .disposed(by: disposeBag)

        // when isLoading or did occured any errors, then tableView should be hide
        Observable
            .combineLatest(
                viewModel.couldNotLoadTweets,
                viewModel.isLoadingTweets,
                resultSelector: { (isLoading: $0, errorLoading: $1)}
            )
            .flatMap { Observable<Bool>.just($0.isLoading || $0.errorLoading) }
            .bind(to: tableView.rx.isHidden)
            .disposed(by: disposeBag)

        // Load lastest tweets
        viewModel.loadTweets.onNext(())
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
