//
//  IosMessageViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 08/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension MessageScreen {
    @MainActor class IosMessageViewModel: ObservableObject {
        private let viewModel: MessageViewModel
        
        @Published var state: MessageState = MessageState(
            messages: [],
            messagesLoading: false,
            messagesError: nil,
            user: nil,
            userLoading: false,
            userError: nil,
            newMessage: "",
            error: nil,
            isSendingMessage: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getMessagesById: GetMessagesById
            @Inject var getUserById: GetUserById
            @Inject var insertMessage: InsertMessage
            
            self.viewModel = MessageViewModel(
                getMessageById: getMessagesById,
                getUserById: getUserById,
                insertMessage: insertMessage,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: MessageEvent) {
            viewModel.onEvent(event: event)
        }
        
        func initFetch(userId: String) {
            viewModel.doInit(
                userId: userId
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
