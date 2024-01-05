//
//  HomeHeader.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 03/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct HomeHeader: View {
    @Binding var searchText: String
    let onSearch: () -> Void
    
    var body: some View {
        VStack(spacing: 12) {
            HStack(alignment: .center) {
                Image("logo_horizontal")
                    .resizable()
                    .scaledToFit()
                    .frame(
                        height: 30
                    )
                
                Spacer()
                
                NavigationLink {
                    Text("Notification")
                } label: {
                    ZStack(alignment: .topTrailing) {
                        Image(systemName:"bell.fill")
                            .resizable()
                            .scaledToFit()
                            .frame(
                                width: 20,
                                height: 20
                            )
                            .foregroundColor(.White)
                    }
                    .frame(
                        width: 34,
                        height: 34
                    )
                    .clipShape(Circle())
                    .background(
                        Color.white
                            .opacity(0.2)
                            .clipShape(Circle())
                    )
                }
                .buttonStyle(.plain)
            }
            
            DefaultTextField(
                text: $searchText,
                placeholder: "Search for swap and trade items",
                startIcon: "magnifyingglass",
                onSubmit: onSearch
            )
        }
        .padding(.horizontal, 24)
        .padding(.vertical, 12)
        .background(
            Color.Primary
        )
    }
}

#Preview {
    HomeHeader(
        searchText: .constant(""),
        onSearch: {  }
    )
}
