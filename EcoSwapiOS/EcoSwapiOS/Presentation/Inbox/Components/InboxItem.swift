//
//  InboxItem.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 08/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct InboxItem: View {
    let inbox: Inbox
    
    var message: String {
        inbox.lastMessage
    }
    
    var body: some View {
        HStack(spacing: 0) {
            AsyncImage(
                url: URL(string: inbox.sentToImageUrl),
                content: { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(
                            width: 30,
                            height: 30
                        )
                },
                placeholder: {
                    ZStack {
                        ProgressView()
                            .frame(alignment: .center)
                    }
                    .frame(
                        width: 30,
                        height: 30
                    )
                    .background(.ultraThinMaterial)
                }
            )
            .clipShape(Circle())
            
            Spacer()
                .frame(width: 32)
            
            VStack(alignment: .leading, spacing: 0) {
                Text("@\(inbox.sentToUsername)")
                    .font(.Caption1B)
                
                Text(message)
                    .font(.Caption1R)
                    .foregroundColor(.gray)
                    .lineLimit(1)
            }
            
            Spacer()
        }
        .padding(16)
        .contentShape(Rectangle())
    }
}

#Preview {
    InboxItem(
        inbox: Dummy().inboxes[0]
    )
}
