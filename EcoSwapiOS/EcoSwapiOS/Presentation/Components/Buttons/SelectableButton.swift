//
//  SelectableButton.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 11/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct SelectableButton: View {
    let text: String
    let value: String?
    let onClick: () -> Void
    
    var body: some View {
        Button {
            onClick()
        } label: {
            HStack {
                Text(value ?? text)
                    .font(.SubHeadlineR)
                
                Spacer()
                
                Image(systemName: "chevron.forward")
            }
            .padding()
            .background(
                Rectangle()
                    .fill(
                        Color.Background
                    )
                    .cornerRadius(8)
                    .shadow(radius: 1)
            )
        }
        .buttonStyle(.plain)
    }
}

#Preview {
    SelectableButton(
        text: "Select Activity",
        value: nil,
        onClick: { }
    )
}
