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
        Int(((value / total) * 100))
    }
    
    @State var angleRatio: CGFloat = 0
    
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
        .onAppear {
            angleRatio = 0
            
            withAnimation {
                angleRatio = (value > 0 && total > 0) ? CGFloat(value/total) : 0
            }
        }
    }
}

#Preview {
    ProgressBarInfo(
        value: 200.0,
        total: 300.0,
        color: .Secondary
    )
    .frame(
        width: 60,
        height: 60
    )
}
