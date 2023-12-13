package uz.gita.newdictionaryenglishuzbekgb.presentation.viewmodel

import android.database.Cursor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.newdictionaryenglishuzbekgb.data.local.room.AppDatabase
import uz.gita.newdictionaryenglishuzbekgb.data.models.WordData

/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
class HomeViewModel : ViewModel() {
    private val dao = AppDatabase.getInstance().wordDao()
    val allWords: MutableLiveData<Cursor> = MutableLiveData()


    fun updateWord(wordData: WordData) {
        dao.updateWord(wordData.toWordEntity())
        allWords.postValue(dao.getAllWords())
    }
    fun getAllWord(){
        allWords.postValue(dao.getAllWords())
    }
}