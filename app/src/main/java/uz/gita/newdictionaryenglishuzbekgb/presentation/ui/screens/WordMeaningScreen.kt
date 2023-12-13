package uz.gita.newdictionaryenglishuzbekgb.presentation.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.gita.newdictionaryenglishuzbekgb.R
import uz.gita.newdictionaryenglishuzbekgb.data.models.WordData
import uz.gita.newdictionaryenglishuzbekgb.databinding.FragmentWordMeaningBinding
import uz.gita.newdictionaryenglishuzbekgb.presentation.viewmodel.WordMeaningViewModel
import java.util.*

/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
class WordMeaningScreen : Fragment() {


    private lateinit var viewModel: WordMeaningViewModel
    private lateinit var binding: FragmentWordMeaningBinding
    private var isFavourite: Boolean = false
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var copyClipboard: ClipboardManager
    private lateinit var clipData: ClipData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWordMeaningBinding.inflate(inflater, container, false)

        val words = arguments?.getSerializable("word") as WordData

        viewModel = ViewModelProvider(this)[WordMeaningViewModel::class.java]

        isFavourite = words.isFavourite

        copyClipboard =
            requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        binding.apply {

            imgBack.setOnClickListener {
                findNavController().navigateUp()
            }

            tvWordName.text = words.engName

            tvMeaningName.text = words.uzbName

            tvWordType.text = words.type

            tvTranscript.text = words.transcript


            imgBookmark.setImageResource(
                if (isFavourite)
                    R.drawable.ic_bookmark_fill
                else
                    R.drawable.ic_bookmark
            )

            imgCopy.setOnClickListener {
                Toast.makeText(requireContext(), "Copied", Toast.LENGTH_SHORT).show()
                clipData = ClipData.newPlainText(
                    "${tvWordName.text}\n${tvMeaningName.text}\nlink: https://play.google.com/store/apps/details?id=uz.gita.logicwordio",
                    tvWordName.text
                )
                copyClipboard.setPrimaryClip(clipData)
            }

            textToSpeech = TextToSpeech(requireContext()) {
                if (it != TextToSpeech.ERROR) {
                    textToSpeech.language = Locale.UK
                }
            }

            imgVolume.setOnClickListener {
                textToSpeech.speak(
                    "${tvWordName.text}",
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    "Book"
                )
            }
            imgBookmark.setOnClickListener {
                isFavourite = !isFavourite
                imgBookmark.setImageResource(
                    if (isFavourite)
                        R.drawable.ic_bookmark_fill
                    else
                        R.drawable.ic_bookmark
                )
                viewModel.update(words.copy(isFavourite = isFavourite))
            }
            imgShare.setOnClickListener {
                share()
            }
        }

        return binding.root
    }

    private fun share() {
        val intent = Intent(Intent.ACTION_SEND)
        binding.apply {
            val shareBody =
                "*******************\n${tvWordName.text}\n${tvMeaningName.text}\n*******************\nlink: https://play.google.com/store/apps/details?id=uz.gita.a2048gameforplaymarket"
            intent.type = "text/plain"
            intent.putExtra(
                Intent.EXTRA_SUBJECT,
                binding.tvWordName.text
            )
            intent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(intent, "Dictionary app"))
        }
    }
}