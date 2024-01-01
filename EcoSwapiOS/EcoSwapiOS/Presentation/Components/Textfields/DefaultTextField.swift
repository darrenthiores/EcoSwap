//
//  DefaultTextField.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DefaultTextField: View {
    @Binding var text: String
    let placeholder: String
    var background: Color = .Background
    var disabled: Bool = false
    var isError: Bool = false
    var startIcon: String?
    var endIcon: String?
    var lineLimit: Int = 1
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
                text: $text
            )
            .font(textFont)
            .focused($textFieldFocus)
            .onChange(of: textFieldFocus) { isFocused in
                onFocusChange(isFocused)
            }
            .lineLimit(1)
            .onSubmit(onSubmit)
            
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
        .frame(height: textFieldHeight)
        .background(
            disabledColor
        )
        .overlay(
            RoundedRectangle(cornerRadius: 20)
                .strokeBorder(
                    borderColor,
                    style: StrokeStyle(lineWidth: 1.0)
                )
        )
        .cornerRadius(20)
        .disabled(disabled)
    }
}

#Preview {
    DefaultTextField(
        text: .constant("abgabvabbaann\nabababababbababa\naaaaajwjwnjjwaijwijowaijwijwijwaiwiaojwaidiwa"),
        placeholder: "Input Field"
    )
}
