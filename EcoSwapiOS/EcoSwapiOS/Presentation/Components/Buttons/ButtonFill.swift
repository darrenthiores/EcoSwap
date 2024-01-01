//
//  ButtonFill.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ButtonFill: View {
    let label: String?
    let onClick: () -> Void
    var startIcon: String?
    var disabled: Bool = false
    var size: ButtonSize = .Default
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
        disabled ? .Gray1 : (isLoading ? .Gray1 : color)
    }
    
    private var contentColor: Color {
        textColor ?? (disabled ? .OnPrimary : .OnPrimary)
    }
    
    private let spacing: CGFloat = 8
    private let paddingHorizontal: CGFloat = 16
    private let paddingVertical: CGFloat = 8
    
    private var iconColor: Color {
        icColor ?? (disabled ? .Gray2 : .OnBackground)
    }
    
    var body: some View {
        Button {
            onClick()
        } label: {
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
                        
                        if label != nil {
                            Spacer()
                                .frame(width: spacing)
                        }
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
                    .foregroundColor(bgColor)
            )
            .foregroundColor(contentColor)
        }
        .buttonStyle(.plain)
        .disabled(disabled)
    }
}

#Preview("Default Button") {
    ButtonFill(
        label: "Click",
        onClick: {  },
        fillWidth: true,
        isLoading: false
    )
}

#Preview("Small Button") {
    ButtonFill(
        label: "Click",
        onClick: {  },
        size: .Small,
        fillWidth: true,
        isLoading: false
    )
}
