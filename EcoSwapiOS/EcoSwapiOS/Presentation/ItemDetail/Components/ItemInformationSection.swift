//
//  ItemInformationSection.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ItemInformationSection: View {
    let item: Item?
    let user: User?
    let isExpanded: Bool
    let onToggleExpand: () -> Void
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text(item?.name ?? "")
                .font(.Title3B)
            
            Spacer()
                .frame(height: 8)
            
            HorizontalUserCard(user: user)
            
            Group {
                Spacer()
                    .frame(height: 16)
                
                Text("Description Product")
                    .font(.CalloutB)
                
                Spacer()
                    .frame(height: 8)
                
                Text(item?.description_ ?? "")
                    .font(.FootnoteR)
                    .lineLimit(
                        isExpanded ? nil : 5
                    )
                
                Spacer()
                    .frame(height: 4)
                
                Button {
                    onToggleExpand()
                } label: {
                    Text(
                        isExpanded ? "See Less" : "See More"
                    )
                    .font(.FootnoteB)
                    .foregroundColor(.Primary)
                }
                .buttonStyle(.plain)
            }
        }
        .padding(.horizontal, 24)
    }
}

#Preview {
    ItemInformationSection(
        item: Dummy().items[0],
        user: Dummy().user,
        isExpanded: false,
        onToggleExpand: {  }
    )
}
