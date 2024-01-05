//
//  DetailProductCard.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DetailProductCard: View {
    let item: Item?
    
    private var category: ItemCategory? {
        Constant()
            .categoryById(
                id: item?.categoryId ?? ""
            )
    }
    private var condition: ItemCondition? {
        Constant()
            .conditionById(
                id: item?.conditionId ?? ""
            )
    }
    
    var body: some View {
        VStack(spacing: 0) {
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
                    Text("Total :")
                        .font(.FootnoteR)
                        .foregroundColor(.gray)
                    
                    Spacer()
                    
                    Text(String(Int(item?.total ?? Int32(0))))
                        .font(.FootnoteB)
                        .foregroundColor(.Primary)
                }
                
                Spacer()
            }
            .padding(8)
            .background(
                Color.Primary
                    .opacity(0.4)
                    .cornerRadius(8, corners: [.topLeft,.topRight])
            )
            
            HStack(alignment: .center, spacing: 4) {
                Spacer()
                
                HStack(alignment: .center, spacing: 4) {
                    Text("Condition :")
                        .font(.FootnoteR)
                        .foregroundColor(.gray)
                    
                    Spacer()
                    
                    Text(condition?.display ?? "")
                        .font(.FootnoteB)
                        .foregroundColor(.Primary)
                }
                
                Divider()
                    .fixedSize()
                
                HStack(alignment: .center, spacing: 4) {
                    Text("Brand :")
                        .font(.FootnoteR)
                        .foregroundColor(.gray)
                    
                    Spacer()
                    
                    Text(item?.brand ?? "")
                        .font(.FootnoteB)
                        .foregroundColor(.Primary)
                }
                
                Spacer()
            }
            .padding(8)
            .background(
                Color.Primary
                    .opacity(0.2)
                    .cornerRadius(8, corners: [.bottomLeft, .bottomRight])
            )
        }
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
    DetailProductCard(
        item: Dummy().items[0]
    )
}
