//
//  CarbonActivityDropDown.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 11/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CarbonActivityDropDown: View {
    let activities: [CarbonActivity]
    let activity: CarbonActivity?
    let isOpen: Bool
    let selectActivity: (CarbonActivity) -> Void
    let toggleIsOpen: () -> Void
    
    var body: some View {
        Menu {
            VStack {
                ForEach(
                    activities,
                    id: \.id
                ) { activity in
                    Button {
                        selectActivity(activity)
                    } label: {
                        Text(activity.display)
                            .font(.SubHeadlineR)
                    }
                    .buttonStyle(.plain)
                }
            }
        } label: {
            HStack {
                Text(activity?.display ?? "Select Activity")
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
    CarbonActivityDropDown(
        activities: Constant().energyActivities,
        activity: nil,
        isOpen: false,
        selectActivity: { _ in },
        toggleIsOpen: {  }
    )
}
