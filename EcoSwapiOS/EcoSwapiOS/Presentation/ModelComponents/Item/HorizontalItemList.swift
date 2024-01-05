//
//  HorizontalItemList.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HorizontalItemList: View {
    let items: [Item]
    let onAppear: () -> Void
    var horizontalSpace: CGFloat = 24
    
    var body: some View {
        ScrollView(.horizontal) {
            LazyHStack(
                spacing: 8
            ) {
                Spacer()
                    .frame(width: horizontalSpace - 8)
                
                ForEach(items, id: \.id) { item in
                    NavigationLink {
                        Text("Item \(item.id)")
                    } label: {
                        ItemCard(
                            item: item
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
                
                Spacer()
                    .frame(width: horizontalSpace - 8)
            }
            .frame(height: 200 + 1)
        }
        .scrollIndicators(.hidden)
    }
}

#Preview {
    HorizontalItemList(
        items: Dummy().items,
        onAppear: {  }
    )
}
