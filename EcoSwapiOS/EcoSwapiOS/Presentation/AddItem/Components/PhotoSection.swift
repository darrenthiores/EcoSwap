//
//  PhotoSection.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 08/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct PhotoSection: View {
    let photos: [UIImage]
    let onAdd: () -> Void
    
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            LazyHStack(spacing: 12) {
                Spacer()
                    .frame(width: 12)
                
                Button {
                    onAdd()
                } label: {
                    ZStack {
                        Color.Primary
                            .opacity(0.3)
                        
                        Image(systemName: "plus")
                            .resizable()
                            .scaledToFit()
                            .frame(
                                width: 24,
                                height: 24
                            )
                            .foregroundColor(.Primary)
                    }
                    .frame(
                        width: 80,
                        height: 80
                    )
                    .clipShape(RoundedRectangle(cornerRadius: 8))
                    .background(
                        RoundedRectangle(cornerRadius: 8)
                            .strokeBorder(
                                Color.Primary,
                                style: StrokeStyle(
                                    lineWidth: 1.0,
                                    dash: [10]
                                )
                            )
                    )
                }
                .buttonStyle(.plain)
                
                ForEach(0..<photos.count, id: \.self) { index in
                    Button {
                        onAdd()
                    } label: {
                        Image(uiImage: photos[index])
                            .resizable()
                            .scaledToFill()
                            .frame(
                                width: 80,
                                height: 80
                            )
                            .clipped()
                            .cornerRadius(8)
                    }
                    .buttonStyle(.plain)
                }
                
                Spacer()
                    .frame(width: 12)
            }
        }
        .frame(height: 80)
    }
}

#Preview {
    PhotoSection(
        photos: [],
        onAdd: {  }
    )
}
