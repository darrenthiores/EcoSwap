//
//  IosStoreProfileViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 07/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension StoreProfileScreen {
    @MainActor class IosStoreProfileViewModel: ObservableObject {
        private let viewModel: StoreProfileViewModel
        
        @Published var state: StoreProfileState = StoreProfileState(
            store: nil,
            isStoreLoading: false,
            storeError: nil,
            items: PagingState(
                isLoading: false,
                items: [],
                error: nil,
                endReached: false,
                page: 1
            ),
            reviews: PagingState(
                isLoading: false,
                items: [],
                error: nil,
                endReached: false,
                page: 1
            ),
            rating: 0,
            message: "",
            error: nil,
            showError: false,
            isLoading: false,
            isSuccess: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getStoreById: GetStoreById
            @Inject var getStoreItems: GetStoreItems
            @Inject var getStoreReviews: GetStoreReviews
            @Inject var addStoreReview: AddStoreReview
            
            self.viewModel = StoreProfileViewModel(
                getStoreById: getStoreById,
                getStoreItems: getStoreItems,
                getStoreReviews: getStoreReviews,
                addStoreReview: addStoreReview,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: StoreProfileEvent) {
            viewModel.onEvent(event: event)
        }
        
        func initialFetch(storeId: String) {
            viewModel.initialFetch(id: storeId)
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
