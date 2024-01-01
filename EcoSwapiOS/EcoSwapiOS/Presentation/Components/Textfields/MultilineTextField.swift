//
//  MultilineTextField.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MultilineTextField: View {
    @Binding var text: String
    let placeholder: String
    var disabled: Bool = false
    var startIcon: String?
    var endIcon: String?
    var isError: Bool = false
    var lineLimit: Int = 1
    var reserved: Bool = false
    var onEndIconClick: () -> Void = {  }
    var onFocusChange: (Bool) -> Void = { _ in }
    var onSubmit: () -> Void = {  }
    
    @FocusState private var textFieldFocus: Bool
    @Environment(\.colorScheme) var colorScheme
    
    private let textFont: Font = .SubHeadlineR
    private let iconSize: CGFloat = 20
    private let textFieldHeight: CGFloat = 48
    
    var borderColor: Color {
        isError ? .red : (textFieldFocus ? .Secondary : .gray)
    }
    
    var iconColor: Color {
        disabled ? .gray : .OnBackground
    }
    
    var disabledColor: Color {
        disabled ? (colorScheme == .dark ? .gray : .Gray1) : Color(UIColor.systemBackground)
    }
    
    var body: some View {
        HStack {
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
                    .frame(width: 8)
            }
            
            TextField(
                placeholder,
                text: $text,
                axis: .vertical
            )
            .font(textFont)
            .focused($textFieldFocus)
            .lineLimit(lineLimit, reservesSpace: reserved)
            .onChange(of: textFieldFocus) { isFocused in
                onFocusChange(isFocused)
            }
            .onSubmit {
                textFieldFocus = false
                onSubmit()
            }
            
            if let icon = endIcon {
                Spacer()
                    .frame(width: 8)
                
                Button {
                    onEndIconClick()
                } label: {
                    Image(systemName: icon)
                        .resizable()
                        .scaledToFit()
                        .frame(
                            width: iconSize,
                            height: iconSize
                        )
                        .foregroundColor(iconColor)
                }
                .buttonStyle(.plain)
            }
        }
        .tint(
            disabled ? .Gray1 : .OnBackground
        )
        .padding(.horizontal, 16)
        .frame(height: ((CGFloat(lineLimit) * textFieldHeight) * (lineLimit == 1 ? 1 : 2/3)))
        .background(
            disabledColor
        )
        .overlay(
            RoundedRectangle(cornerRadius: 8)
                .strokeBorder(
                    borderColor,
                    style: StrokeStyle(lineWidth: 1.0)
                )
        )
        .cornerRadius(8)
        .disabled(disabled)
    }
}

#Preview {
    MultilineTextField(
        text: .constant("abgabvabbaann\nabababababbababa\naaaaa"),
        placeholder: "Input Field",
        lineLimit: 3
    )
}
