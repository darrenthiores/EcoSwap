//
//  MessageUseCases.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class MessageUseCasesModule {
    init() {
        @Inject var messageRepository: MessageRepository
        
        @Provider var getInbox: GetInbox = GetInbox(
            repository: messageRepository
        )
        @Provider var getMessagesById: GetMessagesById = GetMessagesById(
            repository: messageRepository
        )
        @Provider var getUnreadCount: GetUnreadCount = GetUnreadCount(
            repository: messageRepository
        )
        @Provider var insertMessage: InsertMessage = InsertMessage(
            repository: messageRepository
        )
        @Provider var updateInbox: UpdateInbox = UpdateInbox(
            repository: messageRepository
        )
    }
}
