//
//  PersonalMessageItem.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 08/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PersonalMessageItem: View {
    let text: String
    let username: String
    let userImgUrl: String
    let geo: GeometryProxy
    
    var screenWidth: CGFloat {
        geo.size.width
    }
    var width: CGFloat {
        screenWidth * 0.75
    }
    
    var body: some View {
        HStack(spacing: 0) {
            Spacer()
            
            VStack(alignment: .trailing, spacing: 4) {
                HStack(alignment: .center, spacing: 8) {
                    Text("@\(username)")
                        .font(.Caption1B)
                    
                    AsyncImage(
                        url: URL(string: userImgUrl),
                        content: { image in
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fill)
                                .frame(
                                    width: 30,
                                    height: 30
                                )
                        },
                        placeholder: {
                            ZStack {
                                ProgressView()
                                    .frame(alignment: .center)
                            }
                            .frame(
                                width: 30,
                                height: 30
                            )
                            .background(.ultraThinMaterial)
                        }
                    )
                    .clipShape(Circle())
                }
                
                Text(text)
                    .font(.Caption1R)
                    .foregroundColor(.Black)
                    .padding(8)
                    .background(
                        Color.YellowMessage
                            .cornerRadius(
                                8,
                                corners: [.bottomLeft, .bottomRight, .topLeft]
                            )
                    )
                    .frame(
                        minWidth: 0,
                        maxWidth: width,
                        alignment: .trailing
                    )
                    .multilineTextAlignment(.leading)
                    .padding(
                        .trailing, 38
                    )
            }
            .frame(
                width: width
            )
        }
    }
}

#Preview {
    GeometryReader { geo in
        PersonalMessageItem(
            text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sodales rhoncus enim in lobortis. Sed ex est, ultricies vel tempor vel, sagittis eu massa. Ut placerat, turpis vel imperdiet aliquet, lacus lectus aliquam odio, ut placerat nibh nibh eu ex. Phasellus gravida elit et mattis posuere. Cras ut ornare turpis. In nec tristique mauris, id rhoncus turpis. Duis neque odio, porta vitae felis eget, pharetra fringilla nisl. Praesent congue lacus erat, eu pharetra nulla volutpat a",
            username: Dummy().user.name,
            userImgUrl: Dummy().user.imageUrl,
            geo: geo
        )
    }
}
