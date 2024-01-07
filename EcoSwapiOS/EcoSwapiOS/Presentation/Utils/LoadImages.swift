//
//  LoadImages.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 08/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import _PhotosUI_SwiftUI

func loadImageSequentially(
    items: [PhotosPickerItem]
) async -> [Data] {
    var datas: [Data] = []
    
    for item in items {
        if let data = try? await item.loadTransferable(
            type: Data.self
        ) {
            datas.append(data)
        } else {
            print("data null")
        }
    }
    
    return datas
}
