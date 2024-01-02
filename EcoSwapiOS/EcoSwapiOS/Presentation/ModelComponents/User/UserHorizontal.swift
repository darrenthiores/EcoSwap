//
//  UserHorizontal.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct UserHorizontal: View {
    let name: String
    let imageUrl: String
    let rating: Double
    
    var body: some View {
        HStack(
            alignment: .center,
            spacing: 4
        ) {
            AsyncImage(
                url: URL(string: imageUrl),
                content: { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(
                            width: 14,
                            height: 14
                        )
                },
                placeholder: {
                    ZStack {
                        ProgressView()
                            .frame(alignment: .center)
                    }
                    .frame(
                        width: 14,
                        height: 14
                    )
                    .background(.ultraThinMaterial)
                }
            )
            .clipShape(Circle())
            
            Text(name)
                .font(.Caption2R)
                .foregroundColor(.Blue.opacity(0.8))
                .lineLimit(1)
            
            Spacer()
            
            HStack(
                alignment: .center,
                spacing: 2
            ) {
                Image(systemName: "star.fill")
                    .resizable()
                    .scaledToFit()
                    .frame(
                        width: 10,
                        height: 10
                    )
                    .foregroundColor(.Primary)
                
                Text(String(rating))
                    .font(.Caption2R)
                    .foregroundColor(.gray)
            }
        }
    }
}

#Preview {
    UserHorizontal(
        name: "John",
        imageUrl: "",
        rating: 4.9
    )
}
