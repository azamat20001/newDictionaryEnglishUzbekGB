package uz.gita.newdictionaryenglishuzbekgb.presentation.viewmodel

import androidx.lifecycle.ViewModel
import uz.gita.newdictionaryenglishuzbekgb.data.local.room.AppDatabase
import uz.gita.newdictionaryenglishuzbekgb.data.models.WordData


/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
class WordMeaningViewModel : ViewModel() {

    private val dao = AppDatabase.getInstance().wordDao()

    fun update(wordData: WordData) {
        dao.updateWord(wordData.toWordEntity())
    }

}