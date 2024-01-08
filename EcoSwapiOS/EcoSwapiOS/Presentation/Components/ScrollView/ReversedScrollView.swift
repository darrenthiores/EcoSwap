//
//  ReversedScrollView.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 08/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ReversedScrollView<Content: View>: View {
    var axis: Axis.Set
    var proxy: GeometryProxy
    var content: Content
    
    init(
        _ axis: Axis.Set = .horizontal,
        proxy: GeometryProxy,
        @ViewBuilder builder: ()->Content
    ) {
        self.axis = axis
        self.proxy = proxy
        self.content = builder()
    }
    
    var body: some View {
        ScrollView(.vertical) {
            Stack(.vertical) {
                content
                Spacer()
            }
            .frame(
                minWidth: minWidth(in: proxy, for: axis),
                minHeight: minHeight(in: proxy, for: axis)
            )
        }
    }
    
    func minWidth(in proxy: GeometryProxy, for axis: Axis.Set) -> CGFloat? {
       axis.contains(.horizontal) ? proxy.size.width : nil
    }
        
    func minHeight(in proxy: GeometryProxy, for axis: Axis.Set) -> CGFloat? {
       axis.contains(.vertical) ? proxy.size.height : nil
    }
}

#Preview {
    GeometryReader { geo in
        ReversedScrollView(
            .vertical,
            proxy: geo
        ) {
            ForEach(0..<12) { item in
                Text("\(item)")
                    .padding()
                    .background(Color.gray.opacity(0.5))
                    .cornerRadius(6)
            }
        }
    }
}
