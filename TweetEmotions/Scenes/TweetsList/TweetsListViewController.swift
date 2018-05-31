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
        setupDataSource()
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
    
    private func setupDataSource() {
        dataSource.displayTweets = { [weak self] in
            guard let strongSelf = self else { return }
            strongSelf.tableView.reloadData()
        }
        
        dataSource.displayError = { error in
            print(error)
        }
        
        loadUserTimeline()
    }
    
    private func addTapGesture() {
        let tap = UITapGestureRecognizer(target: self, action: #selector(loadUserTimeline))
        view.addGestureRecognizer(tap)
    }
    
    @objc
    private func loadUserTimeline() {
        lastTimelineLoaded = lastTimelineLoaded == "rafael_csa" ? "siracusa" : "rafael_csa"
        dataSource.loadTweets(for: lastTimelineLoaded)
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
        return cell
    }
}
