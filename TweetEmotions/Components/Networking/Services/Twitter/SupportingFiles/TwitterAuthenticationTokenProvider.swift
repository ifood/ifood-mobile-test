import KeychainAccess

protocol TwitterAuthenticationTokenProviding: AnyObject {
    var authenticationToken: AuthenticationToken? { get set }
}

final class TwitterAuthenticationTokenProvider: TwitterAuthenticationTokenProviding {
    var authenticationToken: AuthenticationToken? {
        get {
            guard let accessToken = keychain["authentication_access_token"] else { return nil }
            return AuthenticationToken(accessToken: accessToken, tokenType: .bearer)
        }
        
        set {
            keychain["authentication_access_token"] = newValue?.accessToken
        }
    }
    
    private let keychain = Keychain(service: "com.rafaelcsa.TweetEmotions.keychain")
}
