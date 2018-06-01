// Inspired by https://talk.objc.io/episodes/S01E01-tiny-networking-library

import UIKit

enum NetworkError: Error {
    case networkError(Error)
    case httpError(Error)
    case unexpectedResponseProtocol
    case noData
}

protocol Networking {
    func load(withRequest request: URLRequest, completion: @escaping (Result<Data>) -> Void)
    func cancelInflightRequest()
}

final class NetworkClient: Networking {
    // MARK: Init/Deinit

    init(configuration: URLSessionConfiguration = URLSessionConfiguration.default, networkActivityIndicatorController: NetworkActivityIndicatorControlling) {
        self.configuration = configuration
        self.networkActivityIndicatorController = networkActivityIndicatorController
        self.session = URLSession(configuration: configuration)
    }

    deinit {
        cancelInflightRequest()
    }

    // MARK: Public methods

    func load(withRequest request: URLRequest, completion: @escaping (Result<Data>) -> Void) {
        dataTask = session.dataTask(with: request) { [weak self] data, response, error in
            guard let strongSelf = self else { return }
            strongSelf.networkActivityIndicatorController.decrementNetworkActivity()
            
            if let error = error {
                DispatchQueue.main.async {
                    let networkError = NetworkError.networkError(error)
                    let result: Result<Data> = Result.failure(networkError)
                    completion(result)
                }

                return
            }
            
            guard let httpResponse = response as? HTTPURLResponse,
                let status = httpResponse.status else {
                DispatchQueue.main.async {
                    let result: Result<Data> = Result.failure(NetworkError.unexpectedResponseProtocol)
                    completion(result)
                }
                
                return
            }
            
            guard case .success = status.responseType else {
                DispatchQueue.main.async {
                    let error = NetworkError.httpError(status)
                    let result: Result<Data> = Result.failure(error)
                    completion(result)
                }
                
                return
            }

            guard let data = data else {
                DispatchQueue.main.async {
                    let result: Result<Data> = Result.failure(NetworkError.noData)
                    completion(result)
                }

                return
            }

            DispatchQueue.main.async {
                let result: Result<Data> = Result.success(data)
                completion(result)
            }
        }

        networkActivityIndicatorController.incrementNetworkActivity()
        dataTask?.resume()
    }

    func cancelInflightRequest() {
        networkActivityIndicatorController.decrementNetworkActivity()
        dataTask?.cancel()
    }

    // MARK: Private properties

    private let configuration: URLSessionConfiguration
    private let networkActivityIndicatorController: NetworkActivityIndicatorControlling
    private let session: URLSession
    private var dataTask: URLSessionDataTask?
}
