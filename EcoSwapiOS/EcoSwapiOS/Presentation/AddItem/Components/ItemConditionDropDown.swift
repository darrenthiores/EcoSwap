//
//  ItemConditionDropDown.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 08/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ItemConditionDropDown: View {
    var conditions: [ItemCondition] = Constant().conditions
    let condition: ItemCondition?
    let isOpen: Bool
    let selectCondition: (ItemCondition) -> Void
    let toggleIsOpen: () -> Void
    
    var body: some View {
        Menu {
            VStack {
                ForEach(
                    conditions,
                    id: \.id
                ) { condition in
                    Button {
                        selectCondition(condition)
                    } label: {
                        Text(condition.display)
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
                Text(condition?.display ?? "Select Condition")
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
    ItemConditionDropDown(
        conditions: Constant().conditions,
        condition: nil,
        isOpen: false,
        selectCondition: { _ in },
        toggleIsOpen: {  }
    )
}
