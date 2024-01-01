//
//  PageIndicator.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PageIndicator: View {
    let currentPage: Int
    let pageCount: Int
    
    var body: some View {
        HStack(spacing: 8) {
            ForEach(0..<pageCount, id: \.self) { page in
                let color: Color = (currentPage == page) ? .Primary : .Primary.opacity(0.2)
                
                color
                    .frame(
                        width: 6,
                        height: 6
                    )
                    .clipShape(Circle())
            }
        }
    }
}

#Preview {
    PageIndicator(
        currentPage: 1,
        pageCount: 4
    )
}
