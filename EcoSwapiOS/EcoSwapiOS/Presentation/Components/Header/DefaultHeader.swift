//
//  DefaultHeader.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DefaultHeader: View {
    let title: String
    let onBackClick: () -> Void
    
    var body: some View {
        ZStack {
            HStack {
                CircleButton(
                    image: "arrow.left",
                    onClick: onBackClick
                )
                
                Spacer()
            }
            
            HStack {
                Spacer()
                
                Text(title)
                    .font(.HeadlineB)
                    .lineLimit(1)
                
                Spacer()
            }
            .padding(.horizontal, 32)
        }
        .frame(height: 56)
    }
}

#Preview {
    DefaultHeader(
        title: "My Profile",
        onBackClick: {  }
    )
}
