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
    private const val manImageUrl = "https://t4.ftcdn.net/jpg/02/24/86/95/360_F_224869519_aRaeLneqALfPNBzg0xxMZXghtvBXkfIA.jpg"
    private const val womanImageUrl = "https://img.freepik.com/free-photo/beautiful-female-woman-biting-her-lip-face-expression-feelings-attitude_176420-15189.jpg"

    val items: List<Item> = listOf(
        Item(
            id = "1",
            categoryId = "1",
            name = "Ankle Pant",
            location = "West Jakarta",
            username = "Kevin Valentino",
            userImgUrl = manImageUrl,
            userId = "7",
            rating = 4.0,
            imgUrl = listOf(
                "https://dynamic.zacdn.com/sGLfg18guLNl7hjc3MWWJ09kb8w=/filters:quality(70):format(webp)/https://static-id.zacdn.com/p/executive-7053-4116714-2.jpg",
                "https://executive.co.id/cdn/shop/products/1-LPICRT221L324_GREY_1.jpg?v=1643968538"
            ),
            description = "Ankle Pant bought from local market, it is not fit on my leg so i never used it, and wanna trade the pant to another pant of my size.",
            conditionId = "1",
            total = 1,
            brand = "The Executive",
            statusId = "1"
        ),
        Item(
            id = "2",
            categoryId = "1",
            name = "White Polo",
            location = "South Jakarta",
            username = "Asyam",
            userImgUrl = manImageUrl,
            userId = "9",
            rating = 4.1,
            imgUrl = listOf(
                "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//catalog-image/87/MTA-142660012/brd-04387_uniqlo-kaos-polo-dry-ex-lengan-pendek-452408-uniseks-00-white_full01-8fc347be.jpg"
            ),
            description = "White Polo bought from UniQlo, i have used it once, but doesn't seem good on me, so i wanna trade the Polo with any shirt",
            conditionId = "2",
            total = 2,
            brand = "UniQlo",
            statusId = "1"
        ),
        Item(
            id = "3",
            categoryId = "2",
            name = "Red Samsung S13",
            location = "West Jakarta",
            username = "Steven Enceil",
            userImgUrl = manImageUrl,
            userId = "4",
            rating = 4.0,
            imgUrl = listOf(
                "https://i.pinimg.com/1200x/96/a5/cf/96a5cf6543f48fd566a3f253658586c2.jpg",
                "https://images.samsung.com/is/image/samsung/p6pim/id/sm-a135fzkjxid/gallery/id-galaxy-a13-sm-a135-sm-a135fzkjxid-thumb-531621483"
            ),
            description = "Samsung S13 just bought this yesterday 10th October, i probably can resell this, but i just wanted another color, please if any of you wanna trade with Samsung S13 with Red Color, just message me!",
            conditionId = "2",
            total = 1,
            brand = "Samsung",
            statusId = "1"
        ),
        Item(
            id = "4",
            categoryId = "2",
            name = "MiniSo Earphone",
            location = "West Jakarta",
            username = "Rifqi Arkaan",
            userImgUrl = manImageUrl,
            userId = "2",
            rating = 4.5,
            imgUrl = listOf(
                "https://images.tokopedia.net/img/cache/700/VqbcmM/2022/3/2/2372a93f-921e-4a81-9929-544a7384c20b.jpg",
                "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//95/MTA-71082553/brd-69012_miniso-type-c-earphone-in-ear-headphone-kabel-control-mic-microphone-earbuds-awet-universal-stereo-jawab-akhiri-panggilan-musik-original-wanita-pria-_full02.jpg"
            ),
            description = "MiniSo Earphone, it is new, i think i want to buy another brand or if i can, i want to exchange this to KKV Earphone",
            conditionId = "1",
            total = 1,
            brand = "MiniSO",
            statusId = "1"
        ),
        Item(
            id = "5",
            categoryId = "3",
            name = "Beauty and the Beast",
            location = "Palembang",
            username = "Stevan Wijaya",
            userImgUrl = manImageUrl,
            userId = "8",
            rating = 3.7,
            imgUrl = listOf(
                "https://target.scene7.com/is/image/Target/GUEST_b0262699-d5c9-4d16-a418-ef61b7303fcc?wid=488&hei=488&fmt=pjpeg"
            ),
            description = "Books i bought from Gramedia, conditions still good",
            conditionId = "2",
            total = 3,
            brand = "Gramedia",
            statusId = "1"
        ),
        Item(
            id = "6",
            categoryId = "3",
            name = "Poor Dad Rich Dad",
            location = "Palembang",
            username = "Stevan Wijaya",
            userImgUrl = manImageUrl,
            userId = "8",
            rating = 3.7,
            imgUrl = listOf(
                "https://cdn.gramedia.com/uploads/items/9786020333175_rich-dad-poor-dad-_edisi-revisi_.jpg"
            ),
            description = "Books i bought from Gramedia, conditions still new",
            conditionId = "1",
            total = 1,
            brand = "Gramedia",
            statusId = "1"
        ),
        Item(
            id = "7",
            categoryId = "4",
            name = "IKEA working table",
            location = "North Jakarta",
            username = "Nathan Ginta Thiores",
            userImgUrl = manImageUrl,
            userId = "11",
            rating = 4.9,
            imgUrl = listOf(
                "https://www.ikea.co.id/dairyfarm/id/images/364/0736423_PE740526_S4.jpg"
            ),
            description = "new table from IKEA, does not fit in my room",
            conditionId = "1",
            total = 1,
            brand = "IKEA",
            statusId = "1"
        ),
        Item(
            id = "8",
            categoryId = "4",
            name = "Informa Working Chair",
            location = "Lampung",
            username = "Steven Enceil",
            userImgUrl = manImageUrl,
            userId = "4",
            rating = 4.0,
            imgUrl = listOf(
                "https://res.cloudinary.com/ruparupa-com/image/upload//f_auto,q_auto:eco/v1681448760/Products/10508114_1.jpg"
            ),
            description = "Working chair i bought last month (december 2023), i used it but i don't feel comfortable using this, i want to trade with another chair please drop the image to my message.",
            conditionId = "2",
            total = 1,
            brand = "Informa",
            statusId = "1"
        ),
        Item(
            id = "9",
            categoryId = "5",
            name = "HyperX Gaming Headset",
            location = "West Jakarta",
            username = "Kevin Valentino",
            userImgUrl = manImageUrl,
            userId = "7",
            rating = 4.0,
            imgUrl = listOf(
                "https://row.hyperx.com/cdn/shop/products/hyperx_cloud_stinger_2_front.jpg?v=1662420667"
            ),
            description = "I used it for half years, just wanted to try new headset, if any of you have maybe like Logitech one, please message me, if you will i may trade with my headset + money",
            conditionId = "2",
            total = 1,
            brand = "HyperX",
            statusId = "1"
        ),
        Item(
            id = "10",
            categoryId = "5",
            name = "Razer Gaming Mouse",
            location = "North Jakarta",
            username = "Nathan Ginta Thiores",
            userImgUrl = manImageUrl,
            userId = "11",
            rating = 4.9,
            imgUrl = listOf(
                "https://assets2.razerzone.com/images/pnx.assets/dd2cb52f4bf27ff926aa88e6df386019/razer-basilisk-v3-pro-500x500.png"
            ),
            description = "Bought this from Tokopedia, it is still new, i wanna trade with Logitech Mouse, in the same price range",
            conditionId = "1",
            total = 1,
            brand = "Razer",
            statusId = "1"
        ),
        Item(
            id = "11",
            categoryId = "6",
            name = "Barcelona Home Jersey 2022/2023",
            location = "North Jakarta",
            username = "Darren Thiores",
            userImgUrl = manImageUrl,
            userId = "1",
            rating = 5.0,
            imgUrl = listOf(
                "https://static.nike.com/a/images/t_default/6ee5fa81-be9c-4d0c-a48a-3d0db669c878/fc-barcelona-2022-23-stadium-home-dri-fit-football-shirt-Hn9LCv.png"
            ),
            description = "Original Barcelona Home Jersey 2022/2023, i want to trade this with older jersey but new, maybe at year 2019/2020",
            conditionId = "2",
            total = 1,
            brand = "Nike",
            statusId = "1"
        ),
        Item(
            id = "12",
            categoryId = "6",
            name = "Manchester United Away Jersey 2021/2022",
            location = "South Jakarta",
            username = "Asyam",
            userImgUrl = manImageUrl,
            userId = "9",
            rating = 4.1,
            imgUrl = listOf(
                "https://www.uksoccershop.com/images/re_1627636194_manchester-united-away-shirt-2021-22.jpg"
            ),
            description = "I bought 3 jersey, at first i want to resell it, but i changed my mind and want to trade it with any year home jersey",
            conditionId = "1",
            total = 2,
            brand = "Adidas",
            statusId = "1"
        )
    )

    val storeItems: List<StoreItem> = listOf(
        StoreItem(
            id = "1",
            categoryId = "7",
            name = "Spices",
            imgUrl = listOf(
                "https://manual.co.id/wp-content/uploads/2019/06/Bulk-Store-Shop_Menteng-5-980x719.jpg",
                "https://manual.co.id/wp-content/uploads/2019/06/Bulk-Store-Shop_Menteng-3-980x719.jpg"
            ),
            description = "Spices which is consumed for body care, we used jar which is refillable",
            location = "Menteng, Central Jakarta",
            storeName = "The Bulkstore & Co.",
            storeImgUrl = "https://sp-ao.shortpixel.ai/client/to_auto,q_lossy,ret_img,w_601/https://www.flokq.com/blog/wp-content/uploads/2020/06/the-bulkstore-_-co.png",
            storeId = "1",
            rating = 5.0
        ),
        StoreItem(
            id = "2",
            categoryId = "7",
            name = "Spices and Ingredients",
            imgUrl = listOf(
                "https://hijaubintaro.id/wp-content/uploads/2018/11/saruga-1.jpg",
                "https://www.bintarojaya.id/images/news/bojak.jpg"
            ),
            description = "Spices and Ingredients which is healthy and natural, please bring your own container.",
            location = "Bintaro, South Jakarta",
            storeName = "Saruga Package-Free Shopping Store",
            storeImgUrl = "https://sp-ao.shortpixel.ai/client/to_auto,q_lossy,ret_img,w_601/https://www.flokq.com/blog/wp-content/uploads/2020/06/saruga.png",
            storeId = "2",
            rating = 5.0
        ),
        StoreItem(
            id = "3",
            categoryId = "8",
            name = "Stainless Steel Straw",
            imgUrl = listOf(
                "https://images.tokopedia.net/img/cache/700/product-1/2019/5/11/4610400/4610400_f16c38af-82b9-4b0a-a46d-3a8109b59ef3_605_605"
            ),
            description = "Stainless Steel Straw, stop using plastic!",
            location = "Kemang, South Jakarta",
            storeName = "Naked Inc.",
            storeImgUrl = "https://sp-ao.shortpixel.ai/client/to_auto,q_lossy,ret_img,w_601/https://www.flokq.com/blog/wp-content/uploads/2020/06/naked.png",
            storeId = "3",
            rating = 5.0
        ),
        StoreItem(
            id = "4",
            categoryId = "1",
            name = "\"Ayam\" Outer",
            imgUrl = listOf(
                "https://sejauh.com/cdn/shop/products/sejauh_timun_mas-1960_f749087c-0bd5-4bff-9a57-c82a0b58dcd5.jpg?v=1620114152"
            ),
            description = "Our all-time favourite outerwear with the iconic chicken mascot made with the softest tencel fabric. Versatile for any occasion, layer it over a dress, blouse or even swimwear",
            location = "Kemang, South Jakarta",
            storeName = "Sejauh Mata Memandang",
            storeImgUrl = "https://sp-ao.shortpixel.ai/client/to_auto,q_lossy,ret_img,w_601/https://www.flokq.com/blog/wp-content/uploads/2020/06/sejauh.png",
            storeId = "4",
            rating = 5.0
        )
    )

    val stores: List<Store> = listOf(
        Store(
            id = "1",
            categoryId = "7",
            name = "The Bulkstore & Co.",
            imgUrl = "https://sp-ao.shortpixel.ai/client/to_auto,q_lossy,ret_img,w_601/https://www.flokq.com/blog/wp-content/uploads/2020/06/the-bulkstore-_-co.png",
            location = "Menteng, Central Jakarta",
            rating = 5.0,
            description = "The products that are sold in this store- from spices to body care products- are displayed in glass containers. Because they don’t use plastic bags, buyers should bring their own containers to take their groceries home. If you don’t have any containers, you can buy the ones they sell in the store. Of course, it’s reusable! Once you bought them, don’t forget to take them on your next purchase. ",
            totalReview = 10
        ),
        Store(
            id = "2",
            categoryId = "7",
            name = "Saruga Package-Free Shopping Store",
            imgUrl = "https://sp-ao.shortpixel.ai/client/to_auto,q_lossy,ret_img,w_601/https://www.flokq.com/blog/wp-content/uploads/2020/06/saruga.png",
            location = "Bintaro, South Jakarta",
            rating = 5.0,
            description = "Located in Bintaro, Saruga is not only package-free, but they also let you buy things in bulks. You can decide how much you need something so it won’t be wasted. Not only edible goods, but they also sell a line of natural body care products such as shampoo, soap, and aromatherapy. If you want to try switching the conventional straws with the stainless ones, you can buy them here too! ",
            totalReview = 20
        ),
        Store(
            id = "3",
            categoryId = "8",
            name = "Naked Inc.",
            imgUrl = "https://sp-ao.shortpixel.ai/client/to_auto,q_lossy,ret_img,w_601/https://www.flokq.com/blog/wp-content/uploads/2020/06/naked.png",
            location = "Kemang, South Jakarta",
            rating = 5.0,
            description = "Naked Inc. is a small shop that supports sustainable living. They realize the fact that the products that are sold in conventional stores are often thrown away after people consume them. It is because the portion of the products isn’t equal to the intake of the customers. That’s why sustainable stores sell things in bulk- to create less waste in our everyday lives. If you are just about to start a sustainable living, buy their starter pack! It consists of straws, toothbrushes, and razors. These are the things that are usually made out of plastic. Here, they use stainless steel instead!",
            totalReview = 30
        ),
        Store(
            id = "4",
            categoryId = "1",
            name = "Sejauh Mata Memandang",
            imgUrl = "https://sp-ao.shortpixel.ai/client/to_auto,q_lossy,ret_img,w_601/https://www.flokq.com/blog/wp-content/uploads/2020/06/sejauh.png",
            location = "Kemang, South Jakarta",
            rating = 5.0,
            description = "Sejauh Mata Memandang is a textile label which sells high quality products, while considering the impacts they give to the environment and the society. Their products range from apparel, multifunctional cloths, scarves, shawls, and many more. Occasionally, they launch products that symbolize special moments. For example, when the semanggi flyover was officiated, Sejauh Mata Memandang launched their well-known ‘batik semanggi’ which small shapes represent clover (the shape of semanggi flyover).",
            totalReview = 40
        )
    )

    val user = User(
        id = "1",
        name = "Darren Thiores",
        imageUrl = manImageUrl,
        email = "darrenthiores@gmail.com",
        rating = 5.0,
        totalReview = 3613,
        location = "North Jakarta"
    )

    val users: List<User> = listOf(
        User(
            id = "1",
            name = "Darren Thiores",
            imageUrl = manImageUrl,
            email = "darrenthiores@gmail.com",
            rating = 5.0,
            totalReview = 3613,
            location = "North Jakarta"
        ),
        User(
            id = "2",
            name = "Rifqi Arkaan",
            imageUrl = manImageUrl,
            email = "rifqiarkaan@gmail.com",
            rating = 4.5,
            totalReview = 10,
            location = "West Jakarta"
        ),
        User(
            id = "3",
            name = "Fidelia Maria",
            imageUrl = womanImageUrl,
            email = "fideliamaria@gmail.com",
            rating = 5.0,
            totalReview = 20,
            location = "North Jakarta"
        ),
        User(
            id = "4",
            name = "Steven Enceil",
            imageUrl = manImageUrl,
            email = "stevenenceil@gmail.com",
            rating = 4.0,
            totalReview = 30,
            location = "Lampung"
        ),
        User(
            id = "5",
            name = "Christoper Sul",
            imageUrl = manImageUrl,
            email = "christosul@gmail.com",
            rating = 4.8,
            totalReview = 40,
            location = "West Jakarta"
        ),
        User(
            id = "6",
            name = "Vincent Yo",
            imageUrl = manImageUrl,
            email = "vincentyo@gmail.com",
            rating = 4.1,
            totalReview = 60,
            location = "Pontianak"
        ),
        User(
            id = "7",
            name = "Kevin Valentino",
            imageUrl = manImageUrl,
            email = "kevinval@gmail.com",
            rating = 4.0,
            totalReview = 70,
            location = "West Jakarta"
        ),
        User(
            id = "8",
            name = "Stevan Wijaya",
            imageUrl = manImageUrl,
            email = "stevanwii@gmail.com",
            rating = 3.7,
            totalReview = 80,
            location = "Palembang"
        ),
        User(
            id = "9",
            name = "Asyam",
            imageUrl = manImageUrl,
            email = "asyam@gmail.com",
            rating = 4.1,
            totalReview = 80,
            location = "South Jakarta"
        ),
        User(
            id = "10",
            name = "Charles Yansen",
            imageUrl = manImageUrl,
            email = "charlesyan@gmail.com",
            rating = 4.0,
            totalReview = 90,
            location = "Pontianak"
        ),
        User(
            id = "11",
            name = "Nathan Ginta Thiores",
            imageUrl = manImageUrl,
            email = "nathanginta@gmail.com",
            rating = 4.9,
            totalReview = 100,
            location = "North Jakarta"
        )
    )

    val reviews: List<Review> = listOf(
        Review(
            id = "1",
            review = "the item is in very good condition, i very happy to do the trade",
            reviewedUserId = "4",
            userId = "3",
            username = "Fidelia Maria",
            rating = 5.0,
            date = Clock.System.now().toEpochMilliseconds(),
            userImgUrl = womanImageUrl
        ),
        Review(
            id = "2",
            review = "the item is not bad, just i waited for too long at the appointment place",
            reviewedUserId = "4",
            userId = "11",
            username = "Nathan Ginta Thiores",
            rating = 4.9,
            date = Clock.System.now().toEpochMilliseconds(),
            userImgUrl = manImageUrl
        )
    )

    val storeReviews: List<StoreReview> = listOf(
        StoreReview(
            id = "1",
            review = "Bought spices from the store, very good concept!",
            storeId = "1",
            userId = "1",
            username = "Darren Thiores",
            userImgUrl = manImageUrl,
            rating = 5.0,
            date = Clock.System.now().toEpochMilliseconds()
        ),
        StoreReview(
            id = "2",
            review = "I bought the outer for my girlfriend, she loves it!",
            storeId = "4",
            userId = "1",
            username = "Darren Thiores",
            userImgUrl = manImageUrl,
            rating = 5.0,
            date = Clock.System.now().toEpochMilliseconds()
        )
    )

    val inboxes: List<Inbox> = listOf(
        Inbox(
            id = "1",
            sentFromId = "1",
            sentToId = "2",
            sentToUsername = "Rifqi Arkaan",
            sentToImageUrl = manImageUrl,
            lastMessage = "hey bro!",
            lastSendUserId = "1"
        ),
        Inbox(
            id = "2",
            sentFromId = "1",
            sentToId = "3",
            sentToUsername = "Fidelia Maria",
            sentToImageUrl = womanImageUrl,
            lastMessage = "hey man!",
            lastSendUserId = "3"
        )
    )

    val challenges: List<Challenge> = listOf(
        Challenge(
            id = "1",
            title = "Better Transport",
            description = "this challenge is about to use a better transportation than cars or motorcycles to reduce the co2 produced. Please note that every task is done per 500m either Walking or using Bike.",
            tasks = listOf(
                Challenge.Task(
                    id = "T1",
                    task = "Walking",
                    carbonReduced = 500.0
                ),
                Challenge.Task(
                    id = "T2",
                    task = "Bike",
                    carbonReduced = 400.0
                )
            ),
            deadline = DateUtils.now(),
            goals = 10000.0,
            progress = 1400.0,
            participants = listOf(
                Challenge.Participant(
                    id = "2",
                    name = "Rifqi Arkaan",
                    imageUrl = manImageUrl,
                    progress = 500.0
                ),
                Challenge.Participant(
                    id = "3",
                    name = "Fidelia Maria",
                    imageUrl = womanImageUrl,
                    progress = 500.0
                ),
                Challenge.Participant(
                    id = "11",
                    name = "Nathan Ginta Thiores",
                    imageUrl = manImageUrl,
                    progress = 400.0
                )
            ),
            isJoined = false
        ),
        Challenge(
            id = "2",
            title = "Save Water",
            description = "Some houses use electricity to gain water, and if your house do, then this challenge suits you, our goals is to reduce water usage which will lead to less energy consumption, Please note that the task is per 10liter of less water used, or per 30 minutes of less electricity used",
            tasks = listOf(
                Challenge.Task(
                    id = "T1",
                    task = "Reduce Water Usage",
                    carbonReduced = 30.0
                ),
                Challenge.Task(
                    id = "T2",
                    task = "Using another source of energy to gain water",
                    carbonReduced = 50.0
                ),
                Challenge.Task(
                    id = "T3",
                    task = "Using Solar Panel",
                    carbonReduced = 100.0
                )
            ),
            deadline = DateUtils.now(),
            goals = 20000.0,
            progress = 130.0,
            participants = listOf(
                Challenge.Participant(
                    id = "10",
                    name = "Charles Yansen",
                    imageUrl = manImageUrl,
                    progress = 100.0
                ),
                Challenge.Participant(
                    id = "3",
                    name = "Fidelia Maria",
                    imageUrl = womanImageUrl,
                    progress = 30.0
                )
            ),
            isJoined = true
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