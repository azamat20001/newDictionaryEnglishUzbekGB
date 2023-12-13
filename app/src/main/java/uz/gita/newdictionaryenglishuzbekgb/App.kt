package uz.gita.newdictionaryenglishuzbekgb

import android.app.Application
import uz.gita.newdictionaryenglishuzbekgb.data.local.room.AppDatabase

/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
    }
}