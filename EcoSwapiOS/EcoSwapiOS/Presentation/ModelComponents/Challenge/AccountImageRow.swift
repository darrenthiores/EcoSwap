//
//  AccountImageRow.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 10/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct AccountImageRow: View {
    let images: [String]
    var size: CGFloat = 24
    
    var width: CGFloat {
        (CGFloat(images.count) * size) - (CGFloat((images.count - 1)) * (size/3))
    }
    
    var body: some View {
        ZStack(alignment: .leading) {
            Color.clear
            
            if images.count >= 1 {
                AsyncImage(
                    url: URL(string: images[0]),
                    content: { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(
                                width: size,
                                height: size
                            )
                    },
                    placeholder: {
                        ZStack {
                            ProgressView()
                                .frame(
                                    alignment: .center
                                )
                                .controlSize(.mini)
                        }
                        .frame(
                            width: size,
                            height: size
                        )
                        .background(.ultraThinMaterial)
                    }
                )
                .clipShape(Circle())
                .overlay(
                    Circle()
                        .stroke(
                            Color.Background,
                            lineWidth: 1
                        )
                )
            }
            
            if images.count >= 2 {
                AsyncImage(
                    url: URL(string: images[1]),
                    content: { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(
                                width: size,
                                height: size
                            )
                    },
                    placeholder: {
                        ZStack {
                            ProgressView()
                                .frame(
                                    alignment: .center
                                )
                                .controlSize(.mini)
                        }
                        .frame(
                            width: size,
                            height: size
                        )
                        .background(.ultraThinMaterial)
                    }
                )
                .clipShape(Circle())
                .overlay(
                    Circle()
                        .stroke(
                            Color.Background,
                            lineWidth: 1
                        )
                )
                .offset(
                    x: size - (size/3)
                )
            }
            
            if images.count >= 3 {
                AsyncImage(
                    url: URL(string: images[2]),
                    content: { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(
                                width: size,
                                height: size
                            )
                    },
                    placeholder: {
                        ZStack {
                            ProgressView()
                                .frame(
                                    alignment: .center
                                )
                                .controlSize(.mini)
                        }
                        .frame(
                            width: size,
                            height: size
                        )
                        .background(.ultraThinMaterial)
                    }
                )
                .clipShape(Circle())
                .overlay(
                    Circle()
                        .stroke(
                            Color.Background,
                            lineWidth: 1
                        )
                )
                .offset(
                    x: size + (size/3)
                )
            }
        }
        .frame(
            width: width,
            height: size
        )
    }
}

#Preview {
    AccountImageRow(
        images: [
            "", ""
        ]
    )
    .background(Color.Primary)
}
