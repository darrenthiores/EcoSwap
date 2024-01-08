//
//  MessageHeader.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 08/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct MessageHeader: View {
    let title: String
    let onBackClick: () -> Void
    let onSettingClick: () -> Void
    
    var body: some View {
        HStack {
            MessageCircleButton(
                image: "arrow.left",
                onClick: onBackClick
            )
            
            Spacer()
            
            Text(title)
                .font(.HeadlineB)
                .lineLimit(1)
                .padding(.horizontal, 32)
                .foregroundColor(.OnPrimary)
            
            Spacer()
            
            MessageCircleButton(
                image: "ellipsis",
                onClick: onSettingClick
            )
        }
        .padding(16)
        .background(Color.Primary)
    }
}

#Preview {
    MessageHeader(
        title: "My Profile",
        onBackClick: {  },
        onSettingClick: {  }
    )
}
