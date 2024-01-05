//
//  CategoryItem.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CategoryItem: View {
    let category: ItemCategory
    let geo: GeometryProxy
    
    var width: CGFloat {
        (geo.size.width / 4) - 24
    }
    
    var body: some View {
        VStack(alignment: .center, spacing: 4) {
            ZStack {
                Image(category.getImageName())
                    .resizable()
                    .scaledToFit()
                    .frame(
                        width: 36
                    )
            }
            .frame(
                width: 70,
                height: 70
            )
            .background(
                Color.Primary
                    .opacity(0.3)
            )
            .clipShape(Circle())
            
            Text(category.display)
                .font(.Caption1R)
                .lineLimit(1)
        }
        .frame(
            width: width
        )
    }
}

#Preview {
    GeometryReader { geo in
        CategoryItem(
            category: Constant().categories[0],
            geo: geo
        )
    }
}
