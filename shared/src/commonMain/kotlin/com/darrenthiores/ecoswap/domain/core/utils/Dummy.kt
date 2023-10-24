package com.darrenthiores.ecoswap.domain.core.utils

import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.store.model.Store
import com.darrenthiores.ecoswap.domain.user.model.User

object Dummy {
    val items: List<Item> = listOf(
        Item(
            id = "1",
            categoryId = "1",
            name = "Taylor Michael",
            location = "libero",
            username = "Pablo Tucker",
            userImgUrl = "https://search.yahoo.com/search?p=noluisse",
            userId = "1",
            rating = 4.5,
            imgUrl = listOf(
                "https://www.google.com/#q=ignota"
            ),
            description = "simul",
            conditionId = "1",
            total = 2725,
            brand = "mentitum"
        ),
        Item(
            id = "2",
            categoryId = "2",
            name = "Taylor Michael",
            location = "libero",
            username = "Pablo Tucker",
            userImgUrl = "https://search.yahoo.com/search?p=noluisse",
            userId = "2",
            rating = 4.5,
            imgUrl = listOf(
                "https://www.google.com/#q=ignota"
            ),
            description = "simul",
            conditionId = "2",
            total = 2725,
            brand = "mentitum"
        ),
        Item(
            id = "3",
            categoryId = "3",
            name = "Taylor Michael",
            location = "libero",
            username = "Pablo Tucker",
            userImgUrl = "https://search.yahoo.com/search?p=noluisse",
            userId = "3",
            rating = 4.5,
            imgUrl = listOf(
                "https://www.google.com/#q=ignota"
            ),
            description = "simul",
            conditionId = "2",
            total = 2725,
            brand = "mentitum"
        ),
        Item(
            id = "4",
            categoryId = "4",
            name = "Taylor Michael",
            location = "libero",
            username = "Pablo Tucker",
            userImgUrl = "https://search.yahoo.com/search?p=noluisse",
            userId = "4",
            rating = 4.5,
            imgUrl = listOf(
                "https://www.google.com/#q=ignota"
            ),
            description = "simul",
            conditionId = "1",
            total = 2725,
            brand = "mentitum"
        )
    )

    val stores: List<Store> = listOf(
        Store(
            id = "1",
            categoryId = "1",
            name = "Deanna Houston",
            imgUrl = "https://www.google.com/#q=noster",
            location = "fugit",
            rating = 4.5,
            description = "mucius"
        ),
        Store(
            id = "2",
            categoryId = "2",
            name = "Deanna Houston",
            imgUrl = "https://www.google.com/#q=noster",
            location = "fugit",
            rating = 4.5,
            description = "mucius"
        ),
        Store(
            id = "3",
            categoryId = "3",
            name = "Deanna Houston",
            imgUrl = "https://www.google.com/#q=noster",
            location = "fugit",
            rating = 4.5,
            description = "mucius"
        ),
        Store(
            id = "4",
            categoryId = "4",
            name = "Deanna Houston",
            imgUrl = "https://www.google.com/#q=noster",
            location = "fugit",
            rating = 4.5,
            description = "mucius"
        )
    )

    val user = User(
        id = "1",
        name = "Carroll Warner",
        imageUrl = "https://duckduckgo.com/?q=euripidis",
        email = "junior.guerra@example.com",
        rating = 5.0,
        totalReview = 3613,
        location = "movet"
    )

    val users: List<User> = listOf(
        User(
            id = "1",
            name = "Carroll Warner",
            imageUrl = "https://duckduckgo.com/?q=euripidis",
            email = "junior.guerra@example.com",
            rating = 4.0,
            totalReview = 3613,
            location = "suavitate"
        ),
        User(
            id = "2",
            name = "Carroll Warner",
            imageUrl = "https://duckduckgo.com/?q=euripidis",
            email = "junior.guerra@example.com",
            rating = 3.0,
            totalReview = 3613,
            location = "sagittis"
        )
    )
}