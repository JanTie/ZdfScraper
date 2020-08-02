package digital.tiedemann.zdfmediathekscraper.view.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import digital.tiedemann.zdfmediathekscraper.R
import digital.tiedemann.zdfmediathekscraper.service.repository.Repository
import digital.tiedemann.zdfmediathekscraper.util.setVisibleOrGone
import digital.tiedemann.zdfmediathekscraper.view.adapter.decoration.BottomSpacingDecoration
import kotlinx.android.synthetic.main.fragment_items.*

abstract class BaseResourceListFragment : Fragment(R.layout.fragment_items) {

    protected abstract val adapter: RecyclerView.Adapter<*>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = adapter
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(
            BottomSpacingDecoration(
                requireContext().resources.getDimensionPixelSize(R.dimen.recycler_item_margin)
            )
        )

        swipe_refresh_layout.setOnRefreshListener(::onRefresh)
    }

    abstract fun onRefresh()

    protected fun updateViewVisibility(resource: Repository.Resource<*>) {
        tv_error.setVisibleOrGone(resource is Repository.Resource.Error)
        recycler_view.setVisibleOrGone(resource is Repository.Resource.Success)
        swipe_refresh_layout.isRefreshing = resource is Repository.Resource.Loading
    }
}