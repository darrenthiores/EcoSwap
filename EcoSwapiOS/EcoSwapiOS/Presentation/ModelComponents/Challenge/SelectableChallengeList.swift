//
//  SelectableChallengeList.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 10/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SelectableChallengeList: View {
    let challenges: [Challenge]
    let onClick: (Challenge) -> Void
    let onAppear: () -> Void
    var spaceToTop: CGFloat = 0
    var spaceToBottom: CGFloat = 0
    
    var body: some View {
        ScrollView {
            Spacer()
                .frame(height: spaceToTop)
            
            LazyVStack(spacing: 24) {
                ForEach(challenges, id: \.id) { challenge in
                    Button {
                        onClick(challenge)
                    } label: {
                        SelectableChallengeItem(
                            challenge: challenge
                        )
                    }
                    .buttonStyle(.plain)
                    .onAppear {
                        let index = challenges.firstIndex(
                            where: { challenges in challenges.id == challenge.id }
                        )
                        
                        if let index = index {
                            let count = index + 1
                            
                            if count == challenges.count
                                && count % 15 == 0{
                                onAppear()
                            }
                        }
                    }
                    .padding(.horizontal, 1)
                }
            }
            
            Spacer()
                .frame(height: spaceToBottom)
        }
        .scrollIndicators(.hidden)
        .padding(.horizontal, 23)
    }
}

#Preview {
    SelectableChallengeList(
        challenges: Dummy().challenges,
        onClick: { _ in },
        onAppear: {  }
    )
}
