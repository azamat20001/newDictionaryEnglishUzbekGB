package uz.gita.newdictionaryenglishuzbekgb.presentation.ui.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.gita.newdictionaryenglishuzbekgb.R
import uz.gita.newdictionaryenglishuzbekgb.databinding.FragmentSearchBinding
import uz.gita.newdictionaryenglishuzbekgb.presentation.ui.adapters.SearchAdapter
import uz.gita.newdictionaryenglishuzbekgb.presentation.viewmodel.SearchViewModel


/**
Mobile Developer
Creator:Musatafoyev Azamat
 */
class SearchScreen : Fragment() {


    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private val adapter by lazy { SearchAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        viewModel.getAll()

        binding.listWords.adapter = adapter
        binding.listWords.layoutManager = LinearLayoutManager(requireContext())




        viewModel.allWords.observe(viewLifecycleOwner) {
            if (it != null)
                adapter.submitList(it)
        }

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        adapter.setBookMarkListener { word, pos ->
            viewModel.updateWord(word, pos)
        }
        adapter.setRootListener {
            val bundle = Bundle()
            bundle.putSerializable("word", it)
            preventTwoClick(requireView())
            findNavController().navigate(R.id.action_searchFragment_to_wordMeaningFragment, bundle)
        }



        binding.apply {

            searchBarWords.doOnTextChanged { text, _, _, _ ->
                adapter.query = text!!.toString()
                viewModel.getWordsByQuery(text.toString())
            }
        }

        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            val spokenText: String? =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { results ->
                    results?.get(0)
                }
            // Do something with spokenText
            binding.searchBarWords.setText(spokenText)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun preventTwoClick(view: View) {
        view.isEnabled = false
        view.postDelayed({ view.isEnabled = true }, 300)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchBarWords.isFocusable = true;
        binding.searchBarWords.isFocusableInTouchMode = true;
        binding.searchBarWords.isEnabled=true

        context?.let { showSoftKeyboard(it, binding.searchBarWords) };
    }

    private fun showSoftKeyboard(context: Context, editText: EditText) {
        try {
            editText.requestFocus()
            editText.postDelayed(
                {
                    val keyboard =
                        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    keyboard.showSoftInput(editText, 0)
                }, 200)
        } catch (npe: NullPointerException) {
            npe.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}