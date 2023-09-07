package com.darrenthiores.ecoswap

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform