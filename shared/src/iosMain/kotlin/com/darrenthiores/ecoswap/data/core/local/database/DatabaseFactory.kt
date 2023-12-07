package com.darrenthiores.ecoswap.data.core.local.database

import com.darrenthiores.ecoswap.database.ChatDatabase
import com.squareup.sqldelight.db.SqlDriver

class DatabaseFactory {
    fun createDatabase(driver: SqlDriver): ChatDatabase {
        return ChatDatabase(driver)
    }
}