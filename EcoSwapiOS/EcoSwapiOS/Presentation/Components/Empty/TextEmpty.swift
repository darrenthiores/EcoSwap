//
//  TextEmpty.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct TextEmpty: View {
    let title: String
    let description: String
    
    var body: some View {
        VStack(spacing: 16) {
            Text(title)
                .font(.SubHeadlineB)
                //.foregroundColor(.neutral500)
            
            Text(description)
                .font(.Caption1R)
                //.foregroundColor(.neutral500)
                .multilineTextAlignment(.center)
            
            Spacer()
        }
        .padding(60)
    }
}

#Preview {
    TextEmpty(
        title: "no items posted",
        description: "post item and see it here"
    )
}
