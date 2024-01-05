//
//  ItemDetailTopSection.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ItemDetailTopSection: View {
    let geo: GeometryProxy
    let images: [String]
    @Binding var currentImage: Int
    let onSettingClick: () -> Void
    let onBackClick: () -> Void
    
    private var imageHeight: CGFloat {
        geo.size.width * 0.8
    }
    
    var body: some View {
        ZStack(alignment: .topLeading) {
            TabView(selection: $currentImage) {
                ForEach(Array(images.enumerated()), id: \.offset) { index, url in
                    AsyncImage(
                        url: URL(string: url),
                        content: { image in
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fill)
                                .frame(
                                    width: geo.size.width,
                                    height: imageHeight
                                )
                        },
                        placeholder: {
                            ZStack {
                                ProgressView()
                                    .frame(alignment: .center)
                            }
                            .frame(
                                width: geo.size.width,
                                height: imageHeight
                            )
                            .background(.ultraThinMaterial)
                        }
                    )
                    .tag(index)
                }
            }
            .tabViewStyle(.page(indexDisplayMode: .never))
            
            HStack {
                CircleButton(
                    image: "arrow.left",
                    onClick: onBackClick
                )
                
                Spacer()
                
                CircleButton(
                    image: "ellipsis",
                    onClick: onSettingClick
                )
            }
            .padding(.horizontal, 24)
            .padding(.vertical, 16)
            
            VStack {
                Spacer()
                
                Text("\(currentImage+1)/\(images.count)")
                    .font(.FootnoteB)
                    .padding(.horizontal, 8)
                    .padding(.vertical, 4)
                    .background(
                        Color.black
                            .opacity(0.5)
                            .clipShape(
                                RoundedRectangle(cornerRadius: 8)
                            )
                    )
                    .foregroundColor(.White)
            }
            .padding(.horizontal, 24)
            .padding(.vertical, 16)
        }
        .frame(
            width: geo.size.width,
            height: imageHeight
        )
    }
}

#Preview {
    GeometryReader { geo in
        ItemDetailTopSection(
            geo: geo,
            images: ["1", "2"],
            currentImage: .constant(0),
            onSettingClick: {  },
            onBackClick: {  }
        )
    }
}
