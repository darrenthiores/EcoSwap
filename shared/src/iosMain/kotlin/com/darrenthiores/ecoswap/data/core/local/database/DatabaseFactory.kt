package com.darrenthiores.ecoswap.data.core.local.database

import com.darrenthiores.ecoswap.database.AppDatabase
import com.squareup.sqldelight.db.SqlDriver

class DatabaseFactory {
    fun createDatabase(driver: SqlDriver): AppDatabase {
        return AppDatabase(driver)
    }
}