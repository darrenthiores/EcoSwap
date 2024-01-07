//
//  ItemCategoryDropDown.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 07/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ItemCategoryDropDown: View {
    var categories: [ItemCategory] = Constant().categories
    let category: ItemCategory?
    let isOpen: Bool
    let selectCategory: (ItemCategory) -> Void
    let toggleIsOpen: () -> Void
    
    var body: some View {
        Menu {
            VStack {
                ForEach(
                    categories,
                    id: \.id
                ) { category in
                    Button {
                        selectCategory(category)
                    } label: {
                        Text(category.display)
                            .font(.SubHeadlineR)
                    }
                    .buttonStyle(.plain)
                }
            }
            .onAppear {
                toggleIsOpen()
            }
            .onDisappear {
                toggleIsOpen()
            }
        } label: {
            HStack {
                Text(category?.display ?? "Select Category")
                    .font(.SubHeadlineR)
                
                Spacer()
                
                Image(systemName: isOpen ? "chevron.up" : "chevron.down")
            }
            .padding()
            .background(
                Rectangle()
                    .fill(
                        Color.Background
                    )
                    .cornerRadius(8)
                    .shadow(radius: 1)
            )
        }
        .buttonStyle(.plain)
    }
}

#Preview {
    ItemCategoryDropDown(
        categories: Constant().categories,
        category: nil,
        isOpen: false,
        selectCategory: { _ in },
        toggleIsOpen: {  }
    )
}
