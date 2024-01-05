//
//  HorizontalStoreList.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HorizontalStoreList: View {
    let stores: [Store]
    let onAppear: () -> Void
    var horizontalSpace: CGFloat = 24
    
    var body: some View {
        ScrollView(.horizontal) {
            LazyHStack(
                spacing: 8
            ) {
                Spacer()
                    .frame(width: horizontalSpace - 8)
                
                ForEach(stores, id: \.id) { store in
                    NavigationLink {
                        Text("Store \(store.id)")
                    } label: {
                        StoreCard(
                            store: store
                        )
                    }
                    .buttonStyle(.plain)
                    .onAppear {
                        let index = stores.firstIndex(
                            where: { stores in stores.id == store.id }
                        )
                        
                        if let index = index {
                            let count = index + 1
                            
                            if count == stores.count
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
    HorizontalStoreList(
        stores: Dummy().stores,
        onAppear: {  }
    )
}
