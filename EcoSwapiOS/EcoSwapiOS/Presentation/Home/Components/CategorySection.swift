//
//  CategorySection.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CategorySection: View {
    @Binding var currentTab: Int
    var viewAll: Bool = false
    let onItemClick: (ItemCategory) -> Void
    let onToggleViewAll: () -> Void
    let geo: GeometryProxy
    
    var pageCount: Int {
        Int(
            ceil(
                Double(
                    Constant().categories.count
                ) / 4
            )
        )
    }
    
    var body: some View {
        VStack(
            alignment: .center,
            spacing: 16
        ) {
            SectionHeader(
                title: "Items Category",
                endAction: viewAll ? "View Less" : "View All",
                onActionClick: onToggleViewAll
            )
            .padding(.horizontal, 24)
            
            if !viewAll {
                TabView(selection: $currentTab) {
                    ForEach(0..<pageCount, id: \.self) { page in
                        let index = page + 1
                        let lastIndex = (index * 4) - 1
                        let firstIndexCount = (lastIndex + 1 - 4)
                        let firstIndex = (firstIndexCount < 0) ? 0 : firstIndexCount
                        let categories = (
                            (Constant().categories.count - 1) < lastIndex
                        ) ? Constant().categories[firstIndex...(Constant().categories.count - 1)] : Constant().categories[firstIndex...lastIndex]
                        
                        HStack(spacing: 0) {
                            Spacer()
                                .frame(width: 24)
                            
                            ForEach(Array(categories.enumerated()), id: \.offset) { index, category in
                                Button {
                                    onItemClick(category)
                                } label: {
                                    CategoryItem(
                                        category: category,
                                        geo: geo
                                    )
                                }
                                .buttonStyle(.plain)
                                
                                if index != (categories.count - 1) {
                                    Spacer()
                                }
                            }
                            
                            Spacer()
                            
                            Spacer()
                                .frame(width: 24)
                        }
                        .tag(page)
                    }
                }
                .frame(height: 100)
                .tabViewStyle(.page(indexDisplayMode: .never))
                
                PageIndicator(
                    currentPage: currentTab,
                    pageCount: pageCount
                )
            } else {
                ForEach(0..<pageCount, id: \.self) { page in
                    let index = page + 1
                    let lastIndex = (index * 4) - 1
                    let firstIndexCount = (lastIndex + 1 - 4)
                    let firstIndex = (firstIndexCount < 0) ? 0 : firstIndexCount
                    let categories = (
                        (Constant().categories.count - 1) < lastIndex
                    ) ? Constant().categories[firstIndex...(Constant().categories.count - 1)] : Constant().categories[firstIndex...lastIndex]
                    
                    HStack(spacing: 0) {
                        ForEach(Array(categories.enumerated()), id: \.offset) { index, category in
                            Button {
                                onItemClick(category)
                            } label: {
                                CategoryItem(
                                    category: category,
                                    geo: geo
                                )
                            }
                            .buttonStyle(.plain)
                            
                            if index != (categories.count - 1) {
                                Spacer()
                            }
                        }
                        
                        Spacer()
                    }
                }
                .padding(.horizontal, 24)
            }
        }
    }
}

#Preview {
    GeometryReader { geo in
        CategorySection(
            currentTab: .constant(0),
            onItemClick: { _ in },
            onToggleViewAll: {  },
            geo: geo
        )
    }
}
