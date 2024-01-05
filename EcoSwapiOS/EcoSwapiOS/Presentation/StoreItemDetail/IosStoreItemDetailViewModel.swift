//
//  IosStoreItemDetailViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension StoreItemDetailScreen {
    @MainActor class IosStoreItemDetailViewModel: ObservableObject {
        private let viewModel: StoreItemDetailViewModel
        
        @Published var state: StoreItemDetailState = StoreItemDetailState(
            item: nil,
            store: nil,
            isLoading: false,
            error: nil,
            isExpand: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getStoreItemById: GetStoreItemById
            @Inject var getStoreById: GetStoreById
            
            self.viewModel = StoreItemDetailViewModel(
                getStoreItemById: getStoreItemById,
                getStoreById: getStoreById,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: StoreItemDetailEvent) {
            viewModel.onEvent(event: event)
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
