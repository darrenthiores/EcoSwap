//
//  CarbonCategoryDropDown.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 11/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CarbonCategoryDropDown: View {
    var categories: [CarbonCategory] = Constant().carbonCategories
    let category: CarbonCategory?
    let isOpen: Bool
    let selectCategory: (CarbonCategory) -> Void
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
        .simultaneousGesture(
            TapGesture().onEnded {
                toggleIsOpen()
            }
        )
    }
}

#Preview {
    CarbonCategoryDropDown(
        category: nil,
        isOpen: false,
        selectCategory: { _ in },
        toggleIsOpen: {  }
    )
}
