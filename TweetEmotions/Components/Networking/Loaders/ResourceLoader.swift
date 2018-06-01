import Foundation

struct Resource<T> {
    let request: URLRequest
    let parse: (Data) -> Result<T>
}

protocol ResourceLoading {
    func load<T>(_ resource: Resource<T>, completion: @escaping (Result<T>) -> Void)
    func cancel()
}

final class ResourceLoader: ResourceLoading {
    // MARK: Init/Deinit
    
    init(networkClient: Networking) {
        self.networkClient = networkClient
    }
    
    // MARK: Public methods
    
    func load<T>(_ resource: Resource<T>, completion: @escaping (Result<T>) -> Void) {
        networkClient.load(withRequest: resource.request) { result in
            switch result {
            case .success(let data):
                let result = resource.parse(data)
                completion(result)
                
            case .failure(let error):
                let result: Result<T> = Result.failure(error)
                completion(result)
            }
        }
    }
    
    func cancel() {
        networkClient.cancelInflightRequest()
    }
    
    // MARK: Private properties
    
    private let networkClient: Networking
}
