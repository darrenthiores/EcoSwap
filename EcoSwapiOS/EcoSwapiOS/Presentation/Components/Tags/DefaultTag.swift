//
//  DefaultTag.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DefaultTag: View {
    let label: String
    let onClick: () -> Void
    var selected: Bool = false
    var startIcon: String?
    var endIcon: String?
    var selectedIconColor: Color {
        .OnSecondary
    }
    var unselectedIconColor: Color {
        .gray
    }
    var disabled: Bool = false
    
    @Environment(\.colorScheme) var colorScheme
    
    private var textFont: Font {
        selected ? .Caption2B : .Caption2R
    }
    
    private var iconColor: Color {
        if disabled {
            return .gray
        } else {
            if selected {
                return selectedIconColor
            } else {
                return unselectedIconColor
            }
        }
    }
    
    private var textColor: Color {
        if disabled {
            return .gray
        } else {
            if selected {
                return .OnPrimary
            } else {
                return .OnBackground
            }
        }
    }
    
    private var bgColor: Color {
        if disabled {
            return (colorScheme == .dark ? Color.gray : Color.Gray1)
        } else {
            if selected {
                return .Primary
            } else {
                return (colorScheme == .dark ? Color.gray : Color.Gray1)
            }
        }
    }
    
    private var lineWidth: CGFloat {
        if disabled {
            return 1.0
        } else {
            if selected {
                return 0.0
            } else {
                return 1.0
            }
        }
    }
    
    var body: some View {
        Button {
            onClick()
        } label: {
            HStack {
                if let icon = startIcon {
                    Image(systemName: icon)
                        .resizable()
                        .scaledToFit()
                        .frame(
                            width: 10,
                            height: 10
                        )
                        .foregroundColor(iconColor)
                    
                    Spacer()
                        .frame(width: 4)
                }
                
                Text(label)
                    .font(textFont)
                    .foregroundColor(textColor)
                
                if let icon = endIcon {
                    Spacer()
                        .frame(width: 4)
                    
                    Image(systemName: icon)
                        .resizable()
                        .scaledToFit()
                        .frame(
                            width: 10,
                            height: 10
                        )
                        .foregroundColor(iconColor)
                }
            }
            .padding(.horizontal, 10)
            .frame(height: 24)
            .background(
                RoundedRectangle(cornerRadius: 100)
                    .strokeBorder(
                        Color.Gray1,
                        style: StrokeStyle(
                            lineWidth: lineWidth
                        )
                    )
                    .background(
                        RoundedRectangle(cornerRadius: 100)
                            .foregroundColor(bgColor)
                    )
            )
        }
        .buttonStyle(.plain)
    }
}

#Preview {
    DefaultTag(
        label: "Test",
        onClick: {  },
        selected: true,
        startIcon: "house",
        disabled: true
    )
}
