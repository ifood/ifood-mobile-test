//
//  MoodMock.swift
//  twitter-moodTests
//
//  Created by Dennis Merli on 7/11/19.
//  Copyright © 2019 Dennis Merli. All rights reserved.
//

import Foundation

class MoodMock {
    
    class func loadMoodMockHappy() -> Data {
        do {
            if let happyMood = Bundle(for: MoodMock.self).url(forResource: "mood_happy", withExtension: "json") {
                let data = try Data(contentsOf: happyMood)
                return data
            } else {
                return Data()
            }
        } catch {
            return Data()
        }
    }
    
    class func loadMoodMockNeutral() -> Data {
        do {
            if let neutralMood = Bundle(for: MoodMock.self).url(forResource: "mood_neutral", withExtension: "json") {
                let data = try Data(contentsOf: neutralMood)
                return data
            } else {
                return Data()
            }
        } catch {
            return Data()
        }
    }
}

