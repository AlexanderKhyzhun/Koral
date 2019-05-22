package com.yotalabs.koral.domain.impl

import com.yotalabs.koral.data.Api
import com.yotalabs.koral.data.storages.SignUpRepository
import com.yotalabs.koral.domain.ServicesUseCase
import com.yotalabs.koral.ui.adapters.models.ServiceItem
import com.yotalabs.koral.ui.adapters.models.SubServiceItem
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class ServicesUseCaseImpl(
    private val api: Api,
    private val signUp: SignUpRepository
) : ServicesUseCase {

    override fun fetchCategories(): Single<List<ServiceItem>> {
        return Single.fromCallable {
            listOf<ServiceItem>(
                ServiceItem("1", "Barber"),
                ServiceItem("2", "Men's haircut"),
                ServiceItem("3", "Kids"),
                ServiceItem("4", "Women"),
                ServiceItem("5", "Qwerty"),
                ServiceItem("6", "Barber")
            )
        }
    }

    override fun fetchSubCategoryByCategory(categoryTitle: String): Single<List<SubServiceItem>> {
        return Single.fromCallable {
            when (categoryTitle) {
                "Barber" -> {
                    listOf<SubServiceItem>(
                        SubServiceItem("1", "Men's Cut"),
                        SubServiceItem("2", "Buzz Cut"),
                        SubServiceItem("3", "Design"),
                        SubServiceItem("4", "Edge Up"),
                        SubServiceItem("5", "Fade")
                    )
                }
                else -> {
                    listOf<SubServiceItem>(
                        SubServiceItem("1", "Computer"),
                        SubServiceItem("2", "Animal"),
                        SubServiceItem("3", "Orange"),
                        SubServiceItem("4", "School"),
                        SubServiceItem("5", "Tree")
                    )
                }
            }
        }
    }

    override fun selectedSubServices(): BehaviorSubject<MutableList<SubServiceItem>>
            = signUp.selectedSubServices()

    override fun addSubServiceToSelected(item: SubServiceItem) {
        signUp.subServices().onNext(item)
    }

    override fun changeSelectedService(item: ServiceItem) {
        signUp.selectedService().onNext(item)
    }

}
