//
//  ProgressArc.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 09/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ProgressArc: Shape {
    let width: CGFloat
    let height: CGFloat
    let strokeWidth: CGFloat
    let startAngle: Angle
    let endAngle: Angle
    
    func path(in rect: CGRect) -> Path {
        var p = Path()

        p.addArc(
            center: CGPoint(
                x: width / 2,
                y: height / 2
            ),
            radius: min(width/2, height/2),
            startAngle: startAngle,
            endAngle: endAngle,
            clockwise: false
        )
        
        return p.strokedPath(
            .init(
                lineWidth: strokeWidth
            )
        )
    }
}
