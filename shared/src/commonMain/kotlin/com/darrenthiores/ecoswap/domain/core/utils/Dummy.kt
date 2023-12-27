package com.darrenthiores.ecoswap.domain.core.utils

import com.darrenthiores.ecoswap.domain.carbon.model.Challenge
import com.darrenthiores.ecoswap.domain.carbon.model.ReducedCarbon
import com.darrenthiores.ecoswap.domain.item.model.Item
import com.darrenthiores.ecoswap.domain.item.model.StoreItem
import com.darrenthiores.ecoswap.domain.message.model.Inbox
import com.darrenthiores.ecoswap.domain.reviews.model.Review
import com.darrenthiores.ecoswap.domain.reviews.model.StoreReview
import com.darrenthiores.ecoswap.domain.store.model.Store
import com.darrenthiores.ecoswap.domain.user.model.User
import com.darrenthiores.ecoswap.utils.date.DateUtils
import kotlinx.datetime.Clock

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
            brand = "mentitum",
            statusId = "1"
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
            brand = "mentitum",
            statusId = "1"
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
            brand = "mentitum",
            statusId = "1"
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
            brand = "mentitum",
            statusId = "1"
        ),
        Item(
            id = "5",
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
            brand = "mentitum",
            statusId = "1"
        ),
        Item(
            id = "6",
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
            brand = "mentitum",
            statusId = "1"
        )
    )

    val storeItems: List<StoreItem> = listOf(
        StoreItem(
            id = "1",
            categoryId = "1",
            name = "Walter Kelley",
            imgUrl = listOf(
                ""
            ),
            description = "scripta",
            location = "Jl. Janur Asri, Kelapa Gading Timur, Jakarta Utara, DKI Jakarta 14250",
            storeName = "Merle McIntyre",
            storeImgUrl = "https://search.yahoo.com/search?p=tation",
            storeId = "1",
            rating = 6.7
        ),
        StoreItem(
            id = "2",
            categoryId = "2",
            name = "Walter Kelley",
            imgUrl = listOf(
                ""
            ),
            description = "scripta",
            location = "Jl. Janur Asri, Kelapa Gading Timur, Jakarta Utara, DKI Jakarta 14250",
            storeName = "Merle McIntyre",
            storeImgUrl = "https://search.yahoo.com/search?p=tation",
            storeId = "2",
            rating = 6.7
        ),
        StoreItem(
            id = "3",
            categoryId = "3",
            name = "Walter Kelley",
            imgUrl = listOf(
                ""
            ),
            description = "scripta",
            location = "Jl. Janur Asri, Kelapa Gading Timur, Jakarta Utara, DKI Jakarta 14250",
            storeName = "Merle McIntyre",
            storeImgUrl = "https://search.yahoo.com/search?p=tation",
            storeId = "3",
            rating = 6.7
        ),
        StoreItem(
            id = "4",
            categoryId = "4",
            name = "Walter Kelley",
            imgUrl = listOf(
                ""
            ),
            description = "scripta",
            location = "Jl. Janur Asri, Kelapa Gading Timur, Jakarta Utara, DKI Jakarta 14250",
            storeName = "Merle McIntyre",
            storeImgUrl = "https://search.yahoo.com/search?p=tation",
            storeId = "4",
            rating = 6.7
        )
    )

    val stores: List<Store> = listOf(
        Store(
            id = "1",
            categoryId = "1",
            name = "Deanna Houston",
            imgUrl = "https://www.google.com/#q=noster",
            location = "Jl. Janur Asri, Kelapa Gading Timur, Jakarta Utara, DKI Jakarta 14250",
            rating = 4.5,
            description = "mucius",
            totalReview = 3613
        ),
        Store(
            id = "2",
            categoryId = "2",
            name = "Deanna Houston",
            imgUrl = "https://www.google.com/#q=noster",
            location = "Jl. Janur Asri, Kelapa Gading Timur, Jakarta Utara, DKI Jakarta 14250",
            rating = 4.5,
            description = "mucius",
            totalReview = 3613
        ),
        Store(
            id = "3",
            categoryId = "3",
            name = "Deanna Houston",
            imgUrl = "https://www.google.com/#q=noster",
            location = "Jl. Janur Asri, Kelapa Gading Timur, Jakarta Utara, DKI Jakarta 14250",
            rating = 4.5,
            description = "mucius",
            totalReview = 3613
        ),
        Store(
            id = "4",
            categoryId = "4",
            name = "Deanna Houston",
            imgUrl = "https://www.google.com/#q=noster",
            location = "Jl. Janur Asri, Kelapa Gading Timur, Jakarta Utara, DKI Jakarta 14250",
            rating = 4.5,
            description = "mucius",
            totalReview = 3613
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
            name = "Olga Bartlett",
            imageUrl = "https://duckduckgo.com/?q=euripidis",
            email = "junior.guerra@example.com",
            rating = 3.0,
            totalReview = 3613,
            location = "sagittis"
        ),
        User(
            id = "3",
            name = "Bronski",
            imageUrl = "https://duckduckgo.com/?q=euripidis",
            email = "junior.kii@example.com",
            rating = 4.0,
            totalReview = 3613,
            location = "jakarta"
        )
    )

    val reviews: List<Review> = listOf(
        Review(
            id = "1",
            review = "vocibus",
            reviewedUserId = "2",
            userId = "1",
            username = "Carroll Warner",
            rating = 5.0,
            date = Clock.System.now().toEpochMilliseconds(),
            userImgUrl = ""
        ),
        Review(
            id = "2",
            review = "vocibus",
            reviewedUserId = "1",
            userId = "2",
            username = "Olga Bartlett",
            rating = 4.5,
            date = Clock.System.now().toEpochMilliseconds(),
            userImgUrl = ""
        )
    )

    val storeReviews: List<StoreReview> = listOf(
        StoreReview(
            id = "1",
            review = "velit",
            storeId = "1",
            userId = "1",
            username = "Cora Whitfield",
            userImgUrl = "http://www.bing.com/search?q=dicunt",
            rating = 2.3,
            date = 7483
        ),
        StoreReview(
            id = "2",
            review = "velit",
            storeId = "2",
            userId = "2",
            username = "Cora Whitfield",
            userImgUrl = "http://www.bing.com/search?q=dicunt",
            rating = 2.3,
            date = 7483
        )
    )

    val inboxes: List<Inbox> = listOf(
        Inbox(
            id = "1",
            sentFromId = "1",
            sentToId = "2",
            sentToUsername = "steven",
            sentToImageUrl = "",
            lastMessage = "hey bro!",
            lastSendUserId = "1"
        ),
        Inbox(
            id = "2",
            sentFromId = "1",
            sentToId = "3",
            sentToUsername = "john",
            sentToImageUrl = "",
            lastMessage = "hey bro!",
            lastSendUserId = "3"
        )
    )

    val challenges: List<Challenge> = listOf(
        Challenge(
            id = "1",
            title = "Better Transport",
            description = "this is transport challenge",
            tasks = listOf(
                Challenge.Task(
                    id = "T1",
                    task = "Walking",
                    carbonReduced = 30.0
                ),
                Challenge.Task(
                    id = "T2",
                    task = "Bike",
                    carbonReduced = 30.0
                )
            ),
            deadline = DateUtils.now(),
            goals = 1000.0,
            progress = 100.0,
            participants = listOf(
                Challenge.Participant(
                    id = "1",
                    name = "Darren",
                    imageUrl = "",
                    progress = 100.0
                ),
                Challenge.Participant(
                    id = "2",
                    name = "John",
                    imageUrl = "",
                    progress = 100.0
                ),
                Challenge.Participant(
                    id = "3",
                    name = "Denny",
                    imageUrl = "",
                    progress = 100.0
                )
            ),
            isJoined = false
        )
    )

    val carbonReductions: List<ReducedCarbon> = listOf(
        ReducedCarbon(
            id = "1",
            categoryId = "1",
            taskId = "1",
            taskTitle = "Walking",
            date = DateUtils.now(),
            total = 300.0
        ),
        ReducedCarbon(
            id = "2",
            categoryId = "1",
            taskId = "6",
            taskTitle = "Bike",
            date = DateUtils.now(),
            total = 500.0
        ),
        ReducedCarbon(
            id = "3",
            categoryId = "2",
            taskId = "1",
            taskTitle = "Turning Light Off",
            date = DateUtils.now(),
            total = 50.0
        )
    )
}