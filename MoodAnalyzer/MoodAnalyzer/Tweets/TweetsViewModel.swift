//
//  TweetsViewModel.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 19/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import UIKit

class TweetsViewModel: NSObject {
    
    
    
    // MARK: - Setup
    func setupView(nameLabel: UILabel, bioLabel: UILabel, profileImage: UIImageView, user: User) {
        nameLabel.text = user.name
        bioLabel.text = user.bio
        
        profileImage.layer.cornerRadius = profileImage.frame.height/2
        profileImage.clipsToBounds = true
        
        // Get image from URL
        if let url = URL(string: user.profileImageURL.replacingOccurrences(of: "_normal", with: "")) {
            profileImage.downloaded(from: url)
        }
    }
    
    // MARK: - Sentiment
    func sentimentAnalysis(cell: TweetTableViewCell) -> Mood {
        
        if let tweet = cell.tweet {
            let sentimentAnalyzer = Sentimently()
            
            let sentimentResults = sentimentAnalyzer.score(tweet.text)
            
            // Positive
            if sentimentResults.score >= 2 {
                cell.setWithMood(mood: .happy)
                return .happy
                
                // Neutral
            } else if sentimentResults.score <= 2 && sentimentResults.score >= -2 {
                cell.setWithMood(mood: .neutral)
                return .neutral
                
                // Negative
            } else {
                cell.setWithMood(mood: .sad)
                return .sad
            }
        }
        return .neutral
    }
    
    // MARK: - Table View
    func tableView(_ tableView: UITableView, didUnhighlightRowAt indexPath: IndexPath) {
        if let cell = tableView.cellForRow(at: indexPath) as? TweetTableViewCell {
            UIView.animate(withDuration: 0.3) {
                cell.contentView.transform = CGAffineTransform(scaleX: 1.0, y: 1.0)
                cell.dateLabel.transform = CGAffineTransform(scaleX: 1.0, y: 1.0)
                cell.moodLabel.transform = CGAffineTransform(scaleX: 1.0, y: 1.0)
                cell.tweetTextLabel.transform = CGAffineTransform(scaleX: 1.0, y: 1.0)
            }
        }
    }
    
    func tableView(_ tableView: UITableView, didHighlightRowAt indexPath: IndexPath) {
        if let cell = tableView.cellForRow(at: indexPath) as? TweetTableViewCell {
            UIView.animate(withDuration: 0.3) {
                cell.contentView.transform = CGAffineTransform(scaleX: 1.05, y: 1.05)
                cell.dateLabel.transform = CGAffineTransform(scaleX: 1.05, y: 1.05)
                cell.moodLabel.transform = CGAffineTransform(scaleX: 1.05, y: 1.05)
                cell.tweetTextLabel.transform = CGAffineTransform(scaleX: 1.05, y: 1.05)
            }
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath, tweets: [Tweet]) -> Mood {
        
        if let cell = tableView.cellForRow(at: indexPath) as? TweetTableViewCell {
            
            // MARK - Animation
            UIView.animate(withDuration: 0.3, delay: 0.0, options: .curveLinear, animations: {
                cell.contentView.transform = CGAffineTransform(scaleX: 1.05, y: 1.05)
                cell.dateLabel.transform = CGAffineTransform(scaleX: 0.95, y: 0.95)
                cell.moodLabel.transform = CGAffineTransform(scaleX: 0.95, y: 0.95)
                cell.tweetTextLabel.transform = CGAffineTransform(scaleX: 0.95, y: 0.95)
            }) { (finished) in
                UIView.animate(withDuration: 0.3, animations: {
                    cell.contentView.transform = CGAffineTransform.identity
                    cell.dateLabel.transform = CGAffineTransform.identity
                    cell.moodLabel.transform = CGAffineTransform.identity
                    cell.tweetTextLabel.transform = CGAffineTransform.identity
                })
            }
            
            // MARK: - Sentiment Analysis
            return self.sentimentAnalysis(cell: cell)
        }
        return .neutral
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath, tweets: [Tweet]) -> UITableViewCell {
        
        if let cell = tableView.dequeueReusableCell(withIdentifier: String(describing: TweetTableViewCell.self)) as? TweetTableViewCell {
            
            cell.tweet = tweets[indexPath.row]
            
            cell.setUI()
            
            return cell
        }
        
        return UITableViewCell()
    }
    
}
