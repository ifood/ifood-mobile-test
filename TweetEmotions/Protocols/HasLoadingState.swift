import Foundation

enum LoadingState {
    case idle
    case isLoading
    case hasError(Error)
}

protocol HasLoadingState {
    var loadingState: LoadingState { get }
}
