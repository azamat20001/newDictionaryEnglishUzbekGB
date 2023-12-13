package uz.gita.newdictionaryenglishuzbekgb.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
fun ViewGroup.inflate(resId: Int): View {
    return LayoutInflater.from(this.context).inflate(resId, this, false)
}