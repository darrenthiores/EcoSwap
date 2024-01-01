//
//  CarbonUseCasesModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class CarbonUseCasesModule {
    init() {
        @Inject var carbonRepository: CarbonRepository
        
        @Provider var getFootprint: GetFootPrint = GetFootPrint(
            repository: carbonRepository
        )
        @Provider var insertCarbonReduction: InsertCarbonReduction = InsertCarbonReduction(
            repository: carbonRepository
        )
        @Provider var getChallenges: GetChallenges = GetChallenges(
            repository: carbonRepository
        )
        @Provider var getChallengeById: GetChallengeById = GetChallengeById(
            repository: carbonRepository
        )
        @Provider var joinChallenge: JoinChallenge = JoinChallenge(
            repository: carbonRepository
        )
    }
}
