package uz.gita.newdictionaryenglishuzbekgb.presentation.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.gita.newdictionaryenglishuzbekgb.R
import uz.gita.newdictionaryenglishuzbekgb.data.models.WordData
import uz.gita.newdictionaryenglishuzbekgb.databinding.ListItemWordsBinding
import uz.gita.newdictionaryenglishuzbekgb.utils.WordItemCallback
import uz.gita.newdictionaryenglishuzbekgb.utils.inflate
/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
class SavedWordAdapter : RecyclerView.Adapter<SavedWordAdapter.ViewHolder>() {

    private val wordList: ArrayList<WordData> = arrayListOf()
    private var bookmarkListener: ((WordData) -> Unit)? = null
    private var rootListener: ((WordData) -> Unit)? = null

    fun submitList(list: List<WordData>) {
        val callback = WordItemCallback(wordList, list)
        val result = DiffUtil.calculateDiff(callback)
        wordList.clear()
        wordList.addAll(list)
        result.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ListItemWordsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.imgBookmark.setOnClickListener {
                val data = wordList[adapterPosition]
                bookmarkListener?.invoke(data.copy(isFavourite = !data.isFavourite))
            }
            binding.root.setOnClickListener {
                rootListener?.invoke(wordList[adapterPosition])
            }
        }

        fun onBind(wordData: WordData) {
            binding.apply {
                tvWordName.text = wordData.engName
                tvTxt.text=wordData.transcript
                tvTxt2.text="(${wordData.uzbName})"
                imgBookmark.setImageResource(R.drawable.ic_bookmark_fill)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ListItemWordsBinding.bind(parent.inflate(R.layout.list_item_words)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.onBind(wordList[position])

    override fun getItemCount(): Int = wordList.size

    fun setBookMarkListener(block: (WordData) -> Unit) {
        bookmarkListener = block
    }

    fun setRootListener(block: (WordData) -> Unit) {
        rootListener = block
    }

}