//
//  CircleButton.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CircleButton: View {
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
                    .foregroundColor(.Primary)
            }
            .frame(
                width: 34,
                height: 34
            )
            .clipShape(Circle())
            .background(
                Color.White
                    .clipShape(Circle())
            )
            .shadow(radius: 1)
        }
        .buttonStyle(.plain)
    }
}

#Preview {
    CircleButton(
        image: "house.fill",
        onClick: {  }
    )
}
