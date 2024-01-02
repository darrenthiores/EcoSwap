//
//  BoardingScreen.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 01/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct BoardingScreen: View {
    @StateObject private var viewModel = IosBoardingViewModel()
    
    var body: some View {
        ZStack {
            if viewModel.state.currentPage == 1 {
                BoardingSection(
                    page: Int(viewModel.state.currentPage),
                    imageName: "boarding_1",
                    title: "Listing and Matching",
                    description: "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatu",
                    onNext: {
                        viewModel.onEvent(
                            event: .UpdateBoarding(page: 2)
                        )
                    },
                    onStart: {
                        viewModel.onEvent(
                            event: .Start()
                        )
                    }
                )
                .transition(.slide)
            }
            
            if viewModel.state.currentPage == 2 {
                BoardingSection(
                    page: Int(viewModel.state.currentPage),
                    imageName: "boarding_2",
                    title: "Safety and Trust",
                    description: "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatu",
                    onNext: {
                        viewModel.onEvent(
                            event: .UpdateBoarding(page: 3)
                        )
                    },
                    onStart: {
                        viewModel.onEvent(
                            event: .Start()
                        )
                    }
                )
                .transition(.slide)
            }
            
            if viewModel.state.currentPage == 3 {
                BoardingSection(
                    page: Int(viewModel.state.currentPage),
                    imageName: "boarding_3",
                    title: "Tracking & Challenge",
                    description: "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatu",
                    onNext: {
                        
                    },
                    onStart: {
                        viewModel.onEvent(
                            event: .Start()
                        )
                    }
                )
                .transition(.slide)
            }
        }
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

#Preview {
    BoardingScreen()
}
