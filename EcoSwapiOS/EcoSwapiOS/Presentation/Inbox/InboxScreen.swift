//
//  InboxScreen.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 08/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct InboxScreen: View {
    @Environment(\.dismiss) private var dismiss
    @StateObject private var viewModel = IosInboxViewModel()
    
    var body: some View {
        NavigationStack {
            ScrollView(.vertical, showsIndicators: false) {
                LazyVStack(spacing: 0) {
                    Spacer()
                        .frame(height: 16)
                    
                    ForEach(viewModel.state.inboxes, id: \.id) { inbox in
                        NavigationLink {
                            Text("Message")
                        } label: {
                            InboxItem(
                                inbox: inbox
                            )
                        }
                        .buttonStyle(.plain)
                        
                        Divider()
                            .padding(.leading, 78)
                    }
                    
                    Spacer()
                        .frame(height: 16)
                }
            }
            .navigationBarBackButtonHidden()
            .navigationBarTitleDisplayMode(.inline)
            .onAppear {
                viewModel.startObserving()
            }
            .onDisappear {
                viewModel.dispose()
            }
            .toolbar {
                ToolbarItem(placement: .principal) {
                    DefaultHeader(
                        title: "Inbox",
                        onBackClick: {
                            dismiss()
                        }
                    )
                    .padding(.vertical, 16)
                }
            }
        }
    }
}

#Preview {
    InboxScreen()
}
