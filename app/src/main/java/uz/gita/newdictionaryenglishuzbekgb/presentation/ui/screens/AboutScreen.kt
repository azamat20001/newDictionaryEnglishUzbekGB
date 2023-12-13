package uz.gita.newdictionaryenglishuzbekgb.presentation.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import uz.gita.newdictionaryenglishuzbekgb.R

/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
class AboutScreen: Fragment(R.layout.screen_about) {

    private var btnBack : ImageView?=null
    private var anim : LottieAnimationView?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBack=view.findViewById(R.id.btnBack)
        btnBack?.setOnClickListener {
            findNavController().navigateUp()
        }
        anim=view.findViewById(R.id.anim1)

            anim?.playAnimation()

    }
}