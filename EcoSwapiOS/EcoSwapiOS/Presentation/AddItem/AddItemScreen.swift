//
//  AddItemScreen.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 07/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct AddItemScreen: View {
    @Environment(\.dismiss) private var dismiss
    @StateObject private var viewModel = IosAddItemViewModel()
    
    @State private var showAlert: Bool = false
    
    var body: some View {
        ScrollView(.vertical, showsIndicators: false) {
            LazyVStack(alignment: .leading){
                Spacer()
                    .frame(height: 24)
                
                Group {
                    Text("Add Photos*")
                        .font(.SubHeadlineR)
                        .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 8)
                    
                    PhotoSection(
                        photos: viewModel.iosState.selectedImages,
                        onAdd: {
                            viewModel.onIosEvent(event: .OnPickImages)
                        }
                    )
                    
                    Spacer()
                        .frame(height: 20)
                }
                
                Group {
                    Text("Add Item Name*")
                        .font(.SubHeadlineR)
                        .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 8)
                    
                    DefaultTextField(
                        text: Binding(
                            get: {
                                viewModel.state.name
                            },
                            set: {
                                viewModel.onEvent(
                                    event: .OnNameChange(
                                        name: $0
                                    )
                                )
                            }
                        ),
                        placeholder: "Your Item or Product"
                    )
                    .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 20)
                }
                
                Group {
                    Text("Item Description*")
                        .font(.SubHeadlineR)
                        .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 8)
                    
                    MultilineTextField(
                        text: Binding(
                            get: {
                                viewModel.state.description_
                            },
                            set: {
                                viewModel.onEvent(
                                    event: .OnDescriptionChange(
                                        description: $0
                                    )
                                )
                            }
                        ),
                        placeholder: "Description Item here...",
                        lineLimit: 5,
                        reserved: true
                    )
                    .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 20)
                }
                
                Group {
                    Text("Item Category*")
                        .font(.SubHeadlineR)
                        .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 8)
                    
                    ItemCategoryDropDown(
                        category: viewModel.state.category,
                        isOpen: viewModel.state.categoryDropDownOpen,
                        selectCategory: { category in
                            viewModel.onEvent(
                                event: .OnSelectCategory(
                                    category: category
                                )
                            )
                        },
                        toggleIsOpen: {
                            viewModel.onEvent(
                                event: .ToggleCategoryDropDown()
                            )
                        }
                    )
                    .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 20)
                }
                
                Group {
                    Text("Item Total*")
                        .font(.SubHeadlineR)
                        .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 8)
                    
                    DefaultTextField(
                        text: Binding(
                            get: {
                                viewModel.state.total
                            },
                            set: {
                                viewModel.onEvent(
                                    event: .OnTotalChange(
                                        total: $0
                                    )
                                )
                            }
                        ),
                        placeholder: "Item Total*"
                    )
                    .keyboardType(.numberPad)
                    .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 20)
                }
                
                Group {
                    Text("Condition*")
                        .font(.SubHeadlineR)
                        .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 8)
                    
                    ItemConditionDropDown(
                        condition: viewModel.state.condition,
                        isOpen: viewModel.state.conditionDropDownOpen,
                        selectCondition: { condition in
                            viewModel.onEvent(
                                event: .OnSelectCondition(
                                    condition: condition
                                )
                            )
                        },
                        toggleIsOpen: {
                            viewModel.onEvent(
                                event: .ToggleConditionDropDown()
                            )
                        }
                    )
                    .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 20)
                }
                
                Group {
                    Text("Item Brand*")
                        .font(.SubHeadlineR)
                        .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 8)
                    
                    DefaultTextField(
                        text: Binding(
                            get: {
                                viewModel.state.brand
                            },
                            set: {
                                viewModel.onEvent(
                                    event: .OnBrandChange(
                                        brand: $0
                                    )
                                )
                            }
                        ),
                        placeholder: "Brand Item"
                    )
                    .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 20)
                }
                
                Group {
                    Text("Location*")
                        .font(.SubHeadlineR)
                        .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 8)
                    
                    MultilineTextField(
                        text: Binding(
                            get: {
                                viewModel.state.location
                            },
                            set: {
                                viewModel.onEvent(
                                    event: .OnLocationChange(
                                        location: $0
                                    )
                                )
                            }
                        ),
                        placeholder: "Input Location Item",
                        lineLimit: 2,
                        reserved: true
                    )
                    .padding(.horizontal, 24)
                    
                    Spacer()
                        .frame(height: 20)
                }
                
                Spacer()
                    .frame(height: 24)
            }
        }
        .photosPicker(
            isPresented: $viewModel.iosState.showImagePicker,
            selection: $viewModel.iosState.selectedItems,
            maxSelectionCount: 5,
            matching: .images
        )
        .tint(.Primary)
        .onChange(of: viewModel.iosState.selectedItems) { items in
            Task {
                let images = await loadImageSequentially(
                    items: items
                )
                
                viewModel.onIosEvent(
                    event: .OnImagesPicked(datas: images)
                )
            }
        }
        .navigationBarBackButtonHidden()
        .toolbar(.hidden, for: .tabBar)
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
        .alert(
            "Upload Success",
            isPresented: $showAlert,
            actions: { }
        )
        .onChange(of: viewModel.state.isSuccess) { success in
            if success {
                showAlert = true
            }
        }
        .onChange(of: showAlert) { isShown in
            if !isShown {
                dismiss()
            }
        }
        .toolbar {
            ToolbarItem(placement: .principal) {
                DefaultHeader(
                    title: "Add Item",
                    onBackClick: {
                        dismiss()
                    }
                )
                .padding(.vertical, 16)
            }
            
            ToolbarItem(placement: .bottomBar) {
                ButtonFill(
                    label: "Upload",
                    onClick: {
                        viewModel.onIosEvent(
                            event: .Upload
                        )
                    },
                    disabled: viewModel.state.name.isEmpty || viewModel.state.description.isEmpty ||
                    viewModel.state.category == nil || viewModel.state.total.isEmpty || viewModel.state.condition == nil ||
                    viewModel.state.brand.isEmpty || viewModel.state.location.isEmpty || viewModel.iosState.selectedImages.isEmpty || viewModel.state.isLoading,
                    fillWidth: true,
                    isLoading: viewModel.state.isLoading
                )
                .padding(.horizontal, 24)
                .padding(.vertical, 8)
            }
        }
    }
}

#Preview {
    AddItemScreen()
}
