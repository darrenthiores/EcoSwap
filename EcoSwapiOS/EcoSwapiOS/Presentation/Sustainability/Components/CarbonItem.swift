//
//  CarbonItem.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 10/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CarbonItem: View {
    let carbon: FootPrint.Carbon
    
    var carbonCategory: CarbonCategory? {
        Constant().carbonCategoryById(
            id: carbon.categoryId
        )
    }
    var color: Color {
        .getProgressColorByCategoryId(
            categoryId: carbon.categoryId
        ) ?? .DefaultProgressColor
    }
    
    var body: some View {
        HStack(alignment: .center, spacing: 8) {
            ZStack {
                color
            }
            .frame(
                width: 12,
                height: 12
            )
            .clipShape(Circle())
            .overlay(
                Circle()
                    .stroke(Color.White, lineWidth: 1)
            )
            .background(
                Color.Background
                    .clipShape(Circle())
                    .shadow(radius: 1)
            )
            
            Text(carbonCategory?.display ?? "")
                .font(.SubHeadlineB)
            
            Spacer()
            
            HStack(alignment: .center, spacing: 0) {
                Text(String(carbon.total))
                    .font(.Caption1B)
                    .foregroundColor(.Primary)
                
                Text("CO2")
                    .font(.Caption1R)
            }
        }
        .padding(.vertical, 12)
    }
}

#Preview {
    CarbonItem(
        carbon: .init(
            categoryId: "1",
            total: 100.0
        )
    )
}
