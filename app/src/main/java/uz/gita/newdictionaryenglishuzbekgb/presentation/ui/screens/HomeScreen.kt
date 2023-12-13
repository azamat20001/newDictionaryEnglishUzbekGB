package uz.gita.newdictionaryenglishuzbekgb.presentation.ui.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.gita.newdictionaryenglishuzbekgb.BuildConfig
import uz.gita.newdictionaryenglishuzbekgb.R
import uz.gita.newdictionaryenglishuzbekgb.databinding.FragmentHomeBinding
import uz.gita.newdictionaryenglishuzbekgb.presentation.viewmodel.HomeViewModel
import uz.gita.newdictionaryenglishuzbekgb.presentation.ui.adapters.WordAdapter

/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
class HomeScreen : Fragment() {


    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewModel: HomeViewModel

    private val adapter by lazy { WordAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.listAllWords.adapter = adapter



        adapter.setBookMarkListener {
            viewModel.updateWord(it)
        }

        adapter.setRootListener {
            val bundle = Bundle()
            bundle.putSerializable("word", it)
            preventTwoClick(requireView())
            findNavController().navigate(R.id.action_homeFragment_to_wordMeaningFragment, bundle)
        }



        binding.imgMenu.setOnClickListener {
            //showPopup(it)
        }

        binding.tvSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.imgMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.dictionary -> {
//                    Toast.makeText(requireContext(), "Home", Toast.LENGTH_SHORT).show()
                      // binding.drawerLayout.closeDrawer(GravityCompat.START)
                    findNavController().navigate(HomeScreenDirections.actionHomeFragmentToSearchFragment())
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.about -> {
//                    Toast.makeText(requireContext(), "About", Toast.LENGTH_SHORT).show()
//                    binding.drawerLayout.closeDrawer(GravityCompat.START)
            findNavController().navigate(HomeScreenDirections.actionHomeFragmentToAboutScreen())
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.favourites -> {
//                    Toast.makeText(requireContext(), "Favourites", Toast.LENGTH_SHORT).show()
//                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    findNavController().navigate(HomeScreenDirections.actionHomeFragmentToSavedWordFragment())
                }
                R.id.share -> {
//                    Toast.makeText(requireContext(), "Share", Toast.LENGTH_SHORT).show()
//                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    try {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                        var shareMessage = "\nLet me recommend you this application\n\n"
                        shareMessage =
                            """
                            ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                            
                            
                            """.trimIndent()
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                        startActivity(Intent.createChooser(shareIntent, "choose one"))
                    } catch (e: Exception) {
                        //e.toString();
                    }
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.exit_app -> {
//                    Toast.makeText(requireContext(), "Exit", Toast.LENGTH_SHORT).show()
//                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    requireActivity().finish()
                }
            }
            return@setNavigationItemSelectedListener true
        }

        viewModel.getAllWord()
        viewModel.allWords.observe(viewLifecycleOwner) {
            adapter.submitCursor(it)
        }

    }

    private fun showInfoDialog() {
      //  val dialog = InfoAppDialog(requireContext())
       // dialog.show()
    }
    private  fun preventTwoClick(view: View) {
        view.isEnabled = false
        view.postDelayed({ view.isEnabled = true }, 300)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 102 && resultCode == Activity.RESULT_OK) {
            val result: ArrayList<String>? =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            binding.listAllWords.adapter = null

            val str = result?.get(0).toString()
            if (!str.isEmpty()) {
               // searchView.setQuery(str, false)
              //  binding.tvSearch.
                //   viewModel.search(str)!!
            }
            binding.listAllWords.adapter = adapter
        }
    }
}