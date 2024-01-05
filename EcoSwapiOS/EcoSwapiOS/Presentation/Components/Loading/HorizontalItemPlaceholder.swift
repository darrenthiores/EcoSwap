//
//  HorizontalItemPlaceholder.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 03/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct HorizontalItemPlaceholder: View {
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack {
                ForEach(0...3, id: \.self) { _ in
                    VStack(alignment: .leading, spacing: 8) {
                        
                        ZStack {
                            Color.Gray1
                        }
                        .frame(
                            width: 170,
                            height: 130
                        )
                        
                        VStack(alignment: .leading, spacing: 0) {
                            ZStack {
                                Color.Gray1
                            }
                            .frame(
                                width: 170 * 0.8,
                                height: 15
                            )
                            
                            Spacer()
                                .frame(height: 4)
                            
                            ZStack {
                                Color.Gray1
                            }
                            .frame(
                                width: 170 * 0.8,
                                height: 15
                            )
                            
                            Spacer()
                                .frame(height: 6)
                            
                            ZStack {
                                Color.Gray1
                            }
                            .frame(
                                height: 15
                            )
                        }
                        .padding(.horizontal, 8)
                        
                        Spacer()
                    }
                    .frame(
                        width: 170,
                        height: 200
                    )
                    .background(
                        Color.Background
                            .clipShape(
                                RoundedRectangle(cornerRadius: 8)
                            )
                            .shadow(radius: 1)
                    )
                }
            }
            .padding(.vertical, 1)
            .padding(.bottom, 8)
        }
    }
}

#Preview {
    HorizontalItemPlaceholder()
}
