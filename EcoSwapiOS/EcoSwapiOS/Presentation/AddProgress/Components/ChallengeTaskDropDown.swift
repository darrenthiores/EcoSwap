//
//  ChallengeTaskDropDown.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 11/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ChallengeTaskDropDown: View {
    let tasks: [Challenge.Task]
    let task: Challenge.Task?
    let isOpen: Bool
    let selectTask: (Challenge.Task) -> Void
    let toggleIsOpen: () -> Void
    
    var body: some View {
        Menu {
            VStack {
                ForEach(
                    tasks,
                    id: \.id
                ) { task in
                    Button {
                        selectTask(task)
                    } label: {
                        Text(task.task)
                            .font(.SubHeadlineR)
                    }
                    .buttonStyle(.plain)
                }
            }
        } label: {
            HStack {
                Text(task?.task ?? "Select Task")
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
    ChallengeTaskDropDown(
        tasks: Dummy().challenges[0].tasks,
        task: nil,
        isOpen: false,
        selectTask: { _ in },
        toggleIsOpen: {  }
    )
}
