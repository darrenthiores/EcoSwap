//
//  ItemNetworkModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class ItemNetworkModule {
    init() {
        @Inject var dispatchers: DispatchersProvider
        @Inject var client: Ktor_client_coreHttpClient
        
        @Provider var itemService: ItemService = KtorItemService(client: client)
        @Provider var itemRemoteDataSource: ItemRemoteDataSource = ItemRemoteDataSource(
            apiService: itemService,
            dispatchers: dispatchers
        )
    }
}
