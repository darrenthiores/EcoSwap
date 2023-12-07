package com.darrenthiores.ecoswap.data.core.local.driver

import com.darrenthiores.ecoswap.database.ChatDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(
            ChatDatabase.Schema,
            "chat.db"
        )
    }
}