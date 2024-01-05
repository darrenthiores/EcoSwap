//
//  TabButton.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct TabButton: View {
    let title: String
    let isSelected: Bool
    let onClick: () -> Void
    
    var body: some View {
        VStack(alignment: .center, spacing: 0) {
            Button {
                withAnimation { onClick() }
            } label: {
                HStack {
                    Spacer()
                    
                    Text(title)
                        .font(.FootnoteR)
                    
                    Spacer()
                }
            }
            .contentShape(Rectangle())
            .padding(.vertical, 16)
            .foregroundColor(isSelected ? .Primary : .OnBackground)
                
            if isSelected {
                Color.Primary
                    .frame(height: 1)
            } else {
                Color.clear
                    .frame(height: 1)
            }
        }
    }
}

#Preview {
    TabButton(
        title: "Items",
        isSelected: true,
        onClick: {  }
    )
}
