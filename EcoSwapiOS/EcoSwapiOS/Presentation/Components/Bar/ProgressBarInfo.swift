//
//  ProgressBarInfo.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 09/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ProgressBarInfo: View {
    let value: Double
    let total: Double
    let color: Color
    var strokeWidth: CGFloat = 8
    
    var progress: Int {
        if value < total && total != 0.0 {
            Int(((value / total) * 100))
        } else {
            0
        }
    }
    
    var angleRatio: CGFloat {
        (value > 0 && total > 0) ? CGFloat(value/total) : 0
    }
    
    var body: some View {
        GeometryReader { geo in
            ZStack(alignment: .center) {
                Color.clear
                
                BackgroundArc(
                    width: geo.size.width,
                    height: geo.size.height,
                    strokeWidth: strokeWidth
                )
                .foregroundColor(.Primary.opacity(0.2))
                
                Text("\(progress)%")
                    .font(.Caption1B)
                
                ProgressArc(
                    width: geo.size.width,
                    height: geo.size.height,
                    strokeWidth: strokeWidth,
                    startAngle: .degrees(-90),
                    endAngle: .degrees(
                        360 * angleRatio - 90.0
                    )
                )
                .foregroundColor(color)
            }
        }
        .padding(strokeWidth/2)
    }
}

#Preview {
    ProgressBarInfo(
        value: 230.0,
        total: 300.0,
        color: .Secondary
    )
    .frame(
        width: 60,
        height: 60
    )
}
