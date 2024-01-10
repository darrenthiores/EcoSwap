//
//  ProfileScreen.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProfileScreen: View {
    @Environment(\.dismiss) private var dismiss
    
    private static let topId = "profileTopSection"
    @StateObject var viewModel = IosProfileViewModel()
    @State private var currentTab: ProfileTab = .myitems
    
    var body: some View {
        NavigationStack {
            GeometryReader { geo in
                ScrollViewReader { reader in
                    ScrollView(showsIndicators: false) {
                        ProfileTopSection(
                            user: viewModel.state.user
                        )
                        .id(Self.topId)
                        
                        LazyVStack(spacing: 0, pinnedViews: .sectionHeaders) {
                            Section {
                                switch currentTab {
                                case .myitems:
                                    if viewModel.state.items.items.isEmpty {
                                        TextEmpty(
                                            title: "Nothing posted",
                                            description: "All the items that you posted will be shown here."
                                        )
                                        .background(Color.clear)
                                        .contentShape(Rectangle())
                                    } else {
                                        ItemGrid(
                                            items: viewModel.state.items.items as? [Item] ?? [],
                                            geo: geo,
                                            onAppear: {
                                                if !viewModel.state.items.endReached && !viewModel.state.items.isLoading {
                                                    viewModel.onEvent(
                                                        event: .LoadItemNextPage()
                                                    )
                                                }
                                            },
                                            spaceToTop: 16,
                                            spaceToBottom: 16
                                        )
                                    }
                                case .reviews:
                                    if viewModel.state.reviews.items.isEmpty {
                                        TextEmpty(
                                            title: "Nothing reviewed",
                                            description: "All the reviews that you posted will be shown here."
                                        )
                                        .background(Color.clear)
                                        .contentShape(Rectangle())
                                    } else {
                                        LazyReview(
                                            reviews: viewModel.state.reviews.items as? [Review] ?? [],
                                            geo: geo,
                                            onAppear: {
                                                if !viewModel.state.reviews.endReached && !viewModel.state.reviews.isLoading {
                                                    viewModel.onEvent(
                                                        event: .LoadReviewNextPage()
                                                    )
                                                }
                                            },
                                            spaceToTop: 16,
                                            spaceToBottom: 16
                                        )
                                    }
                                default:
                                    Text("Error")
                                }
                            } header: {
                                VStack(spacing: 0) {
                                    HStack(spacing: 0) {
                                        ForEach(ProfileTab.entries, id: \.name) { tab in
                                            TabButton(
                                                title: tab.title,
                                                isSelected: currentTab == tab,
                                                onClick: {
                                                    currentTab = tab
                                                }
                                            )
                                        }
                                    }
                                    
                                    Divider()
                                }
                                .background(
                                    Color.Background
                                )
                                .contentShape(Rectangle())
                            }
                            .contentShape(Rectangle())
                            .gesture(
                                DragGesture()
                                    .onEnded { value in
                                        
                                        let pi = Double.pi
                                        let direction = atan2(value.translation.width, value.translation.height)
                                        
                                        // right
                                        if (pi/4..<pi*3/4).contains(direction) {
                                            if currentTab == .reviews {
                                                currentTab = .myitems
                                            }
                                        }
                                        
                                        // left
                                        if (-pi*3/4..<(-pi/4)).contains(direction) {
                                            if currentTab == .myitems {
                                                currentTab = .reviews
                                            }
                                        }
                                    }
                            )
                            .onChange(of: currentTab) { _ in
                                withAnimation {  // add animation for scroll to top
                                    reader.scrollTo(Self.topId, anchor: .top) // scroll
                                }
                            }
                        }
                    }
                }
            }
            .navigationBarBackButtonHidden(true)
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .principal) {
                    SettingHeader(
                        title: "My Profile",
                        onBackClick: {
                            dismiss()
                        },
                        onSettingClick: {  }
                    )
                    .padding(16)
                }
            }
            .onAppear {
                viewModel.startObserving()
            }
            .onDisappear {
                viewModel.dispose()
            }
        }
    }
}

#Preview {
    ProfileScreen()
}
