//
//  IosSustainabilityViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 10/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension SustainabilityScreen {
    @MainActor class IosSustainabilityViewModel: ObservableObject {
        private let viewModel: SustainabilityViewModel
        
        @Published var state: SustainabilityState = SustainabilityState(
            footPrint: nil,
            challenges: PagingState(
                isLoading: false,
                items: [],
                error: nil,
                endReached: false,
                page: 1
            ),
            error: nil,
            isLoading: false,
            viewMode: .daily
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getFootPrint: GetFootPrint
            @Inject var getChallenges: GetChallenges
            
            self.viewModel = SustainabilityViewModel(
                getFootPrint: getFootPrint,
                getChallenges: getChallenges,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: SustainabilityEvent) {
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
