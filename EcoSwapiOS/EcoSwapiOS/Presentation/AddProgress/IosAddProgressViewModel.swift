//
//  IosAddProgressViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 11/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension AddProgressScreen {
    @MainActor class IosAddProgressViewModel: ObservableObject {
        private let viewModel: AddProgressViewModel
        
        @Published var state: AddProgressState = AddProgressState(
            category: nil,
            activity: nil,
            activities: [],
            number: "",
            numberError: nil,
            challenges: PagingState(
                isLoading: false,
                items: [],
                error: nil,
                endReached: false,
                page: 1
            ),
            challenge: nil,
            task: nil,
            categoryDropDownOpen: false,
            activityDropDownOpen: false,
            taskDropDownOpen: false,
            isLoading: false,
            error: nil,
            isSuccess: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var addProgress: InsertCarbonReduction
            @Inject var getChallenges: GetChallenges
            @Inject var getChallengeById: GetChallengeById
            
            self.viewModel = AddProgressViewModel(
                addProgress: addProgress,
                getChallenges: getChallenges,
                getChallengeById: getChallengeById,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: AddProgressEvent) {
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
