//
//  StoreRepositoryModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class StoreRepositoryModule {
    init() {
        @Inject var remoteDataSource: StoreRemoteDataSource
        
        @Provider var storeRepository: StoreRepository = StoreRepositoryImpl(
            remoteDataSource: remoteDataSource
        )
    }
}
