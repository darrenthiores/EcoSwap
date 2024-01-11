//
//  SustainabilityScreen.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 10/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SustainabilityScreen: View {
    @StateObject private var viewModel = IosSustainabilityViewModel()
    @State private var currentTab: SustainabilityTab = .tracking
    @State private var showAdd: Bool = false
    @State private var shouldRefresh: Bool = false
    
    var body: some View {
        NavigationStack {
            VStack(spacing: 0) {
                VStack(spacing: 0) {
                    HStack(spacing: 0) {
                        ForEach(SustainabilityTab.entries, id: \.name) { tab in
                            TabButton(
                                title: tab.name,
                                isSelected: currentTab == tab,
                                onClick: {
                                    currentTab = tab
                                }
                            )
                        }
                    }
                    
                    Divider()
                }
                .background(
                    Color.Background
                )
                .contentShape(Rectangle())
                
                ZStack(alignment: .bottomTrailing) {
                    TabView(selection: $currentTab) {
                        SustainabilityTrackingContent(
                            state: viewModel.state,
                            onEvent: { event in
                                viewModel.onEvent(event: event)
                            }
                        )
                        .tag(SustainabilityTab.tracking)
                        
                        if viewModel.state.challenges.items.isEmpty {
                            Text("You haven't joined any Challenges")
                                .tag(SustainabilityTab.challenge)
                        } else {
                            ChallengeList(
                                challenges: viewModel.state.challenges.items as? [Challenge] ?? [],
                                onAppear: {
                                    if !viewModel.state.challenges.endReached && !viewModel.state.challenges.isLoading {
                                        viewModel.onEvent(event: .LoadChallengeNextPage())
                                    }
                                },
                                shouldRefresh: $shouldRefresh,
                                spaceToTop: 24,
                                spaceToBottom: 24 + 56
                            )
                            .tag(SustainabilityTab.challenge)
                        }
                    }
                    .tabViewStyle(.page(indexDisplayMode: .never))
                    
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
                
                if shouldRefresh {
                    viewModel.onEvent(
                        event: .Refresh()
                    )
                    
                    shouldRefresh = false
                }
            }
            .onDisappear {
                viewModel.dispose()
            }
            .navigationDestination(isPresented: $showAdd) {
                AddProgressScreen(
                    shouldRefresh: $shouldRefresh
                )
            }
        }
    }
}

#Preview {
    SustainabilityScreen()
}
