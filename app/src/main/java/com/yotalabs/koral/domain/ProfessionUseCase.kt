package com.yotalabs.koral.domain

import com.yotalabs.koral.ui.adapters.models.ProfessionItem
import io.reactivex.Observable


/**
 * @author SashaKhyzhun
 * Created on 2019-05-14.
 */
interface ProfessionUseCase {

    fun fetchProfessions(): Observable<List<ProfessionItem>>


}