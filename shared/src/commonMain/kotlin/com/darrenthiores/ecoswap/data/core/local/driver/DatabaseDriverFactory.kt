package com.darrenthiores.ecoswap.data.core.local.driver

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun create(): SqlDriver
}