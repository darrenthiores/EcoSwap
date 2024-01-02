//
//  BoardingPagetab.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 01/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct BoardingPageTab: View {
    let currentPage: Int
    
    var body: some View {
        HStack(spacing: 8) {
            ForEach(1...3, id: \.self) { page in
                let color: Color = (currentPage == page) ? .White : .Gray2
                
                color
                    .frame(
                        width: 8,
                        height: 8
                    )
                    .clipShape(Circle())
            }
        }
        .padding(.horizontal, 12)
        .padding(.vertical, 8)
        .background(
            Color.Black
                .opacity(0.2)
                .clipShape(
                    .rect(
                        cornerRadius: 12
                    )
                )
        )
    }
}

#Preview {
    BoardingPageTab(
        currentPage: 1
    )
}
