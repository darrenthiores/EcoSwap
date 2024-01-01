import Foundation
import shared

class AppModule {
    init() {
        @Provider var driver = DatabaseDriverFactory().create()
        @Provider var appDatabase = DatabaseFactory().createDatabase(driver: driver)
        @Provider var dispatchers: DispatchersProvider = StandardDispatchers()
        @Provider var tokenPrefrences: TokenPreferences = IosTokenPreferences()
        @Provider var client = HttpClientFactory().create(
            tokenPreferences: tokenPrefrences
        )
    }
}
