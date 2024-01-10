//
//  IosSearchViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension SearchScreen {
    @MainActor class IosSearchViewModel: ObservableObject {
        private let viewModel: SearchViewModel
        
        @Published var state: SearchState = SearchState(
            searchText: "",
            category: nil,
            items: PagingState(
                isLoading: false,
                items: [],
                error: nil,
                endReached: false,
                page: 1
            ),
            stores: PagingState(
                isLoading: false,
                items: [],
                error: nil,
                endReached: false,
                page: 1
            )
        )
        @Published var searchText: String = ""
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var searchItems: SearchItems
            @Inject var searchStores: SearchStores
            
            self.viewModel = SearchViewModel(
                searchItems: searchItems,
                searchStores: searchStores,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: SearchEvent) {
            viewModel.onEvent(event: event)
        }
        
        func onIosEvent(event: IosSearchEvent) {
            switch event {
            case .OnSearch:
                viewModel.onEvent(
                    event: .OnSearch(text: searchText)
                )
            case .OnUpdateAndSearch(searchText: let searchText):
                self.searchText = searchText
                
                viewModel.onEvent(
                    event: .OnSearch(text: searchText)
                )
            case .OnSelectCategory(category: let category):
                viewModel.onEvent(
                    event: .OnSelectCategory(
                        category: category
                    )
                )
            }
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe { state in
                if let state = state {
                    self.state = state
                }
            }
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
