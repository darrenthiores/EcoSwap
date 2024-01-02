//
//  BoardingSection.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 01/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct BoardingSection: View {
    let page: Int
    let imageName: String
    let title: String
    let description: String
    let onNext: () -> Void
    let onStart: () -> Void
    
    var body: some View {
        ZStack {
            Image(imageName)
                .resizable()
                .scaledToFit()
                .padding(24)
            
            ZStack(alignment: .bottom) {
                Color.clear
                
                VStack(
                    alignment: .center,
                    spacing: 0
                ) {
                    Text(title)
                        .font(.Title1B)
                        .multilineTextAlignment(.center)
                    
                    Spacer()
                        .frame(height: 16)
                    
                    Text(description)
                        .font(.SubHeadlineR)
                        .multilineTextAlignment(.center)
                    
                    Spacer()
                        .frame(height: 32)
                    
                    BoardingPageTab(currentPage: page)
                    
                    Spacer()
                        .frame(height: 16)
                    
                    if page == 3 {
                        ButtonFill(
                            label: "Get Started",
                            onClick: onStart,
                            fillWidth: true
                        )
                    } else {
                        HStack(spacing: 12) {
                            ButtonOutlined(
                                label: "Skip",
                                onClick: onStart,
                                fillWidth: true
                            )
                            
                            ButtonFill(
                                label: "Next",
                                onClick: onNext,
                                fillWidth: true
                            )
                        }
                    }
                }
                .padding(.horizontal, 24)
                .padding(.vertical, 48)
                .background(
                    LinearGradient(
                        gradient: Gradient(colors: [.clear, .Background]),
                        startPoint: .top,
                        endPoint: .init(
                            x: UnitPoint.bottom.x,
                            y: UnitPoint.bottom.y / 4
                        )
                    )
                )
            }
        }
    }
}

#Preview {
    BoardingSection(
        page: 1,
        imageName: "boarding_1",
        title: "Listing and Matchinh",
        description: "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatu",
        onNext: {  },
        onStart: {  }
    )
}
