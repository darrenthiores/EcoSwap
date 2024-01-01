//
//  StoreUseCasesModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class StoreUseCasesModule {
    init() {
        @Inject var storeRepository: StoreRepository
        
        @Provider var getStores: GetStores = GetStores(
            repository: storeRepository
        )
        @Provider var getStoreById: GetStoreById = GetStoreById(
            repository: storeRepository
        )
        @Provider var searchStores: SearchStores = SearchStores(
            repository: storeRepository
        )
    }
}
