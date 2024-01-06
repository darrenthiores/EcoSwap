//
//  IosOtherProfileViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 06/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension OtherProfileScreen {
    @MainActor class IosOtherProfileViewModel: ObservableObject {
        private let viewModel: OtherProfileViewModel
        
        @Published var state: OtherProfileState = OtherProfileState(
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
            @Inject var getUserById: GetUserById
            @Inject var getUserItems: GetUserItems
            @Inject var getUserReviews: GetUserReviews
            @Inject var addUserReview: AddUserReview
            
            self.viewModel = OtherProfileViewModel(
                getUserById: getUserById,
                getUserItems: getUserItems,
                getUserReviews: getUserReviews,
                addUserReview: addUserReview,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: OtherProfileEvent) {
            viewModel.onEvent(event: event)
        }
        
        func initialFetch(id: String) {
            viewModel.initialFetch(id: id)
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
