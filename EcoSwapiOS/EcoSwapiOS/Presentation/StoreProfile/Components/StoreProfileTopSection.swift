//
//  StoreProfileTopSection.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 07/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct StoreProfileTopSection: View {
    let store: Store?
    
    var body: some View {
        VStack(alignment: .center) {
            AsyncImage(
                url: URL(string: store?.imgUrl ?? ""),
                content: { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(
                            width: 128,
                            height: 128
                        )
                },
                placeholder: {
                    ZStack {
                        ProgressView()
                            .frame(alignment: .center)
                    }
                    .frame(
                        width: 128,
                        height: 128
                    )
                    .background(.ultraThinMaterial)
                }
            )
            .clipShape(Circle())
            
            Spacer()
                .frame(height: 16)
            
            Text(store?.name ?? "null")
                .font(.SubHeadlineB)
            
            Spacer()
                .frame(height: 8)
            
            HStack(alignment: .center, spacing: 4) {
                Image(systemName: "star.fill")
                    .resizable()
                    .scaledToFit()
                    .frame(
                        width: 12,
                        height: 12
                    )
                    .foregroundColor(.Primary)
                
                Text(String(store?.rating ?? 0.0))
                    .font(.Caption1R)
                    .foregroundColor(.Primary)
                
                Text("(\(Int(store?.totalReview ?? Int32(0))) Reviews)")
                    .font(.Caption1R)
            }
            
            Spacer()
                .frame(height: 8)
            
            Text(store?.location ?? "null")
                .font(.Caption1R)
                .padding(.horizontal, 32)
                .multilineTextAlignment(.center)
        }
        .padding(24)
    }
}

#Preview {
    StoreProfileTopSection(
        store: Dummy().stores[0]
    )
}
