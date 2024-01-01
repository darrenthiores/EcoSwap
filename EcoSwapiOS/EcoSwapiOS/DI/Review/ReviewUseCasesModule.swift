//
//  ReviewUseCasesModule.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class ReviewUseCasesModule {
    init() {
        @Inject var reviewRepository: ReviewRepository
        
        @Provider var getMyReviews: GetMyReviews = GetMyReviews(
            repository: reviewRepository
        )
        @Provider var getUserReviews: GetUserReviews = GetUserReviews(
            repository: reviewRepository
        )
        @Provider var getStoreReviews: GetStoreReviews = GetStoreReviews(
            repository: reviewRepository
        )
        @Provider var addUserReview: AddUserReview = AddUserReview(
            repository: reviewRepository
        )
        @Provider var addStoreReview: AddStoreReview = AddStoreReview(
            repository: reviewRepository
        )
    }
}
