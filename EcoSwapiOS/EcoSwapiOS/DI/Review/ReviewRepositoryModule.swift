//
//  ReviewRepositoryModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class ReviewRepositoryModule {
    init() {
        @Inject var remoteDataSource: ReviewRemoteDataSource
        
        @Provider var reviewRepository: ReviewRepository = ReviewRepositoryImpl(
            remoteDataSource: remoteDataSource
        )
    }
}
