//
//  CarbonNetworkModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class CarbonNetworkModule {
    init() {
        @Inject var dispatchers: DispatchersProvider
        @Inject var client: Ktor_client_coreHttpClient
        
        @Provider var carbonService: CarbonService = KtorCarbonService(client: client)
        @Provider var carbonRemoteDataSource: CarbonRemoteDataSource = CarbonRemoteDataSource(
            apiService: carbonService,
            dispatchers: dispatchers
        )
    }
}
