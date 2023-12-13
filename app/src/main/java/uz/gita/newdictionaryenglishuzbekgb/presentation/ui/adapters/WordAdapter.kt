package uz.gita.newdictionaryenglishuzbekgb.presentation.ui.adapters

import android.database.Cursor
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.newdictionaryenglishuzbekgb.R
import uz.gita.newdictionaryenglishuzbekgb.data.local.room.entity.WordEntity
import uz.gita.newdictionaryenglishuzbekgb.data.models.WordData
import uz.gita.newdictionaryenglishuzbekgb.databinding.ItemWordHomeBinding
import uz.gita.newdictionaryenglishuzbekgb.utils.coloredString
import uz.gita.newdictionaryenglishuzbekgb.utils.inflate
/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
class WordAdapter : RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    private var bookmarkListener: ((WordData) -> Unit)? = null
    private var rootListener: ((WordData) -> Unit)? = null
    private var cursor: Cursor? = null
    var query: String? = null

    fun submitCursor(cursor: Cursor) {
        this.cursor = cursor
        notifyDataSetChanged()
    }

    fun Cursor.getWordData(): WordData {
        return WordData(
            getInt(0),
            getString(1),
            getString(2),
            getString(3),
            getString(4),
            getString(5),
            getInt(6) == 1
        )
    }


    inner class ViewHolder(private val binding: ItemWordHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.imgBookmark.setOnClickListener {
                cursor?.moveToPosition(adapterPosition)
                val data = cursor?.getWordData()!!
                bookmarkListener?.invoke(data.copy(isFavourite = !data.isFavourite))
                notifyItemChanged(adapterPosition)
            }
            binding.root.setOnClickListener {
                cursor?.moveToPosition(adapterPosition)
                val data = cursor?.getWordData()!!
                rootListener?.invoke(data)
            }
        }

        fun onBind(wordData: WordData) {
            binding.apply {
                tvWordName.text = wordData.engName.coloredString(query)
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
        ViewHolder(ItemWordHomeBinding.bind(parent.inflate(R.layout.item_word_home)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor?.moveToPosition(position)
        holder.onBind(cursor!!.getWordData())
    }

    override fun getItemCount(): Int = cursor?.count ?: 0

    fun setBookMarkListener(block: (WordData) -> Unit) {
        bookmarkListener = block
    }



    fun setRootListener(block: (WordData) -> Unit) {
        rootListener = block
    }

}