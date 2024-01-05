//
//  ItemSection.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 03/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ItemSection: View {
    let title: String
    let items: [Item]
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
                HorizontalItemPlaceholder()
            } else {
                HorizontalItemList(
                    items: items,
                    onAppear: onAppear
                )
            }
        }
    }
}

#Preview {
    ItemSection(
        title: "Recommended for you",
        items: Dummy().items,
        onSeeAllClick: {  },
        onAppear: {  },
        isLoading: false
    )
}
