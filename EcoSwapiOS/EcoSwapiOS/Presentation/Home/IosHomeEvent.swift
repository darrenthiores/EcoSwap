//
//  IosHomeEvent.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 03/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

enum IosHomeEvent {
    case SubmitSearch
    case OnSelectCategory(category: ItemCategory)
    case OnAddClick
}
