//
//  MessageScreen.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 08/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MessageScreen: View {
    let userId: String
    
    @Environment(\.dismiss) private var dismiss
    @StateObject private var viewModel = IosMessageViewModel()
    
    var body: some View {
        GeometryReader { geo in
            VStack(spacing: 0) {
                MessageHeader(
                    title: viewModel.state.user?.name ?? "null",
                    onBackClick: {
                        dismiss()
                    },
                    onSettingClick: {  }
                )
                
                ScrollViewReader { scrollView in
                    ReversedScrollView(
                        .vertical,
                        proxy: geo
                    ) {
                        Spacer()
                            .frame(height: 16)
                        
                        ForEach(viewModel.state.messages.reversed(), id: \.id) { message in
                            if message.sentToId == userId {
                                PersonalMessageItem(
                                    text: message.content,
                                    username: message.sentFromUsername,
                                    userImgUrl: message.sentFromImageUrl,
                                    geo: geo
                                )
                            } else {
                                FriendMessageItem(
                                    text: message.content,
                                    username: message.sentToUsername,
                                    userImgUrl: message.sentToImageUrl,
                                    geo: geo
                                )
                            }
                        }
                        
                        Spacer()
                            .frame(height: 16)
                    }
                    .padding(.horizontal, 16)
                    .onChange(of: viewModel.state.messages) { messages in
                        if let message = messages.first {
                            withAnimation(.default) {
                                scrollView
                                    .scrollTo(message.id)
                            }
                        }
                    }
                }
                
                MessageBottomBar(
                    message: Binding(
                        get: {
                            viewModel.state.newMessage
                        },
                        set: {
                            viewModel.onEvent(
                                event: .OnMessageChange(newText: $0)
                            )
                        }
                    ),
                    onAddPhotoClick: {  },
                    onSendClick: {
                        viewModel.onEvent(
                            event: .SendMessage()
                        )
                    }
                )
            }
        }
        .navigationBarBackButtonHidden()
        .navigationBarTitleDisplayMode(.inline)
        .toolbar(.hidden, for: .tabBar)
        .onAppear {
            viewModel.startObserving()
            viewModel.initFetch(userId: userId)
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

#Preview {
    MessageScreen(
        userId: "1"
    )
}
