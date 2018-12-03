//
//  SearchViewModelTests.swift
//  SentimentAnalysisTests
//
//  Created by Thales Frigo on 03/12/18.
//  Copyright © 2018 Thales Frigo. All rights reserved.
//

import XCTest
@testable import SentimentAnalysis

class SearchViewModelTests: XCTestCase {
    
    func testSearchWithSuccess() {
        let repository = MockSearchRepository(isSuccess: true)
        let viewModel = SearchViewModel(repository: repository)
        
        let expectation = self.expectation(description: "SearchRepository Success")
        viewModel.searchState.onUpdate = { state in
            switch state {
            case .empty:
                XCTAssertTrue(true)
            case .loading:
                XCTAssertTrue(true)
            case .load:
                XCTAssertTrue(true)
                expectation.fulfill()
            default:
                XCTFail("Fail on get search results")
            }
        }
        
        viewModel.search(with: "username")
        waitForExpectations(timeout: Timeout.value, handler: nil)
    }
    
    func testSearchWithFailure() {
        let repository = MockSearchRepository(isSuccess: false)
        let viewModel = SearchViewModel(repository: repository)
        
        let expectation = self.expectation(description: "SearchRepository Success")
        viewModel.searchState.onUpdate = { state in
            switch state {
            case .empty:
                XCTAssertTrue(true)
            case .loading:
                XCTAssertTrue(true)
            case .error:
                XCTAssertTrue(true)
                expectation.fulfill()
            default:
                XCTFail("Error dont called")
            }
        }
        
        viewModel.search(with: "username")
        waitForExpectations(timeout: Timeout.value, handler: nil)
    }
    
    func testShouldLoadMore() {
        let repository = MockSearchRepository(isSuccess: true)
        let viewModel = SearchViewModel(repository: repository)
        viewModel.searchInput.value = "thalesfrigo"
        viewModel.loadingState.value = false
        let tweet = TweetFactory.makeTweet()
        viewModel.maxId.value = tweet.id
        viewModel.hasMoreData.value = true
        viewModel.dataSource.value = [tweet]
        
        XCTAssertTrue(viewModel.shouldLoadMore())
    }
    
    func testShouldNotLoadMore() {
        let repository = MockSearchRepository(isSuccess: false)
        let viewModel = SearchViewModel(repository: repository)
        viewModel.searchInput.value = "thalesfrigo"
        viewModel.loadingState.value = true
        viewModel.maxId.value = TweetFactory.makeTweet().id
        viewModel.hasMoreData.value = true
        viewModel.dataSource.value = []
        
        XCTAssertFalse(viewModel.shouldLoadMore())
    }
    
    func testLoadMoreWithSuccess() {
        let repository = MockSearchRepository(isSuccess: true)
        let viewModel = SearchViewModel(repository: repository)
        viewModel.searchInput.value = "thalesfrigo"
        viewModel.loadingState.value = false
        let tweet = TweetFactory.makeLoadMoreTweet()
        viewModel.maxId.value = tweet.id
        viewModel.hasMoreData.value = true
        viewModel.dataSource.value = [tweet]
        
        var responseTweets = TweetFactory.makeArray()
        
        var skipAttribution = true
        let expectation = self.expectation(description: "Load more tweets with success")
        viewModel.dataSource.onUpdate = { value in
            // First trigger of attribution
            if skipAttribution {
                skipAttribution = false
                return
            }
            
            XCTAssertEqual(value.count, responseTweets.count + 1)
            // Make equal datasource
            responseTweets.insert(tweet, at: 0)
            XCTAssertEqual(value, responseTweets)
            
            expectation.fulfill()
        }
        
        viewModel.loadMore()
        waitForExpectations(timeout: Timeout.value, handler: nil)
    }
    
    func testLoadMoreWithFinish() {
        let repository = MockSearchRepository(isSuccess: true)
        let viewModel = SearchViewModel(repository: repository)
        viewModel.searchInput.value = "thalesfrigo"
        viewModel.loadingState.value = false
        let tweet = TweetFactory.makeTweet()
        viewModel.maxId.value = tweet.id
        viewModel.hasMoreData.value = true
        viewModel.dataSource.value = [tweet]
        
        var skipAttribution = true
        let expectation = self.expectation(description: "Load more with empty results")
        viewModel.hasMoreData.onUpdate = { [weak viewModel] value in
            // First trigger of attribution
            if skipAttribution {
                skipAttribution = false
                return
            }
            
            XCTAssertEqual(value, false)
            XCTAssertEqual(viewModel?.dataSource.value.count, 1)
            
            
            expectation.fulfill()
        }
        
        viewModel.loadMore()
        waitForExpectations(timeout: Timeout.value, handler: nil)
    }
    
    func testLoadMoreWithFailure() {
        let repository = MockSearchRepository(isSuccess: false)
        let viewModel = SearchViewModel(repository: repository)
        viewModel.searchInput.value = "thalesfrigo"
        viewModel.loadingState.value = false
        let tweet = TweetFactory.makeTweet()
        viewModel.maxId.value = tweet.id
        viewModel.hasMoreData.value = true
        viewModel.dataSource.value = [tweet]
        
        var skipAttribution = true
        let expectation = self.expectation(description: "Load more with failure")
        viewModel.hasMoreData.onUpdate = { [weak viewModel] value in
            // First trigger of attribution
            if skipAttribution {
                skipAttribution = false
                return
            }
            
            XCTAssertEqual(value, false)
            XCTAssertNil(viewModel?.maxId.value)
            expectation.fulfill()
        }
        
        viewModel.loadMore()
        waitForExpectations(timeout: Timeout.value, handler: nil)
    }
    
    func testStartProcess() {
        let repository = MockSearchRepository(isSuccess: true)
        let viewModel = SearchViewModel(repository: repository)
        let delegate = MockSearchViewModelDelegate()
        viewModel.delegate = delegate
        viewModel.dataSource.value = TweetFactory.makeArray()
        
        viewModel.process(with: 0)
        
        XCTAssertTrue(delegate.hasCalledProcess)
    }
    
    func testDataSourceMethods() {
        let repository = MockSearchRepository(isSuccess: true)
        let viewModel = SearchViewModel(repository: repository)
        let mockedArray = TweetFactory.makeArray()
        viewModel.dataSource.value = mockedArray
        
        XCTAssertEqual(viewModel.numberOfItems, mockedArray.count)
        XCTAssertEqual(viewModel.item(at: 0), mockedArray[0])
    }
    
    func testValidTwitterUsername() {
        let repository = MockSearchRepository(isSuccess: true)
        let viewModel = SearchViewModel(repository: repository)
        let validUsername = "thalesfrigo_"
        
        XCTAssertTrue(viewModel.shouldChangeText(with: validUsername))
    }
    
    func testInvalidTwitterUsername() {
        let repository = MockSearchRepository(isSuccess: true)
        let viewModel = SearchViewModel(repository: repository)
        let invalidUsername = "#thales.frigo"
        
        XCTAssertFalse(viewModel.shouldChangeText(with: invalidUsername))
    }
}

class MockSearchViewModelDelegate: SearchViewModelDelegate {
    
    var hasCalledProcess = false
    
    func didStartProcessTweet(with tweet: Tweet) {
        hasCalledProcess = true
    }
}
