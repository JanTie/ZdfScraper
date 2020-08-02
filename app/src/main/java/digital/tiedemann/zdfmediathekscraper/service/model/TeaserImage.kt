package digital.tiedemann.zdfmediathekscraper.service.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TeaserImage(
    val url: String,
    val width: Int,
    val height: Int
)