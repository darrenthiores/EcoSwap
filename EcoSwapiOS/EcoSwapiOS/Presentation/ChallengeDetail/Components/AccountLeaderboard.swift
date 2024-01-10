//
//  AccountLeaderboard.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 10/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct AccountLeaderboard: View {
    let name: String
    let imageUrl: String
    let reduction: Double
    let index: Int
    
    var body: some View {
        VStack(spacing: 8) {
            HStack(alignment: .center, spacing: 8) {
                Text(String(index + 1))
                    .font(.SubHeadlineB)
                
                Spacer()
                    .frame(width: 0)
                
                AsyncImage(
                    url: URL(string: imageUrl),
                    content: { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(
                                width: 38,
                                height: 38
                            )
                    },
                    placeholder: {
                        ZStack {
                            ProgressView()
                                .frame(alignment: .center)
                        }
                        .frame(
                            width: 38,
                            height: 38
                        )
                        .background(.ultraThinMaterial)
                    }
                )
                .clipShape(Circle())
                
                Text(name)
                    .font(.CalloutR)
                    .lineLimit(1)
                
                Spacer()
                
                HStack(alignment: .center, spacing: 0) {
                    Text("\(String(format:"%.01f", reduction)) ")
                        .font(.FootnoteB)
                        .foregroundColor(.Primary)
                    
                    Text("CO2")
                        .font(.FootnoteR)
                }
                
                if (0...2).contains(index) {
                    Image(systemName: "star.fill")
                        .resizable()
                        .scaledToFit()
                        .frame(
                            width: 24,
                            height: 24
                        )
                        .foregroundColor(
                            .getProgressColorByIndex(
                                index: index
                            ) ?? .DefaultProgressColor
                        )
                }
            }
            
            Divider()
        }
        .padding(.top, 8)
    }
}

#Preview {
    AccountLeaderboard(
        name: "Darren",
        imageUrl: "",
        reduction: 100.0,
        index: 0
    )
}
