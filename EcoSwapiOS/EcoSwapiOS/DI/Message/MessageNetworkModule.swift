//
//  MessageNetworkModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class MessageNetworkModule {
    init() {
        @Inject var dispatchers: DispatchersProvider
        @Inject var client: Ktor_client_coreHttpClient
        
        @Provider var messageService: MessageService = KtorMessageService(client: client)
        @Provider var messageRemoteDataSource: MessageRemoteDataSource = MessageRemoteDataSource(
            apiService: messageService,
            dispatchers: dispatchers
        )
    }
}
