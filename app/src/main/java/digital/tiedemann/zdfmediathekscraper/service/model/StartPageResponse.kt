package digital.tiedemann.zdfmediathekscraper.service.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StartPageResponse(
    val stage: List<StartPageItem>
)