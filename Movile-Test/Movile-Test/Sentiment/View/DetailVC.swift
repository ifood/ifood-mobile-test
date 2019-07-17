//
//  DetailVC.swift
//  Movile-Test
//
//  Created by Thiago Felipe Alves on 17/07/19.
//  Copyright © 2019 Thalves. All rights reserved.
//

import UIKit

class DetailVC: UIViewController {

    // MARK: - Properties
    @IBOutlet weak var lblEmoji: UILabel!

    var viewModel: DetailVM?

    // MARK: - Initialization
    init(viewModel: DetailVM) {
        super.init(nibName: nil, bundle: nil)
        self.viewModel = viewModel
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

    // MARK: - View Life Cycle
    override func viewDidLoad() {
        super.viewDidLoad()

        self.title = "Analise"
        lblEmoji.text = ""
        addObservers()
    }

    // MARK: - Private Methods
    private func addObservers() {
        viewModel?.status.didChange = { [weak self] status in

            guard let self = self else {
                return
            }

            switch status {
            case .load(data: let sentiment):
                DispatchQueue.main.async {
                    self.updateUI(sentiment: sentiment)
                }
            case .loading:
                break
            case .errored(error: let error):
                break
            }
        }
    }

    private func updateUI(sentiment: Sentiment) {

        if sentiment.documentSentiment.score >= 0.25 {
            self.view.backgroundColor = .yellow
            lblEmoji.text = "😃"
        } else if sentiment.documentSentiment.score <= 0.25 {
            self.view.backgroundColor = .blue
            lblEmoji.text = "😔"
        } else {
            self.view.backgroundColor = .gray
            lblEmoji.text = "😐"
        }
    }
}
