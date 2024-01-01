//
//  UserNetworkModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class UserNetworkModule {
    init() {
        @Inject var dispatchers: DispatchersProvider
        @Inject var client: Ktor_client_coreHttpClient
        
        @Provider var userService: UserService = KtorUserService(client: client)
        @Provider var userRemoteDataSource: UserRemoteDataSource = UserRemoteDataSource(
            apiService: userService,
            dispatchers: dispatchers
        )
    }
}
