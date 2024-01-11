import SwiftUI
import shared

struct ContentView: View {
    @AppStorage("showBoarding") private var showBoarding: Bool = true
    @AppStorage("isLogin") private var isLogin: Bool = false
    @State private var selectedTab: TopLevelDestination = .Home
    
    let appearance: UITabBarAppearance = UITabBarAppearance()
    init() {
        if #available(iOS 15.0, *) {
            UITabBar.appearance().scrollEdgeAppearance = appearance
        }
    }
    
	var body: some View {
        if isLogin {
            TabView(selection: $selectedTab) {
                HomeScreen()
                    .tabItem {
                        Label(
                            "Home",
                            systemImage: selectedTab == .Home ? "house.fill" : "house"
                        )
                        .labelStyle(.iconOnly)
                    }
                    .tag(TopLevelDestination.Home)
                
                SustainabilityScreen()
                    .tabItem {
                        Label(
                            "Sustainability",
                            systemImage: selectedTab == .Sustainability ? "chart.bar.fill" : "chart.bar"
                        )
                        .labelStyle(.iconOnly)
                    }
                    .tag(TopLevelDestination.Sustainability)
                
                InboxScreen()
                    .tabItem {
                        Label(
                            "Message",
                            systemImage: selectedTab == .Message ? "envelope.fill" : "envelope"
                        )
                        .labelStyle(.iconOnly)
                    }
                    .tag(TopLevelDestination.Message)
                
                ProfileScreen()
                    .tabItem {
                        Label(
                            "Profile",
                            systemImage: selectedTab == .Profile ? "person.crop.circle.fill" : "person.crop.circle"
                        )
                        .labelStyle(.iconOnly)
                    }
                    .tag(TopLevelDestination.Profile)
            }
            .accentColor(.Primary)
        } else {
            if showBoarding {
                BoardingScreen()
            } else {
                LoginScreen()
            }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
