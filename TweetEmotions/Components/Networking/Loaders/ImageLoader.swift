import UIKit

enum ImageLoadingError: Error {
    case noCachesDirectory
    case couldNotCreateDirectoryForImages
    case corruptedImageFile
    case couldNotWriteImage
}

protocol ImageLoading {
    func loadImage(at url: URL, completion: @escaping (Result<UIImage>) -> Void)
    func cancel()
}

final class ImageLoader: ImageLoading {
    
    // MARK: Init/Deinit
    
    init(networkClient: Networking, fileManager: FileManaging = FileManager.default) {
        self.networkClient = networkClient
        self.fileManager = fileManager
    }
    
    // MARK: ImageLoading methods
    
    func loadImage(at url: URL, completion: @escaping (Result<UIImage>) -> Void) {
        queue.async {
            if let image = self.cache.object(forKey: url.lastPathComponent as NSString) {
                DispatchQueue.main.async {
                    let result: Result<UIImage> = Result.success(image)
                    completion(result)
                }
                
            } else {
                self.loadLocalOrRemoteImage(at: url, completion: completion)
            }
        }
    }
    
    func cancel() {
        networkClient.cancelInflightRequest()
    }
    
    // MARK: Private properties
    
    private let networkClient: Networking
    private let fileManager: FileManaging
    private let queue = DispatchQueue(label: "com.rafaelcsa.TweetEmotions.ImageLoader", qos: .userInitiated)
    private let cache = NSCache<NSString, UIImage>()
    private let imagesDirectoryPath = "images"
    
    // MARK: Private methods
    
    private func loadLocalOrRemoteImage(at url: URL, completion: @escaping (Result<UIImage>) -> Void) {
        do {
            let imageDirectoryUrl = try directoryUrl()
            let imageLocalUrl = imageDirectoryUrl.appendingPathComponent(url.lastPathComponent)
            
            if fileManager.fileExists(atPath: imageLocalUrl.path) {
                loadLocalImage(from: imageLocalUrl, completion: completion)
                
            } else {
                loadRemoteImage(at: url, writeTo: imageLocalUrl, completion: completion)
            }
            
        } catch {
            let result: Result<UIImage> = Result.failure(error)
            completion(result)
        }
    }
    
    private func directoryUrl() throws -> URL {
        guard let cachesDirectoryPath = NSSearchPathForDirectoriesInDomains(.cachesDirectory, .userDomainMask, true).first else {
            throw ImageLoadingError.noCachesDirectory
        }
        
        let cachesDirectoryUrl = URL(fileURLWithPath: cachesDirectoryPath)
        let imagesDirectory = cachesDirectoryUrl.appendingPathComponent(imagesDirectoryPath)
        
        do {
            try fileManager.createDirectory(at: imagesDirectory, withIntermediateDirectories: true, attributes: nil)
        } catch {
            throw ImageLoadingError.couldNotCreateDirectoryForImages
        }
        
        return imagesDirectory
    }
    
    private func loadLocalImage(from fileUrl: URL, completion: @escaping (Result<UIImage>) -> Void) {
        do {
            let data = try Data(contentsOf: fileUrl)
            
            guard let image = UIImage(data: data) else {
                DispatchQueue.main.async {
                    let error = ImageLoadingError.corruptedImageFile
                    let result: Result<UIImage> = Result.failure(error)
                    completion(result)
                }
                
                return
            }
            
            cache.setObject(image, forKey: fileUrl.lastPathComponent as NSString)
            
            DispatchQueue.main.async {
                let result: Result<UIImage> = Result.success(image)
                completion(result)
            }
        } catch {
            DispatchQueue.main.async {
                let result: Result<UIImage> = Result.failure(error)
                completion(result)
            }
        }
    }
    
    private func loadRemoteImage(at url: URL, writeTo localUrl: URL, completion: @escaping (Result<UIImage>) -> Void) {
        let request = URLRequest(url: url, cachePolicy: .useProtocolCachePolicy, timeoutInterval: 20)
        
        networkClient.load(withRequest: request) { result in
            switch result {
            case .success(let data):
                do {
                    try data.write(to: localUrl, options: .atomic)
                    
                } catch {
                    let error = ImageLoadingError.couldNotWriteImage
                    let result: Result<UIImage> = Result.failure(error)
                    completion(result)
                    return
                }
                
                guard let image = UIImage(data: data) else {
                    let error = ImageLoadingError.corruptedImageFile
                    let result: Result<UIImage> = Result.failure(error)
                    completion(result)
                    return
                }
                
                self.cache.setObject(image, forKey: url.lastPathComponent as NSString)
                let result: Result<UIImage> = Result.success(image)
                completion(result)
                
            case .failure(let error):
                let result: Result<UIImage> = Result.failure(error)
                completion(result)
            }
        }
    }
}
