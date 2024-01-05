//
//  ProfileTopSection.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProfileTopSection: View {
    let user: User?
    
    var body: some View {
        VStack(alignment: .center) {
            AsyncImage(
                url: URL(string: user?.imageUrl ?? ""),
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
            
            Text(user?.name ?? "null")
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
                
                Text(String(user?.rating ?? 0.0))
                    .font(.Caption1R)
                    .foregroundColor(.Primary)
                
                Text("(\(Int(user?.totalReview ?? Int32(0))) Reviews)")
                    .font(.Caption1R)
            }
        }
        .padding(24)
    }
}

#Preview {
    ProfileTopSection(
        user: Dummy()
            .user
    )
}
