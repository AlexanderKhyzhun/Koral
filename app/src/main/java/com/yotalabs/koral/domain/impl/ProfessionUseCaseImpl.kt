package com.yotalabs.koral.domain.impl

import com.yotalabs.koral.data.Api
import com.yotalabs.koral.data.storages.SignUpPersonalRepository
import com.yotalabs.koral.domain.ProfessionUseCase
import com.yotalabs.koral.ui.adapters.models.ProfessionItem
import io.reactivex.Observable

class ProfessionUseCaseImpl(
    private val api: Api,
    private val signUp: SignUpPersonalRepository
) : ProfessionUseCase {

    override fun fetchProfessions(): Observable<List<ProfessionItem>> {
        return Observable.fromCallable {
            listOf(
                ProfessionItem(HAIRCUTS, item_haircuts),
                ProfessionItem(BARBERS, item_barbers),
                ProfessionItem(MAKEUP, item_makeup),
                ProfessionItem(COSMETOLOGISTS, item_cosmetologists),
                ProfessionItem(SPA_AND_MASSAGE, item_spa_and_massage),
                ProfessionItem(NAILS, item_nails),
                ProfessionItem(TATTOO, item_tattoo),
                ProfessionItem(OTHER, item_other)
            )
        }
    }

    fun onProfessionChosen(item: ProfessionItem) {
        signUp.profession().onNext(item)
    }


    companion object {
        const val HAIRCUTS = "profession_id_haircuts"
        const val BARBERS = "profession_id_barbers"
        const val MAKEUP = "profession_id_makeup"
        const val COSMETOLOGISTS = "profession_id_cosmetologists"
        const val SPA_AND_MASSAGE = "profession_id_spa_and_massage"
        const val NAILS = "profession_id_nails"
        const val TATTOO = "profession_id_tattoo"
        const val OTHER = "profession_id_other"

        const val item_haircuts = "haircuts"
        const val item_barbers = "barbers"
        const val item_makeup = "makeup"
        const val item_cosmetologists = "cosmetologists"
        const val item_spa_and_massage = "spa/massage"
        const val item_nails = "nails"
        const val item_tattoo = "tattoo"
        const val item_other = "other profession"

    }
}
