//
//  FirstViewController.swift
//  ListTweets
//
//  Created by Kaique de Souza Monteiro on 05/09/2018.
//

import UIKit

class ListTweetsViewController: UIViewController {
    
    /// Nome do usuario passado pela tela anterior
    var username: String?
    
    /// ViewModel que traz as informacoes para a tela
    let listTweetsViewModel = ListTweetsViewModel()
    
    /// Classe que abstrai os metodos de criacao de alertas
    var alertHelper = GenericHelper()
    
    /// Label que mostrara o emoji
    @IBOutlet weak var emojiLabel: UILabel!
    
    /// Constraint que controla o tamanho da view de emocoes
    @IBOutlet weak var viewHeightConstraint: NSLayoutConstraint!
    
    /// Tabela com os tweets
    @IBOutlet weak var twitterTable: UITableView!
    
    /// Lista com os tweets vindos da API
    var tweets: [String] = []
    
    override func viewDidAppear(_ animated: Bool) {
        viewHeightConstraint.constant = 0
        let spinningAlert = alertHelper.showSpinningWaitingAlert(
            alertTitle: NSLocalizedString("MSG_SEARCHING_TWEETS", comment: ""), target: self)
        self.listTweetsViewModel.getTweetsFrom(user: username, success: { tweets in
            spinningAlert.dismiss(animated: false, completion: {
                self.tweets = tweets
                if self.tweets.count == 0 {
                    self.emptyTableMessage(message: NSLocalizedString("EMPTY_TWEET_TABLE", comment: ""))
                }
                self.twitterTable.reloadData()})
        }, failure: {
            spinningAlert.dismiss(animated: false, completion: {
                self.alertHelper.showAlertMessage(alertMessage: NSLocalizedString("TWEETS_NOT_FOUND", comment: ""),
                                                  target: self)
            })
        })
        self.twitterTable.tableFooterView = UIView()
    }
    
    /// Cria uma mensagem amigavel quando nao existem tweets para um usuario
    ///
    /// - Parameters:
    ///     - message: mensagem que sera exibida
    func emptyTableMessage(message:String) {
        let rect = CGRect(origin: CGPoint(x: 0,y :0), size: CGSize(width: self.view.bounds.size.width, height: self.view.bounds.size.height))
        let messageLabel = UILabel(frame: rect)
        messageLabel.text = message
        messageLabel.textColor = UIColor.black
        messageLabel.numberOfLines = 0;
        messageLabel.textAlignment = .center;
        messageLabel.font = UIFont(name: "TrebuchetMS", size: 15)
        messageLabel.sizeToFit()
        
        self.twitterTable.backgroundView = messageLabel;
        self.twitterTable.separatorStyle = .none;
    }
}

// MARK: - TableView
extension ListTweetsViewController: UITableViewDataSource, UITableViewDelegate{
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.tweets.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: Constants.Identifier.TweetCell) as? TweetTableViewCell
        cell?.contentTweet.text = tweets[indexPath.row]
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        listTweetsViewModel.getSentimentalAnalysisFrom(tweet: tweets[indexPath.row], success: {sentiment in
            UIView.animate(withDuration: 1.0, animations: {
                self.viewHeightConstraint.constant = 100
                self.emojiLabel.text = sentiment.emoji
                self.view.backgroundColor = sentiment.color
            }, completion: {done in
                UIView.animate(withDuration: 1.0, animations: {
                    self.viewHeightConstraint.constant = 0
                    self.emojiLabel.text = ""
                    self.view.backgroundColor = UIColor.white
                })
            })
        })
    }


}


