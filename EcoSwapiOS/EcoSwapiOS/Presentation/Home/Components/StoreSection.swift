//
//  StoreSection.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 03/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct StoreSection: View {
    let title: String
    let stores: [Store]
    let onSeeAllClick: () -> Void
    let onAppear: () -> Void
    let isLoading: Bool
    
    var body: some View {
        VStack(spacing: 16) {
            SectionHeader(
                title: title,
                endAction: "See All",
                onActionClick: onSeeAllClick
            )
            .padding(.horizontal, 24)
            
            if isLoading {
                HorizontalStorePlaceholder()
            } else {
                HorizontalStoreList(
                    stores: stores,
                    onAppear: onAppear
                )
            }
        }
    }
}

#Preview {
    StoreSection(
        title: "Recommended for you",
        stores: Dummy().stores,
        onSeeAllClick: {  },
        onAppear: {  },
        isLoading: false
    )
}
