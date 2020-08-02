package digital.tiedemann.zdfmediathekscraper.service.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StartPageItem(
    val headline: String,
    @Json(name = "titel")
    val title: String,
    @Json(name = "beschreibung")
    val description: String,
    @Json(name = "teaserBild")
    val teaserImage: Map<Int, TeaserImage>
)