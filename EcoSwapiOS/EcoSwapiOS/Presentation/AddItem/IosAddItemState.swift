//
//  IosAddItemState.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 07/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import _PhotosUI_SwiftUI

struct IosAddItemState {
    var selectedItems: [PhotosPickerItem] = []
    var selectedImages: [UIImage] = []
    var showImagePicker: Bool = false
    var pickerImageError: String? = nil
}
