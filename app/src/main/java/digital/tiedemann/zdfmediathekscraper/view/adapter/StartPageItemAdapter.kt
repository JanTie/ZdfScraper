package digital.tiedemann.zdfmediathekscraper.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import digital.tiedemann.zdfmediathekscraper.R
import digital.tiedemann.zdfmediathekscraper.service.model.StartPageItem
import kotlinx.android.synthetic.main.item_article.view.*

class StartPageItemAdapter : RecyclerView.Adapter<StartPageItemAdapter.ViewHolder>() {
    private var items = listOf<StartPageItem>()

    fun setList(items: List<StartPageItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: StartPageItem) {
            itemView.tv_title.text = data.title
            itemView.tv_headline.text = data.headline
            itemView.tv_description.text = data.description
        }
    }
}