//
//  StoreList.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct StoreList: View {
    let stores: [Store]
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
        ScrollView {
            Spacer()
                .frame(height: spaceToTop)
            
            LazyVGrid(
                columns: columns,
                spacing: 8
            ) {
                ForEach(stores, id: \.id) { store in
                    NavigationLink {
                        StoreProfileScreen(storeId: store.id)
                    } label: {
                        FixedStoreCard(
                            store: store,
                            geo: geo
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
            }
            
            Spacer()
                .frame(height: spaceToBottom)
        }
        .scrollIndicators(.hidden)
    }
}

#Preview {
    GeometryReader { geo in
        StoreList(
            stores: Dummy().stores,
            geo: geo,
            onAppear: { }
        )
    }
}
