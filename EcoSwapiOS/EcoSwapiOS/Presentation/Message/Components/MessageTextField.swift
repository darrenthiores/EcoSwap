//
//  MessageTextField.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 08/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct MessageTextField: View {
    @Binding var text: String
    var lineLimit: Int = 3
    
    @FocusState.Binding var textFieldFocus: Bool
    
    var body: some View {
        TextField(
            "Message...",
            text: $text,
            axis: .vertical
        )
        .font(.SubHeadlineR)
        .focused($textFieldFocus)
        .lineLimit(lineLimit)
        .onSubmit {
            textFieldFocus = false
        }
        .tint(.OnBackground)
        .padding(.horizontal, 24)
        .padding(.vertical, 8)
        .background(Color.gray.opacity(0.3))
        .cornerRadius(32)
    }
}

#Preview {
    MessageTextField(
        text: .constant("abgabvabbaann\nabababababbababa\naaaaajwjwnjjwaijwijowaijwijwijwaiwiaojwaidiwa"),
        textFieldFocus: FocusState().projectedValue
    )
}
