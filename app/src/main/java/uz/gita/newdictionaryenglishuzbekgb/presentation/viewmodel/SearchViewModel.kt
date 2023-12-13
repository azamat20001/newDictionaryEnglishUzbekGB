package uz.gita.newdictionaryenglishuzbekgb.presentation.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.newdictionaryenglishuzbekgb.data.local.room.AppDatabase
import uz.gita.newdictionaryenglishuzbekgb.data.models.WordData

/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
class SearchViewModel : ViewModel() {

    private val dao = AppDatabase.getInstance().wordDao()
    private var _allWords: MutableLiveData<List<WordData>> = MutableLiveData(emptyList())
    val allWords: LiveData<List<WordData>> get() = _allWords

    fun getAll() {
        _allWords.value = dao.getAllWord().map { list ->
            list.map {
                it.toWordData()
            }
        }.value
    }

    fun updateWord(wordData: WordData, pos: Int) {
        dao.updateWord(wordData.toWordEntity())
        val list = ArrayList<WordData>()
        list.addAll(_allWords.value!!)
        list[pos] = wordData
        _allWords.postValue(list)

    }

    fun getWordsByQuery(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _allWords.postValue(dao.getWordsByQuery(query).map {
                it.toWordData()
            })
        }
    }
}