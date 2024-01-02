//
//  IosBoardingViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 01/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension BoardingScreen {
    @MainActor class IosBoardingViewModel: ObservableObject {
        private let viewModel: BoardingViewModel
        
        @Published var state: BoardingState = BoardingState(
            currentPage: 1,
            done: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            self.viewModel = BoardingViewModel(
                coroutineScope: nil
            )
        }
        
        func onEvent(event: BoardingEvent) {
            viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe { state in
                if let state = state {
                    self.state = state
                    
                    if state.done {
                        let userDefaults = UserDefaults.standard
                        userDefaults.set(false, forKey: "showBoarding")
                    }
                }
            }
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
