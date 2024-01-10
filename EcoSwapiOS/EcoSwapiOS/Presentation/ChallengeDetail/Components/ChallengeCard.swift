//
//  ChallengeCard.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 10/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ChallengeCard: View {
    let challenge: Challenge?
    let onJoinClick: () -> Void
    
    var body: some View {
        VStack(alignment: .leading, spacing: 16) {
            HStack(alignment: .center, spacing: 8) {
                VStack(alignment: .leading, spacing: 4) {
                    Text(challenge?.title ?? "")
                        .font(.CalloutB)
                        .lineLimit(1)
                    
                    Text("\(challenge?.tasks.count ?? 0) Task Challenge")
                        .font(.FootnoteR)
                }
                
                Spacer()
                
                ProgressBarInfo(
                    value: challenge?.progress ?? 0.0,
                    total: challenge?.goals ?? 0.0,
                    color: .Challenge
                )
                .frame(
                    width: 60,
                    height: 60
                )
            }
            
            if challenge?.isJoined == false {
                ButtonFill(
                    label: "Join CHallenge",
                    onClick: onJoinClick,
                    size: .Small,
                    fillWidth: true
                )
            }
        }
        .padding(16)
        .background(
            Color.Background
                .clipShape(RoundedRectangle(cornerRadius: 8))
                .shadow(radius: 1)
        )
    }
}

#Preview {
    ChallengeCard(
        challenge: Dummy().challenges[0],
        onJoinClick: {  }
    )
}
