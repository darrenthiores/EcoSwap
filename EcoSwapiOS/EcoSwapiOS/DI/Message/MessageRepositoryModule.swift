//
//  MessageRepositoryModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class MessageRepositoryModule {
    init() {
        @Inject var localDataSource: MessageLocalDataSource
        @Inject var remoteDataSource: MessageRemoteDataSource
        
        @Provider var messageRepository: MessageRepository = MessageRepositoryImpl(
            remoteDataSource: remoteDataSource,
            localDataSource: localDataSource
        )
    }
}
