//
//  SettingHeader.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SettingHeader: View {
    let title: String
    let onBackClick: () -> Void
    let onSettingClick: () -> Void
    
    var body: some View {
        HStack {
            CircleButton(
                image: "arrow.left",
                onClick: onBackClick
            )
            
            Spacer()
            
            Text(title)
                .font(.HeadlineB)
                .lineLimit(1)
                .padding(.horizontal, 32)
            
            Spacer()
            
            CircleButton(
                image: "ellipsis",
                onClick: onSettingClick
            )
        }
        .frame(height: 56)
    }
}

#Preview {
    SettingHeader(
        title: "My Profile",
        onBackClick: {  },
        onSettingClick: {  }
    )
}
