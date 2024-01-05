//
//  ItemGrid.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ItemGrid: View {
    let items: [Item]
    let geo: GeometryProxy
    let onAppear: () -> Void
    var spaceToTop: CGFloat = 0
    var spaceToBottom: CGFloat = 0
    
    private var itemWidth: CGFloat {
        geo.size.width / 2 - 24 - (12/2)
    }
    private var columns: [GridItem] {
       [
            GridItem(.fixed(itemWidth)),
            GridItem(.fixed(itemWidth))
       ]
    }
    
    var body: some View {
        LazyVGrid(
            columns: columns,
            spacing: 8
        ) {
            ForEach(items, id: \.id) { item in
                NavigationLink {
                    ItemDetailScreen(id: item.id)
                } label: {
                    FixedItemCard(
                        item: item,
                        geo: geo
                    )
                }
                .buttonStyle(.plain)
                .onAppear {
                    let index = items.firstIndex(
                        where: { items in items.id == item.id }
                    )
                    
                    if let index = index {
                        let count = index + 1
                        
                        if count == items.count
                            && count % 15 == 0{
                            onAppear()
                        }
                    }
                }
            }
        }
        .padding(.top, spaceToTop)
        .padding(.bottom, spaceToBottom)
    }
}

#Preview {
    GeometryReader { geo in
        ItemGrid(
            items: Dummy().items,
            geo: geo,
            onAppear: { }
        )
    }
}
