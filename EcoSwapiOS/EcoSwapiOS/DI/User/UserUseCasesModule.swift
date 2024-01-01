//
//  UserUseCasesModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class UserUseCasesModule {
    init() {
        @Inject var userRepository: UserRepository
        
        @Provider var getUser: GetUser = GetUser(
            repository: userRepository
        )
        @Provider var getUserById: GetUserById = GetUserById(
            repository: userRepository
        )
    }
}
