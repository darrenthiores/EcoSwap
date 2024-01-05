//
//  IosHomeViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension HomeScreen {
    @MainActor class IosHomeViewModel: ObservableObject {
        private let viewModel: HomeViewModel
        
        @Published var state: HomeState = HomeState(
            recommendations: [],
            recommendationError: nil,
            isRecommendationLoading: false,
            nearby: [],
            nearbyError: nil,
            isNearbyLoading: false,
            stores: [],
            storeError: nil,
            isStoreLoading: false,
            viewAll: false
        )
        @Published var iosState: IosHomeState = IosHomeState()
        
        @Published var searchText: String = ""
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getItems: GetItems
            @Inject var getStores: GetStores
            
            self.viewModel = HomeViewModel(
                getItems: getItems,
                getStores: getStores,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: HomeEvent) {
            viewModel.onEvent(event: event)
        }
        
        func onIosEvent(event: IosHomeEvent) {
            switch event {
            case .SubmitSearch:
                iosState.selectedCategory = nil
                
                if !searchText.isEmpty {
                    iosState.showSearch = true
                }
            case .OnSelectCategory(category: let category):
                iosState.selectedCategory = category
                iosState.showSearch = true
            case .OnAddClick:
                iosState.showAdd = true
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
