package uz.gita.newdictionaryenglishuzbekgb.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.newdictionaryenglishuzbekgb.data.models.WordData

/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
@Entity(tableName = "dictionary")
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "english")
    val engName: String,
    @ColumnInfo(name = "uzbek")
    val uzbName: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "transcript")
    val transcript: String,
    @ColumnInfo(name = "countable")
    val countable: String?,
    @ColumnInfo(name = "is_favourite")
    val isFavourite: Boolean
) {
    fun toWordData() = WordData(id, engName, uzbName, type, transcript, countable, isFavourite)
}
