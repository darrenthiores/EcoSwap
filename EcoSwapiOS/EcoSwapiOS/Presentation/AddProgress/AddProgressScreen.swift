//
//  AddProgressScreen.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 11/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AddProgressScreen: View {
    var challengeId: String? = nil
    @Binding var shouldRefresh: Bool
    
    @Environment(\.dismiss) private var dismiss
    @StateObject private var viewModel = IosAddProgressViewModel()
    @State private var showSheet: Bool = false
    
    var body: some View {
        ScrollView (.vertical, showsIndicators: false){
            LazyVStack(alignment: .leading) {
                Spacer()
                    .frame(height: 16)
                
                Group {
                    Text("Category*")
                        .font(.SubHeadlineR)
                    
                    Spacer()
                        .frame(height: 8)
                    
                    CarbonCategoryDropDown(
                        category: viewModel.state.category,
                        isOpen: viewModel.state.categoryDropDownOpen,
                        selectCategory: { category in
                            viewModel.onEvent(
                                event: .OnSelectCategory(category: category)
                            )
                        },
                        toggleIsOpen: {
                            viewModel.onEvent(
                                event: .ToggleCategoryDropDown()
                            )
                        }
                    )
                    
                    Spacer()
                        .frame(height: 20)
                }
                .padding(.horizontal, 24)
                
                if viewModel.state.category?.id == "1" || viewModel.state.category?.id == "2" {
                    Group {
                        Text("Activity*")
                            .font(.SubHeadlineR)
                        
                        Spacer()
                            .frame(height: 8)
                        
                        CarbonActivityDropDown(
                            activities: viewModel.state.activities,
                            activity: viewModel.state.activity,
                            isOpen: viewModel.state.activityDropDownOpen,
                            selectActivity: { activity in
                                viewModel.onEvent(
                                    event: .OnSelectActivity(activity: activity)
                                )
                            },
                            toggleIsOpen: {
                                viewModel.onEvent(
                                    event: .ToggleActivityDropDown()
                                )
                            }
                        )
                        
                        Spacer()
                            .frame(height: 20)
                    }
                    .padding(.horizontal, 24)
                    
                    Group {
                        Text(
                            (viewModel.state.category?.id == "1") ? "Number (in Meter)*" : "Number (in Minute)*"
                        )
                        .font(.SubHeadlineR)
                        
                        Spacer()
                            .frame(height: 8)
                        
                        DefaultTextField(
                            text: Binding(
                                get: {
                                    viewModel.state.number
                                },
                                set: {
                                    viewModel.onEvent(
                                        event: .OnNumberChange(number: $0)
                                    )
                                }
                            ),
                            placeholder: (viewModel.state.category?.id == "1") ? "How far you do the task (in Meter)" : "How long you do the task (in Minute)"
                        )
                        .keyboardType(.numberPad)
                        
                        Spacer()
                            .frame(height: 20)
                    }
                    .padding(.horizontal, 24)
                }
                
                if viewModel.state.category?.id == "3" {
                    Group {
                        Text("Challenge*")
                            .font(.SubHeadlineR)
                        
                        Spacer()
                            .frame(height: 8)
                        
                        SelectableButton(
                            text: "Select Challenge",
                            value: viewModel.state.challenge?.title,
                            onClick: {
                                showSheet = true
                            }
                        )
                        
                        Spacer()
                            .frame(height: 20)
                    }
                    .padding(.horizontal, 24)
                    
                    if let challenge = viewModel.state.challenge {
                        Group {
                            Text("Task*")
                                .font(.SubHeadlineR)
                            
                            Spacer()
                                .frame(height: 8)
                            
                            ChallengeTaskDropDown(
                                tasks: challenge.tasks,
                                task: viewModel.state.task,
                                isOpen: viewModel.state.taskDropDownOpen,
                                selectTask: { task in
                                    viewModel.onEvent(
                                        event: .OnSelectTask(task: task)
                                    )
                                },
                                toggleIsOpen: {
                                    viewModel.onEvent(
                                        event: .ToggleTaskDropDown()
                                    )
                                }
                            )
                            
                            Spacer()
                                .frame(height: 20)
                        }
                        .padding(.horizontal, 24)
                    }
                }
                
                Spacer()
                    .frame(height: 24)
            }
        }
        .onAppear {
            viewModel.startObserving()
            
            if let challengeId = challengeId {
                viewModel.onEvent(
                    event: .InitByChallenge(
                        challengeId: challengeId
                    )
                )
            }
        }
        .onDisappear {
            viewModel.dispose()
        }
        .navigationBarBackButtonHidden()
        .toolbar(.hidden, for: .tabBar)
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .principal) {
                DefaultHeader(
                    title: "Add Progress",
                    onBackClick: {
                        dismiss()
                    }
                )
                .padding(16)
            }
            
            ToolbarItem(placement: .bottomBar) {
                ButtonFill(
                    label: "Upload",
                    onClick: {
                        viewModel.onEvent(
                            event: .Upload()
                        )
                    },
                    disabled: viewModel.state.category == nil ||
                    (viewModel.state.activity == nil && viewModel.state.challenge == nil) || (viewModel.state.challenge != nil && viewModel.state.task == nil) || (viewModel.state.activity != nil && viewModel.state.number.isEmpty) || viewModel.state.isLoading,
                    fillWidth: true,
                    isLoading: viewModel.state.isLoading
                )
                .padding(.horizontal, 24)
                .padding(.vertical, 8)
            }
        }
        .sheet(isPresented: $showSheet) {
            ChallengeSheet(
                challenges: viewModel.state.challenges.items as? [Challenge] ?? [],
                onSelectChallenge: { challenge in
                    viewModel.onEvent(
                        event: .OnSelectChallenge(challenge: challenge)
                    )
                    
                    showSheet = false
                },
                onAppear: {
                    if !viewModel.state.challenges.endReached && !viewModel.state.challenges.isLoading {
                        viewModel.onEvent(event: .LoadChallengeNextPage())
                    }
                }
            )
        }
        .onChange(of: viewModel.state.isSuccess) { success in
            if success {
                shouldRefresh = true
                showSheet = false
                dismiss()
            }
        }
    }
}

#Preview {
    AddProgressScreen(
        challengeId: nil,
        shouldRefresh: .constant(false)
    )
}
