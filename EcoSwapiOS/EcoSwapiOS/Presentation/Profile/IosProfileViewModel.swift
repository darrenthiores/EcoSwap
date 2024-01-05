//
//  IosProfileViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension ProfileScreen {
    @MainActor class IosProfileViewModel: ObservableObject {
        private let viewModel: ProfileViewModel
        
        @Published var state: ProfileState = ProfileState(
            user: nil,
            isUserLoading: false,
            userError: nil,
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
            )
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getUser: GetUser
            @Inject var getMyItems: GetMyItems
            @Inject var getMyReviews: GetMyReviews
            
            self.viewModel = ProfileViewModel(
                getUser: getUser,
                getItems: getMyItems,
                getReviews: getMyReviews,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: ProfileEvent) {
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
