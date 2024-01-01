//
//  DistanceLabel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DistanceLabel: View {
    let distance: String
    
    var body: some View {
        HStack(spacing: 4) {
            Image(systemName: "mappin")
                .resizable()
                .scaledToFit()
                .frame(
                    width: 10,
                    height: 10
                )
                .foregroundColor(.Primary)
            
            Text(distance)
                .font(.Caption2B)
                .foregroundColor(.Primary)
        }
        .padding(.horizontal, 10)
        .padding(.vertical, 4)
        .background(
            RoundedRectangle(cornerRadius: 10)
                .strokeBorder(
                    Color.Gray1,
                    style: StrokeStyle(
                        lineWidth: 1.0
                    )
                )
                .background(
                    RoundedRectangle(cornerRadius: 10)
                        .foregroundColor(.Background)
                )
        )
    }
}

#Preview {
    DistanceLabel(
        distance: "2,6 km"
    )
}
