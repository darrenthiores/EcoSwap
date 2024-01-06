//
//  ProfileBottomBar.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 06/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ProfileBottomBar: View {
    let onAddReviewClick: () -> Void
    
    var body: some View {
        HStack(spacing: 16) {
            NavigationLink {
                Text("Message")
            } label: {
                NavigationButtonOutlined(
                    label: "Message",
                    startIcon: "ellipsis.message.fill",
                    fillWidth: true
                )
            }
            .buttonStyle(.plain)
            
            ButtonFill(
                label: "Add Review",
                onClick: onAddReviewClick,
                startIcon: "pencil",
                fillWidth: true
            )
        }
        .padding(8)
        .background(Color.Background)
    }
}

#Preview {
    ProfileBottomBar(
        onAddReviewClick: {  }
    )
}
