//
//  IosChallengeDetailViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 10/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension ChallengeDetailScreen {
    @MainActor class IosChallengeDetailViewModel: ObservableObject {
        private let viewModel: ChallengeDetailViewModel
        
        @Published var state: ChallengeDetailState = ChallengeDetailState(
            challenge: nil,
            error: nil,
            isLoading: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getChallengeById: GetChallengeById
            @Inject var joinChallenge: JoinChallenge
            
            self.viewModel = ChallengeDetailViewModel(
                getChallengeById: getChallengeById,
                joinChallenge: joinChallenge,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: ChallengeDetailEvent) {
            viewModel.onEvent(event: event)
        }
        
        func initFetch(challengeId: String) {
            viewModel.doInitChallenge(id: challengeId)
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
