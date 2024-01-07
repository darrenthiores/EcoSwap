//
//  IosAddItemEvent.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 07/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

enum IosAddItemEvent {
    case OnPickImages
    case OnImagesPicked(datas: [Data])
    case OnImagesError(error: String)
    case Upload
}
