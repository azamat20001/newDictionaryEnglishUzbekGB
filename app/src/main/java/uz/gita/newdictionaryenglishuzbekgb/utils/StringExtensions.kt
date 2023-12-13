package uz.gita.newdictionaryenglishuzbekgb.utils

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan


/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
fun String.coloredString(query: String?): Spannable {
    val spannable = SpannableStringBuilder(this)
    if (query == null) return spannable
    if (!this.contains(query)) return spannable
    if (query == this) {
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#FF5690F1")),
            0, this.length,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        return spannable
    }
    val start = this.indexOf(query)
    val end = query.length + start
    spannable.setSpan(
        ForegroundColorSpan(Color.parseColor("#FF5690F1")),
        start, end,
        Spannable.SPAN_EXCLUSIVE_INCLUSIVE
    )

    return spannable

}