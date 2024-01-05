//
//  StoreItemDetailScreen.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct StoreItemDetailScreen: View {
    let id: String
    
    @Environment(\.dismiss) private var dismiss
    @StateObject private var viewModel = IosStoreItemDetailViewModel()
    @State private var currentImage: Int = 0
    
    var body: some View {
        GeometryReader { geo in
            VStack(spacing: 0) {
                Spacer()
                    .frame(height: 1)
                
                ScrollView(.vertical, showsIndicators: false) {
                    LazyVStack(spacing: 16) {
                        ItemDetailTopSection(
                            geo: geo,
                            images: viewModel.state.item?.imgUrl ?? [],
                            currentImage: $currentImage,
                            onSettingClick: {  },
                            onBackClick: { dismiss() }
                        )
                        
                        StoreItemInformationSection(
                            item: viewModel.state.item,
                            store: viewModel.state.store,
                            isExpanded: viewModel.state.isExpand,
                            onToggleExpand: {
                                viewModel.onEvent(
                                    event: .ToggleExpand()
                                )
                            }
                        )
                        
                        StoreProductDetailSection(
                            item: viewModel.state.item
                        )
                        
                        Spacer()
                            .frame(height: 24)
                    }
                }
            }
        }
        .navigationBarBackButtonHidden()
        .toolbar(.hidden, for: .tabBar)
        .onAppear {
            viewModel.startObserving()
            
            viewModel.onEvent(
                event: .InitialFetch(id: id)
            )
        }
        .onDisappear {
            viewModel.dispose()
        }
        .toolbar {
            ToolbarItem(placement: .bottomBar) {
                NavigationLink {
                    Text("Message")
                } label: {
                    NavigationButtonFill(
                        label: "Message",
                        fillWidth: true
                    )
                    .padding(.horizontal, 24)
                    .padding(.vertical, 8)
                }
                .buttonStyle(.plain)
                .background(Color.Background)
            }
        }
    }
}

#Preview {
    StoreItemDetailScreen(
        id: "1"
    )
}
