//
//  NavigationButtonOutlined.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 06/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct NavigationButtonOutlined: View {
    let label: String?
    var startIcon: String?
    var size: ButtonSize = .Default
    var background: Color?
    var color: Color = .Primary
    var textColor: Color?
    var icColor: Color?
    var fillWidth: Bool = false
    var isLoading: Bool = false
    
    private var buttonHeight: CGFloat {
        switch size {
        case .Small:
            return 24
        case .Default:
            return 48
        }
    }
    private var textFont: Font {
        switch size {
        case .Small:
            return .Caption2B
        case .Default:
            return .CalloutB
        }
    }
    private let iconSize: CGFloat = 20
    
    private var cornerRadius: CGFloat {
        switch size {
        case .Small:
            return 8
        case .Default:
            return 30
        }
    }
    
    private var bgColor: Color {
        background ?? .Background
    }
    
    private var contentColor: Color {
        .OnBackground
    }
    
    private let spacing: CGFloat = 8
    private let paddingHorizontal: CGFloat = 16
    private let paddingVertical: CGFloat = 8
    
    private var iconColor: Color {
        .OnBackground
    }
    
    var body: some View {
        HStack {
            if isLoading {
                ProgressView()
            } else {
                if let icon = startIcon {
                    Image(systemName: icon)
                        .resizable()
                        .scaledToFit()
                        .frame(
                            width: iconSize,
                            height: iconSize
                        )
                        .foregroundColor(iconColor)
                    
                    Spacer()
                        .frame(width: spacing)
                }
                
                if let label = label {
                    Text(label)
                        .font(textFont)
                }
            }
        }
        .frame(maxWidth: fillWidth ? .infinity : nil)
        .padding(.horizontal, paddingHorizontal)
        .padding(.vertical, paddingVertical)
        .frame(height: buttonHeight)
        .background(
            RoundedRectangle(cornerRadius: cornerRadius)
                .strokeBorder(
                    Color.Gray1,
                    style: StrokeStyle(
                        lineWidth: 1.0
                    )
                )
                .background(
                    RoundedRectangle(cornerRadius: cornerRadius)
                        .foregroundColor(bgColor)
                )
        )
        .foregroundColor(contentColor)
    }
}

#Preview {
    NavigationButtonOutlined(
        label: "Click"
    )
}
