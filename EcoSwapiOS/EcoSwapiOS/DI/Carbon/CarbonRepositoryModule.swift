//
//  CarbonRepositoryModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class CarbonRepositoryModule {
    init() {
        @Inject var localDataSource: CarbonLocalDataSource
        @Inject var remoteDataSource: CarbonRemoteDataSource
        
        @Provider var carbonRepository: CarbonRepository = CarbonRepositoryImpl(
            remoteDataSource: remoteDataSource,
            localDataSource: localDataSource
        )
    }
}
