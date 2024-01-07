//
//  IosAddItemViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 07/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

extension AddItemScreen {
    @MainActor class IosAddItemViewModel: ObservableObject {
        private let viewModel: AddItemViewModel
        
        @Published var state: AddItemState = AddItemState(
            name: "",
            description: "",
            category: nil,
            total: "",
            condition: nil,
            brand: "",
            location: "",
            categoryDropDownOpen: false,
            conditionDropDownOpen: false,
            isLoading: false,
            error: nil,
            isSuccess: false
        )
        @Published var iosState: IosAddItemState = IosAddItemState()
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var addItem: AddItem
            
            self.viewModel = AddItemViewModel(
                addItem: addItem,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: AddItemEvent) {
            viewModel.onEvent(event: event)
        }
        
        func onIosEvent(event: IosAddItemEvent) {
            switch event {
            case .OnPickImages:
                iosState.showImagePicker = true
            case .OnImagesPicked(datas: let datas):
                iosState.selectedImages = []
                datas.forEach { data in
                    if let image = UIImage(data: data) {
                        iosState.selectedImages.append(image)
                        iosState.pickerImageError = nil
                    }
                }
            case .OnImagesError(error: let error):
                iosState.pickerImageError = error
            case .Upload:
                viewModel.onEvent(
                    event: .Upload(
                        photos: []
                    )
                )
            }
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe { state in
                if let state = state {
                    self.state = state
                }
            }
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
