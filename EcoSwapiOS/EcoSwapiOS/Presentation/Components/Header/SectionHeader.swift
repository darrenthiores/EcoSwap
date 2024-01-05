//
//  SectionHeader.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SectionHeader: View {
    let title: String
    var endAction: String?
    var onActionClick: () -> Void = {  }
    
    var body: some View {
        HStack(alignment: .center) {
            Text(title)
                .font(.CalloutB)
            
            Spacer()
            
            if let action = endAction {
                Button {
                    onActionClick()
                } label: {
                    Text(action)
                        .font(.CalloutR)
                        .foregroundColor(.Primary)
                }
                .buttonStyle(.plain)
            }
        }
        .frame(
            maxWidth: .infinity
        )
    }
}

#Preview {
    SectionHeader(
        title: "Items Category",
        endAction: "View All",
        onActionClick: {  }
    )
}
