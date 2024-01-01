//
//  ReviewNetworkModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class ReviewNetworkModule {
    init() {
        @Inject var dispatchers: DispatchersProvider
        @Inject var client: Ktor_client_coreHttpClient
        
        @Provider var reviewService: ReviewService = KtorReviewService(client: client)
        @Provider var reviewRemoteDataSource: ReviewRemoteDataSource = ReviewRemoteDataSource(
            apiService: reviewService,
            dispatchers: dispatchers
        )
    }
}
