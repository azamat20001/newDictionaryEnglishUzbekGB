package uz.gita.newdictionaryenglishuzbekgb.presentation.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.newdictionaryenglishuzbekgb.R
import uz.gita.newdictionaryenglishuzbekgb.data.models.WordData
import uz.gita.newdictionaryenglishuzbekgb.databinding.ListItemWordsBinding
import uz.gita.newdictionaryenglishuzbekgb.utils.coloredString
import uz.gita.newdictionaryenglishuzbekgb.utils.inflate
/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var wordList: ArrayList<WordData> = arrayListOf()
    private var bookmarkListener: ((WordData,Int) -> Unit)? = null
    private var rootListener: ((WordData) -> Unit)? = null
    var query: String? = null

    fun submitList(list: List<WordData>) {
        wordList.clear()
        wordList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListItemWordsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.imgBookmark.setOnClickListener {
                val data = wordList[adapterPosition]
                bookmarkListener?.invoke(data.copy(isFavourite = !data.isFavourite),adapterPosition)
            }
            binding.root.setOnClickListener {
                rootListener?.invoke(wordList[adapterPosition])
            }
        }

        fun onBind(wordData: WordData) {
            binding.apply {
                tvWordName.text = wordData.engName.coloredString(query)
                tvTxt.text=wordData.uzbName
                tvTxt2.text="(${wordData.transcript})"
                imgBookmark.setImageResource(
                    if (wordData.isFavourite)
                        R.drawable.ic_bookmark_fill
                    else
                        R.drawable.ic_bookmark_border
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ListItemWordsBinding.bind(parent.inflate(R.layout.list_item_words)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.onBind(wordList[position])

    override fun getItemCount(): Int = wordList.size

    fun setBookMarkListener(block: (WordData,Int) -> Unit) {
        bookmarkListener = block
    }

    fun setRootListener(block: (WordData) -> Unit) {
        rootListener = block
    }

}