//
//  ItemCategoryExtension.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension ItemCategory {
    func getImageName() -> String {
        switch id {
        case "1":
            return "clothing_icon"
        case "2":
            return "electronics_icon"
        case "3":
            return "books_icon"
        case "4":
            return "furniture_icon"
        default:
            return "ic_no_picture"
        }
    }
}
