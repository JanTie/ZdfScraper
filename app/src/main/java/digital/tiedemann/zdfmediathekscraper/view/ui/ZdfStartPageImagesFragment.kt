package digital.tiedemann.zdfmediathekscraper.view.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import digital.tiedemann.zdfmediathekscraper.service.repository.Repository
import digital.tiedemann.zdfmediathekscraper.view.adapter.ImagesAdapter
import digital.tiedemann.zdfmediathekscraper.viewmodel.ZdfStartPageImageViewModel
import kotlinx.android.synthetic.main.fragment_items.*

class ZdfStartPageImagesFragment : BaseResourceListFragment() {

    override val adapter by lazy { ImagesAdapter() }

    private val viewModel by activityViewModels<ZdfStartPageImageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.images.observe(viewLifecycleOwner) {
            updateViewVisibility(it)
            when (it) {
                is Repository.Resource.Error -> tv_error.text = it.message
                is Repository.Resource.Success -> adapter.setList(it.data ?: listOf())
            }
        }
    }

    override fun onRefresh() = viewModel.refreshData()

    companion object {
        fun newInstance() =
            ZdfStartPageImagesFragment()
    }
}