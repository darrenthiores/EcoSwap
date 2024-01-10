//
//  ChallengeDetailContent.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 10/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ChallengeDetailContent: View {
    let state: ChallengeDetailState
    let onEvent: (ChallengeDetailEvent) -> Void
    var viewAll: Bool = false
    var onToggleViewAll: () -> Void = {  }
    
    var body: some View {
        ScrollView(.vertical, showsIndicators: false) {
            LazyVStack(alignment: .leading, spacing: 0) {
                Spacer()
                    .frame(height: 24)
                
                Group {
                    ChallengeCard(
                        challenge: state.challenge,
                        onJoinClick: {
                            onEvent(.Join())
                        }
                    )
                    .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 16)
                }
                
                Group {
                    Text("Description :")
                        .font(.CalloutB)
                    
                    Spacer()
                        .frame(height: 4)
                    
                    Text(state.challenge?.description_ ?? "-")
                        .font(.FootnoteR)
                    
                    Spacer()
                        .frame(height: 16)
                }
                .padding(.horizontal, 24)
                
                Group {
                    Text("Details :")
                        .font(.CalloutB)
                    
                    ForEach(state.challenge?.tasks ?? [], id: \.id) { task in
                        TaskItem(task: task)
                    }
                    
                    Spacer()
                        .frame(height: 16)
                }
                .padding(.horizontal, 24)
                
                HStack(alignment: .center, spacing: 0) {
                    AccountImageRow(
                        images: state.challenge?
                            .participants
                            .prefix(3)
                            .map { account in
                                account.imageUrl
                            } ?? [],
                        size: 38
                    )
                    
                    Spacer()
                        .frame(width: 4)
                    
                    Text("\(state.challenge?.participants.count ?? 0) ")
                        .font(.CalloutB)
                        .foregroundColor(.Primary)
                    
                    Text("People Joined")
                        .font(.CalloutR)
                    
                    Spacer()
                }
                .padding(.horizontal, 24)
                
                Group {
                    Spacer()
                        .frame(height: 16)
                    
                    Divider()
                        .frame(height: 3)
                    
                    Spacer()
                        .frame(height: 16)
                }
                
                Group {
                    SectionHeader(
                        title: "Leaderboards",
                        endAction: viewAll ? "View Less" : "View All",
                        onActionClick: onToggleViewAll
                    )
                    
                    Spacer()
                        .frame(height: 8)
                    
                    ForEach(
                        Array((state.challenge?.participants ?? []).enumerated()),
                        id: \.offset
                    ) { index, participant in
                        AccountLeaderboard(
                            name: participant.name,
                            imageUrl: participant.imageUrl,
                            reduction: participant.progress,
                            index: index
                        )
                    }
                }
                .padding(.horizontal, 24)
                
                Spacer()
                    .frame(height: 24 + 56)
            }
        }
    }
}

#Preview {
    ChallengeDetailContent(
        state: ChallengeDetailState(
            challenge: nil,
            error: nil,
            isLoading: false
        ),
        onEvent: { _ in }
    )
}
