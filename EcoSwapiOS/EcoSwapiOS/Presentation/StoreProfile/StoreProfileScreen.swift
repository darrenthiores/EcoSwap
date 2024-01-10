//
//  StoreProfileScreen.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 07/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct StoreProfileScreen: View {
    let storeId: String
    
    @Environment(\.dismiss) private var dismiss
    
    private static let topId = "storeProfileTopSection"
    @StateObject var viewModel = IosStoreProfileViewModel()
    @State private var currentTab: StoreProfileTab = .items
    @State private var showAddReviewSheet: Bool = false
    
    var body: some View {
        GeometryReader { geo in
            ScrollViewReader { reader in
                ScrollView(showsIndicators: false) {
                    StoreProfileTopSection(
                        store: viewModel.state.store
                    )
                    .id(Self.topId)
                    
                    LazyVStack(spacing: 0, pinnedViews: .sectionHeaders) {
                        Section {
                            switch currentTab {
                            case .items:
                                if viewModel.state.items.items.isEmpty {
                                    TextEmpty(
                                        title: "Nothing posted",
                                        description: "All the items that you posted will be shown here."
                                    )
                                    .background(Color.clear)
                                    .contentShape(Rectangle())
                                } else {
                                    StoreItemGrid(
                                        items: viewModel.state.items.items as? [StoreItem] ?? [],
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
                                    LazyStoreReview(
                                        reviews: viewModel.state.reviews.items as? [StoreReview] ?? [],
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
                            case .location:
                                TextEmpty(
                                    title: "Not Available",
                                    description: "This feature is currently not available"
                                )
                                .background(Color.clear)
                                .contentShape(Rectangle())
                            default:
                                Text("Error")
                            }
                        } header: {
                            VStack(spacing: 0) {
                                HStack(spacing: 0) {
                                    ForEach(StoreProfileTab.entries, id: \.name) { tab in
                                        TabButton(
                                            title: tab.name,
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
                                            currentTab = .items
                                        }
                                        
                                        if currentTab == .location {
                                            currentTab = .reviews
                                        }
                                    }
                                    
                                    // left
                                    if (-pi*3/4..<(-pi/4)).contains(direction) {
                                        if currentTab == .reviews {
                                            currentTab = .location
                                        }
                                        
                                        if currentTab == .items {
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
        .toolbar(.hidden, for: .tabBar)
        .navigationBarBackButtonHidden(true)
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .principal) {
                SettingHeader(
                    title: "Store",
                    onBackClick: {
                        dismiss()
                    },
                    onSettingClick: {  }
                )
                .padding(16)
            }
            
            ToolbarItem(placement: .bottomBar) {
                ProfileBottomBar(
                    id: storeId,
                    onAddReviewClick: {
                        showAddReviewSheet = true
                    }
                )
            }
        }
        .sheet(isPresented: $showAddReviewSheet) {
            ReviewSheet(
                rating: Int(viewModel.state.rating),
                message: Binding(
                    get: { viewModel.state.message },
                    set: {
                        viewModel.onEvent(
                            event: .OnMessageChange(
                                message: $0
                            )
                        )
                    }
                ),
                isLoading: viewModel.state.isLoading,
                onPickRating: { rating in
                    viewModel.onEvent(
                        event: .OnPickRating(
                            rating: Int32(rating)
                        )
                    )
                },
                onUpload: {
                    viewModel.onEvent(
                        event: .AddReview()
                    )
                },
                reviewFor: .StoreProfile
            )
        }
        .onChange(of: viewModel.state.isSuccess) { success in
            if success {
                showAddReviewSheet = false
                
                viewModel.onEvent(
                    event: .Reset()
                )
            }
        }
        .alert(
            viewModel.state.error ?? "Unknown Error Just Occurred!",
            isPresented: Binding(
                get: {
                    viewModel.state.showError
                },
                set: { show in
                    if !show {
                        viewModel.onEvent(
                            event: .HideError()
                        )
                    }
                }
            ),
            actions: { }
        )
        .onAppear {
            viewModel.startObserving()
            
            viewModel.initialFetch(storeId: storeId)
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

#Preview {
    StoreProfileScreen(
        storeId: "1"
    )
}
