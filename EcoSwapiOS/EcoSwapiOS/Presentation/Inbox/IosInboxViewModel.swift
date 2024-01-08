//
//  IosInboxViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 08/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension InboxScreen {
    @MainActor class IosInboxViewModel: ObservableObject {
        private let viewModel: InboxViewModel
        
        @Published var state: InboxState = InboxState(
            inboxes: [],
            isLoading: false,
            error: nil
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getInbox: GetInbox
            
            self.viewModel = InboxViewModel(
                getInbox: getInbox,
                coroutineScope: nil
            )
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
