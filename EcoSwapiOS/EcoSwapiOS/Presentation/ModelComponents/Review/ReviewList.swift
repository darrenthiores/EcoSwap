//
//  ReviewList.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ReviewList: View {
    let reviews: [Review]
    let geo: GeometryProxy
    let onAppear: () -> Void
    var spaceToTop: CGFloat = 0
    var spaceToBottom: CGFloat = 0
    
    var body: some View {
        ScrollView {
            Spacer()
                .frame(height: spaceToTop)
            
            LazyVStack(
                spacing: 32
            ) {
                ForEach(reviews, id: \.id) { review in
                    ReviewItem(
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
            
            Spacer()
                .frame(height: spaceToBottom)
        }
        .scrollIndicators(.hidden)
        .padding(.horizontal, 24)
    }
}

#Preview {
    GeometryReader { geo in
        ReviewList(
            reviews: Dummy().reviews,
            geo: geo,
            onAppear: { }
        )
    }
}
