//
//  MessageLocalDataSource.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class MessageLocalModule {
    init() {
        @Inject var appDatabase: AppDatabase
        @Provider var messageDao: MessageDao = SqlDelightMessageDao(db: appDatabase)
        @Provider var messageLocalDataSource: MessageLocalDataSource = MessageLocalDataSource(
            dao: messageDao
        )
    }
}
