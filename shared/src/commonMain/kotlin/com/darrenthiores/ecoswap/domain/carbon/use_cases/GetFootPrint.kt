package com.darrenthiores.ecoswap.domain.carbon.use_cases

import com.darrenthiores.ecoswap.domain.carbon.model.CarbonView
import com.darrenthiores.ecoswap.domain.carbon.model.FootPrint
import com.darrenthiores.ecoswap.domain.carbon.repository.CarbonRepository
import com.darrenthiores.ecoswap.domain.utils.Resource

class GetFootPrint(
    private val repository: CarbonRepository
) {
    suspend operator fun invoke(
        fetch: Boolean = false,
        view: CarbonView
    ): Resource<FootPrint> {
        return repository
            .getFootPrint(
                fetch = fetch,
                viewId = view.id
            )
    }
}