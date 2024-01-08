//
//  MessageCircleButton.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 08/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct MessageCircleButton: View {
    let image: String
    let onClick: () -> Void
    
    var body: some View {
        Button {
            onClick()
        } label: {
            ZStack(alignment: .center) {
                Image(systemName: image)
                    .resizable()
                    .scaledToFit()
                    .frame(
                        width: 20,
                        height: 20
                    )
                    .foregroundColor(.OnPrimary)
            }
            .frame(
                width: 34,
                height: 34
            )
            .clipShape(Circle())
            .background(
                Color.White
                    .opacity(0.2)
                    .clipShape(Circle())
                    .shadow(radius: 1)
            )
        }
        .buttonStyle(.plain)
    }
}

#Preview {
    MessageCircleButton(
        image: "house.fill",
        onClick: {  }
    )
}
