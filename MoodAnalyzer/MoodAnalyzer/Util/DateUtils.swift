//
//  DateUtils.swift
//  MoodAnalyzer
//
//  Created by Carlos Henrique Salvador on 19/12/18.
//  Copyright © 2018 Carlos Henrique Salvador. All rights reserved.
//

import Foundation

struct DateUtils {
    
    static func parseTwitterDate(twitterDate:String, outputDateFormat:String)->String?{
        let formatter = DateFormatter()
        formatter.dateFormat = "EEE MMM dd HH:mm:ss Z yyyy"
        
        let indate = formatter.date(from: twitterDate)
        let outputFormatter = DateFormatter()
        outputFormatter.dateFormat = "hh:mm a - dd MMM YYYY"
        var outputDate:String?
        if let d = indate {
            outputDate = outputFormatter.string(from: d)
        }
        return outputDate;
    }
    
}
