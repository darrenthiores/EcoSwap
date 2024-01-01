import Foundation
import shared

class IosTokenPreferences: TokenPreferences {
    let keyChain = KeychainSwift.shared
    
    func getAccessToken() -> String {
        return keyChain.get("accessToken") ?? ""
    }
    
    func getRefreshToken() -> String {
        return keyChain.get("refreshToken") ?? ""
    }
    
    func resetToken() {
        keyChain.set("", forKey: "accessToken")
        keyChain.set("", forKey: "refreshToken")
    }
    
    func setToken(accessToken: String, refreshToken: String) {
        keyChain.set(accessToken, forKey: "accessToken")
        keyChain.set(refreshToken, forKey: "refreshToken")
    }
}

