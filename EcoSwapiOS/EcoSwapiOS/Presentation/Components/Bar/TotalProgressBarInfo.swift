//
//  TotalProgressBarInfo.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 09/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct TotalProgressBarInfo: View {
    let values: [Double]
    let total: Double
    let colors: [Color]
    var strokeWidth: CGFloat = 56
    
    @State var angleRatioTransport: CGFloat = 0
    @State var angleRatioEnergy: CGFloat = 0
    @State var angleRatioChallenge: CGFloat = 0
    
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
                
                ProgressArc(
                    width: geo.size.width,
                    height: geo.size.height,
                    strokeWidth: strokeWidth,
                    startAngle: .degrees(-90),
                    endAngle: .degrees(
                        360 * angleRatioTransport - 90.0
                    )
                )
                .foregroundColor(colors[0])
                
                ProgressArc(
                    width: geo.size.width,
                    height: geo.size.height,
                    strokeWidth: strokeWidth,
                    startAngle: .degrees(-90.0 + (360 * angleRatioTransport)),
                    endAngle: .degrees(
                        360 * angleRatioEnergy - 90.0 + (360 * angleRatioTransport)
                    )
                )
                .foregroundColor(colors[1])
                
                ProgressArc(
                    width: geo.size.width,
                    height: geo.size.height,
                    strokeWidth: strokeWidth,
                    startAngle: .degrees(-90.0 + (360 * angleRatioTransport) + (360 * angleRatioEnergy)),
                    endAngle: .degrees(
                        360 * angleRatioChallenge - 90.0 + (360 * angleRatioTransport) + (360 * angleRatioEnergy)
                    )
                )
                .foregroundColor(colors[2])
            }
        }
        .padding(strokeWidth/2)
        .onAppear {
            angleRatioTransport = 0
            angleRatioEnergy = 0
            angleRatioChallenge = 0
            
            withAnimation {
                angleRatioTransport = (values[0] > 0 && total > 0) ? CGFloat(values[0]/total) : 0
                
                angleRatioEnergy = (values[1] > 0 && total > 0) ? CGFloat(values[1]/total) : 0
                
                angleRatioChallenge = (values[2] > 0 && total > 0) ? CGFloat(values[2]/total) : 0
            }
        }
    }
}

#Preview {
    TotalProgressBarInfo(
        values: [
            300.0, 300.0, 300.0
        ],
        total: 900.0,
        colors: [
            .blue, .green, .red
        ]
    )
}
