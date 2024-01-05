//
//  IosSearchEvent.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 05/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

enum IosSearchEvent {
    case OnSearch
    case OnUpdateAndSearch(searchText: String)
    case OnSelectCategory(category: ItemCategory?)
}
