//
//  HorizontalUserCard.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HorizontalUserCard: View {
    let user: User?
    
    var body: some View {
        HStack(alignment: .top, spacing: 8) {
            AsyncImage(
                url: URL(string: user?.imageUrl ?? ""),
                content: { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(
                            width: 44,
                            height: 44
                        )
                },
                placeholder: {
                    ZStack {
                        ProgressView()
                            .frame(alignment: .center)
                    }
                    .frame(
                        width: 44,
                        height: 44
                    )
                    .background(.ultraThinMaterial)
                }
            )
            .clipShape(Circle())
            
            VStack(alignment: .leading, spacing: 2) {
                Text(user?.name ?? "")
                    .font(.SubHeadlineB)
                    .foregroundColor(.Primary)
                    .lineLimit(1)
                
                HStack(alignment: .center, spacing: 4) {
                    Image(systemName: "mappin")
                        .resizable()
                        .scaledToFit()
                        .frame(
                            width: 14,
                            height: 14
                        )
                        .foregroundColor(.gray)
                    
                    Text(user?.location ?? "")
                        .font(.FootnoteR)
                        .foregroundColor(.gray)
                        .lineLimit(1)
                }
            }
            
            Spacer()
            
            HStack(alignment: .center, spacing: 2) {
                Image(systemName: "star.fill")
                    .resizable()
                    .scaledToFit()
                    .frame(
                        width: 16,
                        height: 16
                    )
                    .foregroundColor(.Primary)
                
                Text(String(user?.rating ?? 0.0))
                    .font(.CalloutR)
                    .foregroundColor(.gray)
            }
        }
        .padding(10)
        .clipShape(
            RoundedRectangle(
                cornerRadius: 8
            )
        )
        .background(
            Color.Background
                .clipShape(
                    RoundedRectangle(
                        cornerRadius: 8
                    )
                )
                .shadow(radius: 1)
        )
    }
}

#Preview {
    HorizontalUserCard(
        user: Dummy().user
    )
}
