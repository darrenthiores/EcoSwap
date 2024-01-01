package com.darrenthiores.ecoswap.data.core.local.driver

import com.darrenthiores.ecoswap.database.AppDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(
            AppDatabase.Schema,
            "app.db"
        )
    }
}