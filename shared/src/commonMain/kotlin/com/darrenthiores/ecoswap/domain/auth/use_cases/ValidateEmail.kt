package com.darrenthiores.ecoswap.domain.auth.use_cases

import com.darrenthiores.ecoswap.domain.utils.Resource

class ValidateEmail {

    operator fun invoke(
        email: String
    ): Resource<Unit> {
       if (!isValidEmail(email)) {
           return Resource.Error(
               "Email format is wrong!"
           )
       }

        return Resource.Success(Unit)
    }

    private fun isValidEmail(email: String): Boolean {
        val emailAddressRegex = Regex(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        return email.matches(emailAddressRegex)
    }
}