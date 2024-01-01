//
//  CarbonLocalModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class CarbonLocalModule {
    init() {
        @Inject var appDatabase: AppDatabase
        @Provider var carbonDao: CarbonDao = SqlDelightCarbonDao(db: appDatabase)
        @Provider var carbonLocalDataSource: CarbonLocalDataSource = CarbonLocalDataSource(
            dao: carbonDao
        )
    }
}
