package digital.tiedemann.zdfmediathekscraper.view.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import digital.tiedemann.zdfmediathekscraper.R
import digital.tiedemann.zdfmediathekscraper.service.repository.Repository
import digital.tiedemann.zdfmediathekscraper.util.setVisibleOrGone
import digital.tiedemann.zdfmediathekscraper.view.adapter.ImagesAdapter
import digital.tiedemann.zdfmediathekscraper.view.adapter.decoration.BottomSpacingDecoration
import digital.tiedemann.zdfmediathekscraper.viewmodel.ZdfStartPageImageViewModel
import kotlinx.android.synthetic.main.fragment_items.*

class ZdfStartPageImagesFragment : Fragment(R.layout.fragment_items) {

    private val viewModel by activityViewModels<ZdfStartPageImageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ImagesAdapter()
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(
            BottomSpacingDecoration(
                requireContext().resources.getDimensionPixelSize(R.dimen.recycler_item_margin)
            )
        )

        swipe_refresh_layout.setOnRefreshListener {
            viewModel.refreshData()
        }

        viewModel.images.observe(viewLifecycleOwner) {
            tv_error.setVisibleOrGone(it is Repository.Resource.Error)
            recycler_view.setVisibleOrGone(it is Repository.Resource.Success)
            swipe_refresh_layout.isRefreshing = it is Repository.Resource.Loading
            when (it) {
                is Repository.Resource.Error -> tv_error.text = it.message
                is Repository.Resource.Success -> adapter.setList(it.data ?: listOf())
            }
        }
    }

    companion object {
        fun newInstance() =
            ZdfStartPageImagesFragment()
    }
}