//
//  HomeScreen.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct HomeScreen: View {
    @StateObject private var viewModel = IosHomeViewModel()
    @State private var currentTab: Int = 0
    
    var body: some View {
        NavigationStack {
            GeometryReader { geo in
                VStack(spacing: 0) {
                    HomeHeader(
                        searchText: $viewModel.searchText,
                        onSearch: {
                            viewModel.onIosEvent(
                                event: .SubmitSearch
                            )
                        }
                    )
                    
                    ZStack(alignment: .bottomTrailing) {
                        ScrollView(.vertical, showsIndicators: false) {
                            LazyVStack(spacing: 24) {
                                CategorySection(
                                    currentTab: $currentTab,
                                    viewAll: viewModel.state.viewAll,
                                    onItemClick: { category in
                                        viewModel.onIosEvent(
                                            event: .OnSelectCategory(
                                                category: category
                                            )
                                        )
                                    },
                                    onToggleViewAll: {
                                        viewModel.onEvent(
                                            event: .ToggleViewAll()
                                        )
                                    },
                                    geo: geo
                                )
                                
                                ItemSection(
                                    title: "Recommended for you",
                                    items: viewModel.state.recommendations,
                                    onSeeAllClick: {  },
                                    onAppear: { },
                                    isLoading: viewModel.state.isRecommendationLoading
                                )
                                
                                ItemSection(
                                    title: "Items nearby",
                                    items: viewModel.state.nearby,
                                    onSeeAllClick: {  },
                                    onAppear: { },
                                    isLoading: viewModel.state.isNearbyLoading
                                )
                                
                                StoreSection(
                                    title: "Eco-Friendly Stores for you",
                                    stores: viewModel.state.stores,
                                    onSeeAllClick: {  },
                                    onAppear: {  },
                                    isLoading: viewModel.state.isStoreLoading
                                )
                            }
                            .padding(.vertical, 24)
                        }
                        
                        Button {
                            viewModel.onIosEvent(
                                event: .OnAddClick
                            )
                        } label: {
                            ZStack(alignment: .center) {
                                Image(systemName: "plus")
                                    .resizable()
                                    .scaledToFit()
                                    .frame(
                                        width: 16,
                                        height: 16
                                    )
                                    .foregroundColor(.OnPrimary)
                            }
                            .frame(
                                width: 56,
                                height: 56
                            )
                            .clipShape(Circle())
                            .background(
                                Color.Primary
                                    .clipShape(Circle())
                                    .shadow(radius: 1)
                            )
                        }
                        .padding(24)
                    }
                }
            }
            .onAppear {
                viewModel.startObserving()
            }
            .onDisappear {
                viewModel.dispose()
            }
            .navigationDestination(isPresented: $viewModel.iosState.showAdd) {
                AddItemScreen()
            }
            .navigationDestination(isPresented: $viewModel.iosState.showSearch) {
                SearchScreen(
                    searchText: viewModel.searchText,
                    selectedCategory: viewModel.iosState.selectedCategory
                )
            }
        }
    }
}

#Preview {
    HomeScreen()
}
