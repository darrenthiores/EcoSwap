//
//  SearchScreen.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchScreen: View {
    var searchText: String = ""
    var selectedCategory: ItemCategory? = nil
    
    @Environment(\.dismiss) private var dismiss
    @StateObject private var viewModel = IosSearchViewModel()
    @State private var currentTab: SearchTab = .items
    
    var body: some View {
        GeometryReader { geo in
            VStack(spacing: 0) {
                SearchHeader(
                    searchText: $viewModel.searchText,
                    selectedCategory: viewModel.state.category,
                    currentTab: currentTab,
                    onSearch: {
                        viewModel.onIosEvent(event: .OnSearch)
                    },
                    onSelectCategory: { category in
                        viewModel.onIosEvent(event: .OnSelectCategory(category: category))
                    },
                    onSelectTab: { tab in
                        currentTab = tab
                    },
                    onBackClick: {
                        dismiss()
                    }
                )
                
                TabView(selection: $currentTab) {
                    if viewModel.state.items.items.isEmpty {
                        Text("Items not found")
                            .tag(SearchTab.items)
                    } else {
                        ItemList(
                            items: viewModel.state.items.items as? [Item] ?? [],
                            geo: geo,
                            onAppear: {
                                if !viewModel.state.items.endReached && !viewModel.state.items.isLoading {
                                    viewModel.onEvent(event: .LoadItemNextPage())
                                }
                            },
                            spaceToTop: 16,
                            spaceToBottom: 16
                        )
                        .tag(SearchTab.items)
                    }
                    
                    if viewModel.state.stores.items.isEmpty {
                        Text("Stores not found")
                            .tag(SearchTab.stores)
                    } else {
                        StoreList(
                            stores: viewModel.state.stores.items as? [Store] ?? [],
                            geo: geo,
                            onAppear: {
                                if !viewModel.state.stores.endReached && !viewModel.state.stores.isLoading {
                                    viewModel.onEvent(event: .LoadStoreNextPage())
                                }
                            },
                            spaceToTop: 16,
                            spaceToBottom: 16
                        )
                        .tag(SearchTab.stores)
                    }
                }
                .tabViewStyle(.page(indexDisplayMode: .never))
            }
        }
        .navigationBarBackButtonHidden()
        .toolbar(.hidden, for: .tabBar)
        .onAppear {
            viewModel.startObserving()
            
            if !searchText.isEmpty {
                viewModel.onIosEvent(
                    event: .OnUpdateAndSearch(searchText: searchText)
                )
            }
            
            if selectedCategory != nil {
                viewModel.onIosEvent(
                    event: .OnSelectCategory(category: selectedCategory)
                )
            }
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

#Preview {
    SearchScreen()
}
