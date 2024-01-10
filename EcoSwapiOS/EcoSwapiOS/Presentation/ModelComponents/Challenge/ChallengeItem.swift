//
//  ChallengeItem.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 10/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ChallengeItem: View {
    let challenge: Challenge
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack(alignment: .center, spacing: 8) {
                Text(challenge.title)
                    .font(.Caption1B)
                    .lineLimit(1)
                
                Spacer()
                
                NavigationLink {
                    Text("Challenge Detail")
                } label: {
                    NavigationButtonFill(
                        label: "See Details",
                        size: .Small
                    )
                }
                .buttonStyle(.plain)
            }
            
            Divider()
            
            HStack(alignment: .center, spacing: 8) {
                VStack(alignment: .leading, spacing: 4) {
                    Text("Details :")
                        .font(.Caption2B)
                    
                    Text(challenge.description_)
                        .font(.Caption2R)
                        .lineLimit(3)
                    
                    HStack(alignment: .center, spacing: 0) {
                        AccountImageRow(
                            images: challenge
                                .participants
                                .prefix(3)
                                .map { account in
                                    account.imageUrl
                                }
                        )
                        
                        Spacer()
                            .frame(width: 4)
                        
                        Text("\(challenge.participants.count) ")
                            .font(.Caption2B)
                            .foregroundColor(.Primary)
                        
                        Text("People Joined")
                            .font(.Caption2R)
                    }
                }
                
                Spacer()
                
                ProgressBarInfo(
                    value: challenge.progress,
                    total: challenge.goals,
                    color: .Challenge
                )
                .frame(
                    width: 60,
                    height: 60
                )
            }
        }
        .padding(16)
        .clipShape(RoundedRectangle(cornerRadius: 10))
        .background(
            Color.Background
                .clipShape(
                    RoundedRectangle(cornerRadius: 10)
                )
                .shadow(radius: 1)
        )
    }
}

#Preview {
    ChallengeItem(
        challenge: Dummy().challenges[0]
    )
}
