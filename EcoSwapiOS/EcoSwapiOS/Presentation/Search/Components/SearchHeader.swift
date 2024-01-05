//
//  SearchHeader.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchHeader: View {
    @Binding var searchText: String
    let selectedCategory: ItemCategory?
    let currentTab: SearchTab
    let onSearch: () -> Void
    let onSelectCategory: (ItemCategory?) -> Void
    let onSelectTab: (SearchTab) -> Void
    let onBackClick: () -> Void
    
    var body: some View {
        VStack(spacing: 0) {
            HStack(alignment: .center, spacing: 8) {
                Button {
                    onBackClick()
                } label: {
                    Image(systemName: "arrow.left")
                        .resizable()
                        .scaledToFit()
                        .frame(
                            width: 24,
                            height: 24
                        )
                }
                .buttonStyle(.plain)
                
                DefaultTextField(
                    text: $searchText,
                    placeholder: "Search for swap and trade items",
                    startIcon: "magnifyingglass",
                    onSubmit: {
                        onSearch()
                    }
                )
            }
            .padding(.horizontal, 24)
            
            Spacer()
                .frame(height: 12)
            
            ScrollView(.horizontal, showsIndicators: false) {
                LazyHStack(spacing: 8) {
                    Spacer()
                        .frame(width: 16)
                    
                    DefaultTag(
                        label: "All Items",
                        onClick: {
                            onSelectCategory(nil)
                        },
                        selected: selectedCategory == nil
                    )
                    
                    ForEach(Constant().categories, id: \.id) { category in
                            DefaultTag(
                                label: category.display,
                                onClick: {
                                    onSelectCategory(category)
                                },
                                selected: selectedCategory?.id == category.id
                            )
                    }
                    
                    Spacer()
                        .frame(width: 16)
                }
                .frame(height: 24)
            }
            
            Spacer()
                .frame(height: 12)
            
            HStack(spacing: 0) {
                ForEach(SearchTab.entries, id: \.name) { tab in
                    TabButton(
                        title: tab.name,
                        isSelected: currentTab == tab,
                        onClick: {
                            onSelectTab(tab)
                        }
                    )
                }
            }
            
            Divider()
        }
        .padding(.top, 12)
    }
}

#Preview {
    SearchHeader(
        searchText: .constant(""),
        selectedCategory: nil,
        currentTab: .stores,
        onSearch: {  },
        onSelectCategory: { _ in },
        onSelectTab: { _ in },
        onBackClick: {  }
    )
}
