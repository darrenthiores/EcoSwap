//
//  ReviewItem.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ReviewItem: View {
    let review: Review
    let geo: GeometryProxy
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            NavigationLink {
                Text("Review \(review.userId)")
            } label: {
                HStack(spacing: 4) {
                    AsyncImage(
                        url: URL(string: review.userImgUrl),
                        content: { image in
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fill)
                                .frame(width: 32, height: 32)
                        },
                        placeholder: {
                            ZStack {
                                ProgressView()
                                    .frame(alignment: .center)
                            }
                            .frame(width: 32, height: 32)
                            .background(.ultraThinMaterial)
                        }
                    )
                    .clipShape(Circle())
                    
                    VStack(alignment: .leading, spacing: 2) {
                        Text("@\(review.username)")
                            .font(.Caption1B)
                        
                        Text(
                            DateUtils()
                                .formatDate(timestamp: review.date)
                        )
                        .font(.Caption2R)
                    }
                    
                    Spacer()
                    
                    HStack(spacing: 4) {
                        Image(systemName: "star.fill")
                            .resizable()
                            .scaledToFit()
                            .frame(
                                width: 20,
                                height: 20
                            )
                            .foregroundColor(.Primary)
                        
                        Text(String(review.rating))
                            .font(.SubHeadlineB)
                            .foregroundColor(.Primary)
                    }
                }
            }
            .buttonStyle(.plain)
            
            Spacer()
                .frame(height: 8)
            
            Text(review.review)
                .font(.FootnoteR)
            
            Spacer()
                .frame(height: 16)
            
            Divider()
        }
    }
}

#Preview {
    GeometryReader { geo in
        ReviewItem(
            review: Dummy().reviews[0],
            geo: geo
        )
    }
}
