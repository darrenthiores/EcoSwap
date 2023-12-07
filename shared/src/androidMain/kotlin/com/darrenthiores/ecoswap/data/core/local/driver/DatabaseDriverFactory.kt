package com.darrenthiores.ecoswap.data.core.local.driver

import android.content.Context
import com.darrenthiores.ecoswap.database.ChatDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context
){
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            schema = ChatDatabase.Schema,
            context = context,
            name = "chat.db"
        )
    }
}