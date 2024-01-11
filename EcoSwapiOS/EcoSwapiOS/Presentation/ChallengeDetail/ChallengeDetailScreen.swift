//
//  ChallengeDetailScreen.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 10/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ChallengeDetailScreen: View {
    let challengeId: String
    @Binding var shouldRefresh: Bool
    
    @Environment(\.dismiss) private var dismiss
    @StateObject private var viewModel = IosChallengeDetailViewModel()
    @State private var showAdd: Bool = false
    
    var body: some View {
        ZStack(alignment: .bottomTrailing) {
            ChallengeDetailContent(
                state: viewModel.state,
                onEvent: { event in
                    viewModel.onEvent(event: event)
                }
            )
            
            if viewModel.state.challenge?.isJoined == true {
                Button {
                    showAdd = true
                } label: {
                    ZStack(alignment: .center) {
                        Image(systemName: "plus")
                            .resizable()
                            .scaledToFit()
                            .frame(
                                width: 16,
                                height: 16
                            )
                            .foregroundColor(.OnPrimary)
                    }
                    .frame(
                        width: 56,
                        height: 56
                    )
                    .clipShape(Circle())
                    .background(
                        Color.Primary
                            .clipShape(Circle())
                            .shadow(radius: 1)
                    )
                }
                .padding(24)
            }
        }
        .onAppear {
            viewModel.startObserving()
            viewModel.initFetch(challengeId: challengeId)
        }
        .onDisappear {
            viewModel.dispose()
        }
        .navigationBarBackButtonHidden()
        .toolbar(.hidden, for: .tabBar)
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .principal) {
                SettingHeader(
                    title: "Challenge Details",
                    onBackClick: {
                        dismiss()
                    },
                    onSettingClick: {  }
                )
                .padding(16)
            }
        }
        .navigationDestination(isPresented: $showAdd) {
            AddProgressScreen(
                challengeId: challengeId,
                shouldRefresh: $shouldRefresh
            )
        }
    }
}

#Preview {
    ChallengeDetailScreen(
        challengeId: "1",
        shouldRefresh: .constant(false)
    )
}
