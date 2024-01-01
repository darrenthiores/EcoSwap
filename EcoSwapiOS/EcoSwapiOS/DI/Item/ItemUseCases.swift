//
//  ItemUseCases.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 29/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class ItemUseCasesModule {
    init() {
        @Inject var itemRepository: ItemRepository
        
        @Provider var addItem: AddItem = AddItem(
            repository: itemRepository
        )
        @Provider var getItems: GetItems = GetItems(
            repository: itemRepository
        )
        @Provider var getItemById: GetItemById = GetItemById(
            repository: itemRepository
        )
        @Provider var getStoreItemById: GetStoreItemById = GetStoreItemById(
            repository: itemRepository
        )
        @Provider var searchItems: SearchItems = SearchItems(
            repository: itemRepository
        )
        @Provider var getMyItems: GetMyItems = GetMyItems(
            repository: itemRepository
        )
        @Provider var getUserItems: GetUserItems = GetUserItems(
            repository: itemRepository
        )
        @Provider var getStoreItems: GetStoreItems = GetStoreItems(
            repository: itemRepository
        )
    }
}
