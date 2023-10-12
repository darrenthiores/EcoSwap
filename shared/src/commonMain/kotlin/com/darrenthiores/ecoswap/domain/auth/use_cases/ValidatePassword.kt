package com.darrenthiores.ecoswap.domain.auth.use_cases

import com.darrenthiores.ecoswap.domain.utils.Resource

class ValidatePassword {
    operator fun invoke(
        password: String
    ): Resource<Unit> {
        if (password.length < 8) {
            return Resource.Error(
                "Password used to has at least 8 characters"
            )
        }

        return Resource.Success(Unit)
    }
}