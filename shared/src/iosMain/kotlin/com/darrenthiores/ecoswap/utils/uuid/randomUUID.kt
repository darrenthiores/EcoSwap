package com.darrenthiores.ecoswap.utils.uuid

import platform.Foundation.NSUUID

actual fun randomUUID(): String = NSUUID().UUIDString()