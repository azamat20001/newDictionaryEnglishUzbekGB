package uz.gita.newdictionaryenglishuzbekgb.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.gita.newdictionaryenglishuzbekgb.R
import uz.gita.newdictionaryenglishuzbekgb.databinding.FragmentSavedWordBinding
import uz.gita.newdictionaryenglishuzbekgb.presentation.ui.adapters.SavedWordAdapter
import uz.gita.newdictionaryenglishuzbekgb.presentation.viewmodel.SavedWordViewModel


/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
class SavedWordScreen : Fragment() {

    private lateinit var binding: FragmentSavedWordBinding
    private lateinit var viewModel: SavedWordViewModel
    private val adapter: SavedWordAdapter by lazy { SavedWordAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSavedWordBinding.inflate(inflater, container, false)

        binding.listFavouritesWords.adapter = adapter

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
        viewModel = ViewModelProvider(this)[SavedWordViewModel::class.java]





        adapter.setRootListener {
            val bundle = Bundle()
            bundle.putSerializable("word", it)
            preventTwoClick(requireView())
            findNavController().navigate(
                R.id.action_savedWordFragment_to_wordMeaningFragment,
                bundle
            )
        }
        adapter.setBookMarkListener {
            viewModel.updateWords(it)
        }

        return binding.root
    }
    private  fun preventTwoClick(view: View) {
        view.isEnabled = false
        view.postDelayed({ view.isEnabled = true }, 300)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allFavouriteWords.observe(viewLifecycleOwner) {
            if(it.isEmpty()){
                binding.imgPlace.visibility = View.VISIBLE
            }
            else{
                binding.imgPlace.visibility = View.INVISIBLE
            }
            adapter.submitList(it)
        }
    }

}