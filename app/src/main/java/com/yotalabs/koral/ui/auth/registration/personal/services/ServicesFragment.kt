package com.yotalabs.koral.ui.auth.registration.personal.services

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.jakewharton.rxbinding2.view.clicks
import com.mancj.slideup.SlideUp
import com.yotalabs.koral.R
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.ui.adapters.DelegateAdapter
import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.adapters.decorators.LinearDecorator
import com.yotalabs.koral.ui.adapters.delegates.ServicesDelegateAdapter
import com.yotalabs.koral.ui.adapters.delegates.SubServicesDelegateAdapter
import com.yotalabs.koral.ui.adapters.diffs.ServiceItemDiffUtilsCallback
import com.yotalabs.koral.ui.adapters.diffs.SubServiceItemDiffUtilsCallback
import com.yotalabs.koral.ui.adapters.models.ServiceItem
import com.yotalabs.koral.ui.adapters.models.SubServiceItem
import com.yotalabs.koral.ui.mvp.BaseActivity
import com.yotalabs.koral.ui.mvp.BaseFragment
import com.yotalabs.koral.utils.dp
import com.yotalabs.koral.utils.setGone
import com.yotalabs.koral.utils.setVisible
import kotlinx.android.synthetic.main.fragment_services.*
import kotlinx.android.synthetic.main.item_slide_up_services.*
import kotlinx.android.synthetic.main.item_toolbar_purple.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

/**
 * @author SashaKhyzhun
 * Created on 2019-05-15.
 */
@SuppressLint("CheckResult")
class ServicesFragment : BaseFragment(), ServicesView {

    interface Callback {
        fun redirectFromServicesToProfessions()
    }

    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: ServicesPresenter

    private var callback: Callback? = null

    private val servicesAdapter by lazy {
        DelegateAdapter<DisplayableItem>()
    }

    private val subServicesAdapter by lazy {
        DelegateAdapter<DisplayableItem>()
    }

//    lateinit var slideUp: SlideUp

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Callback) {
            callback = context
        } else {
            throw RuntimeException("$context must implement Callback")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_services, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        item_toolbar_title.text = TITLE

        item_toolbar_back_button.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { callback?.redirectFromServicesToProfessions() }


        servicesAdapter.delegatesManager.addDelegate(
            ServicesDelegateAdapter(
                presenter::onClickOnService,
                ::bindUntilDestroy
            )
        )

        with (fragment_services_rv_services) {
            adapter = servicesAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.HORIZONTAL,
                false
            )

            //addItemDecoration(LinearDecorator(20.dp(), 14.dp(), 20.dp(), 24.dp()))
        }

        subServicesAdapter.delegatesManager.addDelegate(
            SubServicesDelegateAdapter(
                presenter::onClickOnSubService,
                ::bindUntilDestroy
            )
        )

        with (fragment_services_rv_sub_services) {
            adapter = subServicesAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )

            addItemDecoration(
                LinearDecorator(
                    top = 8.dp(),
                    bottom = 8.dp(),
                    itemsBottom = 8.dp(),
                    sideOffset = 24.dp()
                )
            )
        }

//        val slideView = view.findViewById<View>(R.id.item_slide_up_layout_parent)
//        slideUp = SlideUp(slideView)
//        slideUp.hideImmediately()

//        slideUp.setSlideListener(object : SlideUp.SlideListener {
//            override fun onSlideDown(percent: Float) {/*what to do while slide down*/ }
//            override fun onVisibilityChanged(visibility: Int) {/*show or hide views*/ }
//        })

        // slideUp.animateIn() //showView

//        item_slide_up_services_btn_apply.clicks()
//            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
//            .compose(bindUntilDestroy())
//            .observeOn(schedulers.mainThread())
//            .subscribe {
//                presenter.onClickApply(
//                    item_slide_up_services_sb_price.progress,
//                    item_slide_up_services_sb_duration.progress
//                )
//            }



    }


    override fun renderServices(categories: List<ServiceItem>) {
        servicesAdapter.set(categories) { old, new ->
            ServiceItemDiffUtilsCallback(old, new)
        }
    }

    override fun renderSubServices(subCategories: List<SubServiceItem>) {
        subServicesAdapter.set(subCategories) { old, new ->
            SubServiceItemDiffUtilsCallback(old, new)
        }
    }

    override fun onApplyClicked() {

    }

    override fun renderError(throwable: Throwable) {
        showSnack(throwable.message)
    }

    override fun renderMessage(text: String) {
        showSnack(text)
    }

    override fun hideTextAddFewServices() {
        fragment_services_tv_add_a_few_services.setGone()
    }

    override fun showSubCategoriesRecycler() {
        fragment_services_rv_sub_services.setVisible()
    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    companion object {
        const val TITLE = "Add Services"
        const val TAG = "ServicesFragment"
        fun newInstance() = ServicesFragment()
    }
}