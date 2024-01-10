//
//  TaskItem.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 10/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TaskItem: View {
    let task: Challenge.Task
    
    var body: some View {
        HStack(alignment: .top, spacing: 8) {
            Image(systemName: "circle.fill")
                .resizable()
                .scaledToFit()
                .frame(
                    width: 4,
                    height: 4
                )
                .padding(.top, 6)
            
            Text(task.task)
                .font(.FootnoteR)
            
            Spacer()
        }
        .padding(.vertical, 2)
    }
}

#Preview {
    TaskItem(
        task: Dummy().challenges[0].tasks[0]
    )
}
