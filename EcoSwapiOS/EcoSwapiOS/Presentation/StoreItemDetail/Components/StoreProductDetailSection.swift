//
//  StoreProductDetailSection.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct StoreProductDetailSection: View {
    let item: StoreItem?
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text("Detail Product")
                .font(.CalloutB)
            
            DetailStoreProductCard(item: item)
        }
        .padding(.horizontal, 24)
    }
}

#Preview {
    StoreProductDetailSection(
        item: Dummy().storeItems[0]
    )
}
