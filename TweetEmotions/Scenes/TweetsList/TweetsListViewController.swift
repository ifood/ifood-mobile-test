import UIKit

final class TweetsListViewController: UIViewController, HasLoadingState {
    
    // MARK: HasLoadingState
    
    var loadingState: LoadingState = .idle {
        didSet {
            didSetLoadingState()
        }
    }
    
    // MARK: Init/Deinit
    
    init(dataSource: TweetsListDataSource) {
        self.dataSource = dataSource
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: UIViewController overrides
    
    override func viewDidLoad() {
        super.viewDidLoad()
        addSubviews()
        addConstraints()
        addTapGesture()
        loadTweets()
    }
    
    // MARK: Private properties
    
    private let dataSource: TweetsListDataSource
    
    private lazy var tableView: UITableView = {
        let tableView = UITableView()
        tableView.rowHeight = UITableViewAutomaticDimension
        tableView.estimatedRowHeight = 80
        tableView.tableFooterView = UIView()
        tableView.dataSource = self
        tableView.translatesAutoresizingMaskIntoConstraints = false
        TweetsListCell.register(for: tableView)
        return tableView
    }()
    
    private lazy var spinner: UIActivityIndicatorView = {
        let spinner = UIActivityIndicatorView(activityIndicatorStyle: .gray)
        spinner.hidesWhenStopped = true
        spinner.translatesAutoresizingMaskIntoConstraints = false
        return spinner
    }()
    
    private lazy var errorView: ErrorView = {
        let view = ErrorView()
        view.isHidden = true
        view.delegate = self
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    // MARK: Private methods
    
    private func addSubviews() {
        view.addSubview(spinner)
        view.addSubview(errorView)
        view.addSubview(tableView)
    }
    
    private func addConstraints() {
        spinner.centerInSuperview()
        errorView.constrainToSafeAndReadableGuides()
        tableView.constrainToSafeAndReadableGuides()
    }
    
    private func loadTweets() {
        loadingState = .isLoading
        dataSource.loadTweets(for: lastTimelineLoaded) { [weak self] result in
            guard let strongSelf = self else { return }
            
            switch result {
            case .success:
                strongSelf.loadingState = .idle
                
            case .failure(let error):
                strongSelf.loadingState = .hasError(error)
            }
        }
    }
    
    private func didSetLoadingState() {
        switch loadingState {
        case .idle:
            spinner.stopAnimating()
            errorView.isHidden = true
            tableView.isHidden = false
            tableView.reloadData()
            
        case .isLoading:
            spinner.startAnimating()
            errorView.isHidden = true
            tableView.isHidden = true
            
        case .hasError:
            spinner.stopAnimating()
            errorView.isHidden = false
            tableView.isHidden = true
        }
    }
    
    private func addTapGesture() {
        let tap = UITapGestureRecognizer(target: self, action: #selector(changeUserTimeline))
        view.addGestureRecognizer(tap)
    }
    
    @objc
    private func changeUserTimeline() {
        lastTimelineLoaded = lastTimelineLoaded == "rafael_csa" ? "siracusa" : "rafael_csa"
        loadTweets()
    }
    
    private var lastTimelineLoaded = "rafael_csa"
}

extension TweetsListViewController: UITableViewDataSource {
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return dataSource.tweets.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = TweetsListCell.dequeueCell(from: tableView, at: indexPath)
        let tweet = dataSource.tweets[indexPath.row]
        cell.tweet = tweet
        
        loadProfileImage(for: tweet, in: cell)
        
        return cell
    }
    
    private func loadProfileImage(for tweet: Tweet, in cell: TweetsListCell) {
        cell.loadingState = .isLoading
        dataSource.loadProfileImage(for: tweet) { result in
            switch result {
            case .success(let image):
                // The check below is not really needed as we currently only display
                // a single's user timeline at a time, but leaving it here anyway
                // as a best practice.
                guard let cellTweetId = cell.tweet?.id, cellTweetId == tweet.id else { return }
                cell.profileImage = image
                cell.loadingState = .idle
                
            case .failure(let error):
                cell.loadingState = .hasError(error)
            }
        }
    }
}

extension TweetsListViewController: ErrorViewDelegate {
    func retry() {
        loadTweets()
    }
}
