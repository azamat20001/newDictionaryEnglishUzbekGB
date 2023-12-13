package uz.gita.newdictionaryenglishuzbekgb.data.local.room.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import uz.gita.newdictionaryenglishuzbekgb.data.local.room.entity.WordEntity

/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
@Dao
interface  WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(wordEntity: WordEntity)

    @Query("SELECT*FROM dictionary")
    fun getAllWords(): Cursor

    @Query("SELECT*FROM dictionary")
    fun getAllWord(): LiveData<List<WordEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateWord(wordEntity: WordEntity)

    @Query("SELECT*FROM dictionary WHERE is_favourite")
    fun getAllFavouriteWords(): LiveData<List<WordEntity>>

    @Query("SELECT*FROM dictionary WHERE english LIKE '%' || :query || '%'")
    suspend fun getWordsByQuery(query: String): List<WordEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun allUpdateWords(list: List<WordEntity>)

}