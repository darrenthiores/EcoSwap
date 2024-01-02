//
//  FixedStoreCard.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FixedStoreCard: View {
    let store: Store
    let geo: GeometryProxy
    
    private var cardWidth: CGFloat {
        geo.size.width / 2 - 24 - (12/2)
    }
    
    private var imageHeight: CGFloat {
        (cardWidth + 24 + 3) * 0.8
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            ZStack(alignment: .bottomLeading) {
                AsyncImage(
                    url: URL(string: store.imgUrl),
                    content: { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(
                                width: cardWidth,
                                height: imageHeight
                            )
                    },
                    placeholder: {
                        ZStack {
                            ProgressView()
                                .frame(alignment: .center)
                        }
                        .frame(
                            width: cardWidth,
                            height: imageHeight
                        )
                        .background(.ultraThinMaterial)
                    }
                )
                .cornerRadius(10)
                
                DistanceLabel(distance: "2,6 km")
                    .padding(8)
                
//                HStack(spacing: 4) {
//                    Image(systemName: "eye")
//                        .resizable()
//                        .scaledToFit()
//                        .frame(
//                            width: 12,
//                            height: 12
//                        )
//
//                    Text("0")
//                        .font(.C4)
//                }
//                .foregroundColor(.white)
//                .padding(8)
            }
            
            VStack(
                alignment: .leading,
                spacing: 0
            ) {
                Text("@\(store.name)")
                    .font(.Caption1B)
                    .frame(
                        maxWidth: .infinity,
                        alignment: .leading
                    )
                    .lineLimit(1)
                
                Spacer()
                    .frame(height: 8)
                
                HStack(
                    alignment: .center,
                    spacing: 4
                ) {
                    Image(systemName: "mappin")
                        .resizable()
                        .scaledToFit()
                        .frame(
                            width: 12,
                            height: 12
                        )
                        .foregroundColor(.gray)
                    
                    Text(store.location)
                        .font(.Caption2R)
                        .foregroundColor(.gray)
                        .lineLimit(1)
                    
                    Text("•")
                        .font(.Caption2R)
                        .foregroundColor(.gray)
                    
                    HStack(
                        alignment: .center,
                        spacing: 2
                    ) {
                        Image(systemName: "star.fill")
                            .resizable()
                            .scaledToFit()
                            .frame(
                                width: 10,
                                height: 10
                            )
                            .foregroundColor(.Primary)
                        
                        Text(String(store.rating))
                            .font(.Caption2R)
                            .foregroundColor(.gray)
                    }
                }
            }
            .padding(8)
        }
        .frame(
            width: cardWidth
        )
        .cornerRadius(10)
        .background(
            Color.Background
                .cornerRadius(10)
                .shadow(radius: 1)
        )

    }
}

#Preview {
    GeometryReader { geo in
        FixedStoreCard(
            store: Dummy().stores[0],
            geo: geo
        )
    }
}
