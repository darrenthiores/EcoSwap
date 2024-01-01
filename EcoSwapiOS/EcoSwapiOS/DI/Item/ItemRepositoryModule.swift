//
//  ItemRepositoryModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class ItemRepositoryModule {
    init() {
        @Inject var remoteDataSource: ItemRemoteDataSource
        
        @Provider var itemRepository: ItemRepository = ItemRepositoryImpl(
            remoteDataSource: remoteDataSource
        )
    }
}
