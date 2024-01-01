//
//  UserRepositoryModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class UserRepositoryModule {
    init() {
        @Inject var remoteDataSource: UserRemoteDataSource
        
        @Provider var userRepository: UserRepository = UserRepositoryImpl(
            remoteDataSource: remoteDataSource
        )
    }
}
