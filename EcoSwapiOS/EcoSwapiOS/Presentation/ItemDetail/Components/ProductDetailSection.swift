//
//  ProductDetailSection.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProductDetailSection: View {
    let item: Item?
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text("Detail Product")
                .font(.CalloutB)
            
            DetailProductCard(item: item)
        }
        .padding(.horizontal, 24)
    }
}

#Preview {
    ProductDetailSection(
        item: Dummy().items[0]
    )
}
