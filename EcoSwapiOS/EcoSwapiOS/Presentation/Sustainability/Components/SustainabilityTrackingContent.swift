//
//  SustainabilityTrackingContent.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 10/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SustainabilityTrackingContent: View {
    let state: SustainabilityState
    let onEvent: (SustainabilityEvent) -> Void
    
    var body: some View {
        GeometryReader { geo in
            ScrollView(.vertical, showsIndicators: false) {
                LazyVStack(alignment: .center, spacing: 0) {
                    Spacer()
                        .frame(height: 24)
                    
                    TotalProgressBarInfo(
                        values: state
                            .footPrint?
                            .carbons
                            .map { carbon in
                                carbon.total
                            } ?? [0.0, 0.0, 0.0],
                        total: state.footPrint?.total ?? 0.0,
                        colors: state
                            .footPrint?
                            .carbons
                            .compactMap { carbon in
                                .getProgressColorByCategoryId(
                                    categoryId: carbon.categoryId
                                )
                            } ?? [.Transport, .Energy, .Challenge]
                    )
                    .frame(
                        width: min(geo.size.width, geo.size.height),
                        height: min(geo.size.width, geo.size.height)
                    )
                    
                    Spacer()
                        .frame(height: 24)
                    
                    HStack(spacing: 0) {
                        ForEach(CarbonView.entries, id: \.name) { tab in
                            TabButton(
                                title: tab.name,
                                isSelected: state.viewMode == tab,
                                onClick: {
                                    onEvent(
                                        .OnViewModeClick(
                                            mode: CarbonView
                                                .companion
                                                .getById(
                                                    id: tab.id
                                                )
                                        )
                                    )
                                }
                            )
                        }
                    }
                    .background(
                        Color.Background
                    )
                    .contentShape(Rectangle())
                    
                    Spacer()
                        .frame(height: 24)
                    
                    ForEach(state.footPrint?.carbons ?? [], id: \.categoryId) { carbon in
                        CarbonItem(
                            carbon: carbon
                        )
                    }
                    
                    Spacer()
                        .frame(height: 24 + 56)
                }
            }
        }
        .padding(.horizontal, 24)
    }
}

#Preview {
    SustainabilityTrackingContent(
        state: SustainabilityState(
            footPrint: nil,
            challenges: PagingState(
                isLoading: false,
                items: [],
                error: nil,
                endReached: false,
                page: 1
            ),
            error: nil,
            isLoading: false,
            viewMode: .daily
        ),
        onEvent: { _ in }
    )
}
