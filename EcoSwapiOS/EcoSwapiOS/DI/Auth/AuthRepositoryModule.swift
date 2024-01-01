//
//  AuthRepositoryModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class AuthRepositoryModule {
    init() {
        @Inject var remoteDataSource: AuthRemoteDataSource
        
        @Provider var authRepository: AuthRepository = AuthRepositoryImpl(
            remoteDataSource: remoteDataSource
        )
    }
}
