package digital.tiedemann.zdfmediathekscraper.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import digital.tiedemann.zdfmediathekscraper.R
import digital.tiedemann.zdfmediathekscraper.service.model.TeaserImage
import kotlinx.android.synthetic.main.item_image.view.*

class ImagesAdapter(private var items: List<TeaserImage> = listOf()) :
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    fun setList(items: List<TeaserImage>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: TeaserImage) {
            Glide.with(itemView).load(data.url).into(itemView.image_view)
        }
    }
}
