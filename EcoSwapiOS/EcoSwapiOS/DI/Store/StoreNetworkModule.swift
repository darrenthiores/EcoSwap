//
//  StoreNetworkModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class StoreNetworkModule {
    init() {
        @Inject var dispatchers: DispatchersProvider
        @Inject var client: Ktor_client_coreHttpClient
        
        @Provider var storeService: StoreService = KtorStoreService(client: client)
        @Provider var storeRemoteDataSource: StoreRemoteDataSource = StoreRemoteDataSource(
            apiService: storeService,
            dispatchers: dispatchers
        )
    }
}
