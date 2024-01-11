//
//  ChallengeSheet.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 11/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ChallengeSheet: View {
    let challenges: [Challenge]
    let onSelectChallenge: (Challenge) -> Void
    let onAppear: () -> Void
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Spacer()
                .frame(height: 16)
            
            HStack {
                Spacer()
                
                ZStack {
                    Color.gray
                }
                .frame(
                    width: 56,
                    height: 4
                )
                .clipShape(
                    RoundedRectangle(cornerRadius: 32)
                )
                
                Spacer()
            }
            
            Spacer()
                .frame(height: 32)
            
            Text("Active and Joined Challenge")
                .font(.Title2B)
                .padding(.horizontal, 24)
            
            Spacer()
                .frame(height: 12)
            
            SelectableChallengeList(
                challenges: challenges,
                onClick: onSelectChallenge,
                onAppear: onAppear,
                spaceToTop: 12,
                spaceToBottom: 24
            )
        }
    }
}

#Preview {
    ChallengeSheet(
        challenges: Dummy().challenges,
        onSelectChallenge: { _ in },
        onAppear: {  }
    )
}
