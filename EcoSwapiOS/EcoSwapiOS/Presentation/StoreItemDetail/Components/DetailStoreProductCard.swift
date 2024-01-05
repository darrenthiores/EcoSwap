//
//  DetailStoreProductCard.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DetailStoreProductCard: View {
    let item: StoreItem?
    
    private var category: ItemCategory? {
        Constant()
            .categoryById(
                id: item?.categoryId ?? ""
            )
    }
    
    var body: some View {
        HStack(alignment: .center, spacing: 4) {
            Spacer()
            
            HStack(alignment: .center, spacing: 4) {
                Text("Category :")
                    .font(.FootnoteR)
                    .foregroundColor(.gray)
                
                Spacer()
                
                Text(category?.display ?? "")
                    .font(.FootnoteB)
                    .foregroundColor(.Primary)
            }
            
            Divider()
                .fixedSize()
            
            HStack(alignment: .center, spacing: 4) {
                Text("Rating :")
                    .font(.FootnoteR)
                    .foregroundColor(.gray)
                
                Spacer()
                
                Text(String(item?.rating ?? 0.0))
                    .font(.FootnoteB)
                    .foregroundColor(.Primary)
            }
            
            Spacer()
        }
        .padding(8)
        .background(
            Color.Primary
                .opacity(0.4)
                .cornerRadius(8)
        )
        .clipShape(
            RoundedRectangle(cornerRadius: 8)
        )
        .overlay {
            RoundedRectangle(cornerRadius: 8)
                .stroke(.gray, lineWidth: 1)
        }
        .background(
            Color.Background
                .clipShape(
                    RoundedRectangle(cornerRadius: 8)
                )
                .shadow(radius: 1)
        )
        
    }
}

#Preview {
    DetailStoreProductCard(
        item: Dummy().storeItems[0]
    )
}
