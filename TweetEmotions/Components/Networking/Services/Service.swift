import Foundation

protocol Service {
    associatedtype Loadable: Decodable
    
    var url: URL { get }
    var endpoint: String { get }
    var keyDecodingStrategy: JSONDecoder.KeyDecodingStrategy { get }
    var resourceLoader: ResourceLoading { get }
    
    func resource(with request: URLRequest) -> Resource<Loadable>
    func cancel()
}

extension Service {
    var keyDecodingStrategy: JSONDecoder.KeyDecodingStrategy {
        return .convertFromSnakeCase
    }
    
    func resource(with request: URLRequest) -> Resource<Loadable> {
        let resource = Resource(request: request) { data -> Result<Loadable> in
            do {
                let decoder = JSONDecoder()
                decoder.keyDecodingStrategy = self.keyDecodingStrategy
                
                let response = try decoder.decode(Loadable.self, from: data)
                return Result.success(response)
                
            } catch {
                return Result.failure(error)
            }
        }
        
        return resource
    }
    
    func cancel() {
        resourceLoader.cancel()
    }
}
