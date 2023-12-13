package uz.gita.newdictionaryenglishuzbekgb.utils

import androidx.recyclerview.widget.DiffUtil
import uz.gita.newdictionaryenglishuzbekgb.data.models.WordData

/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
class WordItemCallback(
    private val oldWordList: List<WordData>,
    private val newWordList: List<WordData>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldWordList.size

    override fun getNewListSize(): Int = newWordList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldWordList[oldItemPosition] == newWordList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldWordList[oldItemPosition]
        val new = newWordList[newItemPosition]

        return old.id == new.id && old.engName == new.engName && old.uzbName == new.uzbName
    }
}