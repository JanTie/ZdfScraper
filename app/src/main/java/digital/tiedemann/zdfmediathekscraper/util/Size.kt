package digital.tiedemann.zdfmediathekscraper.util

import android.content.Context
import android.util.TypedValue

fun Float.toPx(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this,
    context.resources.displayMetrics
)