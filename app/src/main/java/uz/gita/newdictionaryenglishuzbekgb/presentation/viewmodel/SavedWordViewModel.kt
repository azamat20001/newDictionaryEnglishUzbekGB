package uz.gita.newdictionaryenglishuzbekgb.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import uz.gita.newdictionaryenglishuzbekgb.data.local.room.AppDatabase
import uz.gita.newdictionaryenglishuzbekgb.data.models.WordData

/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
class SavedWordViewModel : ViewModel() {

    private val dao = AppDatabase.getInstance().wordDao()

    val allFavouriteWords: LiveData<List<WordData>> = dao.getAllFavouriteWords().map { list ->
        list.map {
            it.toWordData()
        }
    }

    fun updateWords(wordData: WordData) {
        dao.updateWord(wordData.toWordEntity())
    }


}