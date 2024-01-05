//
//  ItemDetailViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension ItemDetailScreen {
    @MainActor class IosItemDetailViewModel: ObservableObject {
        private let viewModel: ItemDetailViewModel
        
        @Published var state: ItemDetailState = ItemDetailState(
            item: nil,
            user: nil,
            isLoading: false,
            error: nil,
            isExpand: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getItemById: GetItemById
            @Inject var getUserById: GetUserById
            
            self.viewModel = ItemDetailViewModel(
                getItemById: getItemById,
                getUserById: getUserById,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: ItemDetailEvent) {
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
