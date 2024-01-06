//
//  StoreReviewGrid.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 07/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct StoreReviewGrid: View {
    let reviews: [StoreReview]
    let geo: GeometryProxy
    let onAppear: () -> Void
    var spaceToTop: CGFloat = 0
    var spaceToBottom: CGFloat = 0
    
    var body: some View {
        LazyVStack(
            spacing: 32
        ) {
            ForEach(reviews, id: \.id) { review in
                StoreReviewItem(
                    review: review,
                    geo: geo
                )
                .onAppear {
                    let index = reviews.firstIndex(
                        where: { reviews in reviews.id == review.id }
                    )
                    
                    if let index = index {
                        let count = index + 1
                        
                        if count == reviews.count
                            && count % 15 == 0{
                            onAppear()
                        }
                    }
                }
            }
        }
        .padding(.horizontal, 24)
        .padding(.top, spaceToTop)
        .padding(.bottom, spaceToBottom)
    }
}

#Preview {
    GeometryReader { geo in
        StoreReviewGrid(
            reviews: Dummy().storeReviews,
            geo: geo,
            onAppear: { }
        )
    }
}
