import SwiftUI

@main
struct iOSApp: App {
    init() {
        AppModule()
        
        AuthNetworkModule()
        AuthRepositoryModule()
        
        CarbonLocalModule()
        CarbonNetworkModule()
        CarbonRepositoryModule()
        
        ItemNetworkModule()
        ItemRepositoryModule()
        
        MessageLocalModule()
        MessageNetworkModule()
        MessageRepositoryModule()
        
        ReviewNetworkModule()
        ReviewRepositoryModule()
        
        StoreNetworkModule()
        StoreRepositoryModule()
        
        UserNetworkModule()
        UserRepositoryModule()
        
        AuthUseCasesModule()
        CarbonUseCasesModule()
        ItemUseCasesModule()
        MessageUseCasesModule()
        ReviewUseCasesModule()
        StoreUseCasesModule()
        UserUseCasesModule()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
