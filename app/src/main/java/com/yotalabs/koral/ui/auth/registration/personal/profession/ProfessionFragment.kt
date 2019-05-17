package com.yotalabs.koral.ui.auth.registration.personal.profession

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.yotalabs.koral.R
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.ui.adapters.DelegateAdapter
import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.adapters.decorators.LinearDecorator
import com.yotalabs.koral.ui.adapters.delegates.ProfessionDelegateAdapter
import com.yotalabs.koral.ui.adapters.diffs.ProfessionItemDiffUtilsCallback
import com.yotalabs.koral.ui.mvp.BaseFragment
import com.yotalabs.koral.utils.dp
import kotlinx.android.synthetic.main.fragment_profession.*
import kotlinx.android.synthetic.main.item_toolbar_purple.*
import org.koin.android.ext.android.inject

/**
 * @author SashaKhyzhun
 * Created on 5/8/19.
 */

class ProfessionFragment : BaseFragment(), ProfessionView {

    interface Callback {
        fun redirectFromChooseProfessionToChooseService()
        fun redirectFromChooseProfessionToOther()
    }

    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: ProfessionPresenter

    private val professionDelegateAdapter by lazy {
        DelegateAdapter<DisplayableItem>()
    }

    private var callback: Callback? = null

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
        return inflater.inflate(R.layout.fragment_profession, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        item_toolbar_title.text = TITLE

        professionDelegateAdapter.delegatesManager.addDelegate(
            ProfessionDelegateAdapter(
                presenter::onClickProfession,
                ::bindUntilDestroy)
        )

        with (fragment_profession_rv) {
            adapter = professionDelegateAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )

            addItemDecoration(
                LinearDecorator(
                    20.dp(),
                    14.dp(),
                    20.dp(),
                    24.dp()
                )
            )
        }

    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }


    override fun renderView(values: List<DisplayableItem>) {
        professionDelegateAdapter.set(values) { old, new ->
            ProfessionItemDiffUtilsCallback(old, new)
        }
    }

    override fun onProfessionChosen() {
        callback?.redirectFromChooseProfessionToChooseService()
    }

    override fun onOtherChosen() {
        callback?.redirectFromChooseProfessionToOther()
    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun renderError(throwable: Throwable) {

    }

    override fun renderMessage(text: String) {

    }

    companion object {
        const val TITLE = "Choose Profession"
        const val TAG = "ProfessionFragment"
        fun newInstance() = ProfessionFragment()
    }
}