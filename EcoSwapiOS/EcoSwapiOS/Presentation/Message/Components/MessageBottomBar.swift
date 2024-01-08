//
//  MessageBottomBar.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 08/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct MessageBottomBar: View {
    @Binding var message: String
    let onAddPhotoClick: () -> Void
    let onSendClick: () -> Void
    
    @FocusState private var isFocused: Bool
    
    var body: some View {
        HStack(alignment: .bottom, spacing: 8) {
            MessageTextField(
                text: $message,
                textFieldFocus: $isFocused
            )
            
            Button {
                onAddPhotoClick()
            } label: {
                ZStack(alignment: .center) {
                    Image(systemName: "photo")
                        .resizable()
                        .scaledToFit()
                        .frame(
                            width: 18,
                            height: 18
                        )
                        .foregroundColor(.Primary)
                }
                .frame(
                    width: 40,
                    height: 40
                )
                .clipShape(Circle())
                .background(
                    Color.White
                        .clipShape(Circle())
                        .shadow(radius: 1)
                )
            }
            .buttonStyle(.plain)
            
            Button {
                isFocused = false
                onSendClick()
            } label: {
                ZStack(alignment: .center) {
                    Image(systemName: "paperplane.fill")
                        .resizable()
                        .scaledToFit()
                        .frame(
                            width: 18,
                            height: 18
                        )
                        .foregroundColor(.OnPrimary)
                }
                .frame(
                    width: 40,
                    height: 40
                )
                .clipShape(Circle())
                .background(
                    Color.Primary
                        .clipShape(Circle())
                        .shadow(radius: 1)
                )
            }
            .buttonStyle(.plain)
        }
        .padding(16)
    }
}

#Preview {
    MessageBottomBar(
        message: .constant(""),
        onAddPhotoClick: {  },
        onSendClick: {  }
    )
}
