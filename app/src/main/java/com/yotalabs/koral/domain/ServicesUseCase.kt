package com.yotalabs.koral.domain

import com.yotalabs.koral.ui.adapters.models.ServiceItem
import com.yotalabs.koral.ui.adapters.models.SubServiceItem
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

interface ServicesUseCase {

    fun fetchCategories(): Single<List<ServiceItem>>

    fun fetchSubCategoryByCategory(categoryTitle: String): Single<List<SubServiceItem>>

    fun addSubServiceToSelected(item: SubServiceItem)

    fun changeSelectedService(item: ServiceItem)

    fun selectedSubServices(): BehaviorSubject<MutableList<SubServiceItem>>

}
