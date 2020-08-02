package digital.tiedemann.zdfmediathekscraper.view.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.muellerwulff.simpleviewpager.Page
import com.muellerwulff.simpleviewpager.SimplePage
import com.muellerwulff.simpleviewpager.toPagerAdapter
import digital.tiedemann.zdfmediathekscraper.R
import kotlinx.android.synthetic.main.fragment_main.*

class ZdfStartPageFragment : Fragment(R.layout.fragment_main) {

    private val pages: List<Page> by lazy {
        listOf(
            SimplePage(R.string.tab_title_items) { ZdfStartPageItemFragment.newInstance() },
            SimplePage(R.string.tab_title_images) { ZdfStartPageImagesFragment.newInstance() }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewpager.adapter = pages.toPagerAdapter(childFragmentManager)
        tab_layout.setupWithViewPager(viewpager)
    }

    companion object {
        fun newInstance() =
            ZdfStartPageFragment()
    }
}