//
//  AuthNetworkModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class AuthNetworkModule {
    init() {
        @Inject var dispatchers: DispatchersProvider
        @Inject var client: Ktor_client_coreHttpClient
        
        @Provider var authService: AuthService = KtorAuthService(client: client)
        @Provider var authRemoteDataSource: AuthRemoteDataSource = AuthRemoteDataSource(
            apiService: authService,
            dispatchers: dispatchers
        )
    }
}
