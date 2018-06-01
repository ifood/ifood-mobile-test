import UIKit

final class TweetsListViewController: UIViewController {
    
    // MARK: Init/Deinit
    
    init(dataSource: TweetsListDataSource) {
        self.dataSource = dataSource
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: UIViewController overrides
    
    override func loadView() {
        view = tableView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
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
        TweetsListCell.register(for: tableView)
        return tableView
    }()
    
    // MARK: Private methods
    
    private func loadTweets() {
        dataSource.loadTweets(for: lastTimelineLoaded) { [weak self] result in
            guard let strongSelf = self else { return }
            
            switch result {
            case .success:
                strongSelf.tableView.reloadData()
                
            case .failure(let error):
                print(error)
            }
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
