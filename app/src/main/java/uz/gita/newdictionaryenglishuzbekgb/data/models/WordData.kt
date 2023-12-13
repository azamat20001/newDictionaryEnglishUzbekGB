package uz.gita.newdictionaryenglishuzbekgb.data.models

import uz.gita.newdictionaryenglishuzbekgb.data.local.room.entity.WordEntity
import java.io.Serializable

/**
Mobile Developer
Creator:Mustafoyev Azamat
 */

data class WordData(
    val id: Int,
    val engName: String,
    val uzbName: String,
    val type: String,
    val transcript: String,
    val countable: String?,
    var isFavourite: Boolean
) : Serializable {
    fun toWordEntity() = WordEntity(id, engName, uzbName, type, transcript, countable, isFavourite)
}    