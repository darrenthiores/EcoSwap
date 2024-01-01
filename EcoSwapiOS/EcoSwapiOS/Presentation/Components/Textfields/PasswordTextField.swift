//
//  PasswordTextField.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PasswordTextField: View {
    @Binding var text: String
    @Binding var passwordVisible: Bool
    let placeholder: String
    var background: Color = .Background
    var disabled: Bool = false
    var onFocusChange: (Bool) -> Void = { _ in }
    var onSubmit: () -> Void = {  }
    
    @FocusState var textFieldFocus: Bool
    @Environment(\.colorScheme) var colorScheme
    
    private let textFont: Font = .SubHeadlineR
    private let iconSize: CGFloat = 20
    private let textFieldHeight: CGFloat = 48
    var borderColor: Color {
        textFieldFocus ? .Secondary : .gray
    }
    
    var iconColor: Color {
        disabled ? .gray : .Primary
    }
    
    var disabledColor: Color {
        disabled ? (colorScheme == .dark ? .gray : .Gray1) : Color(UIColor.systemBackground)
    }
    
    var body: some View {
        HStack {
            Group{
                if passwordVisible {
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
                } else {
                    SecureField(
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
                }
            }
            .animation(.easeInOut(duration: 0.2), value: passwordVisible)
            
            Spacer()
                .frame(width: 8)
            
            Button {
                passwordVisible.toggle()
            } label: {
                Image(systemName: !passwordVisible ? "eye.slash" : "eye")
                    .resizable()
                    .scaledToFit()
                    .frame(
                        width: iconSize,
                        height: iconSize
                    )
                    .foregroundColor(iconColor)
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
    PasswordTextField(
        text: .constant("abc123"),
        passwordVisible: .constant(true),
        placeholder: "Input Field"
    )
}
