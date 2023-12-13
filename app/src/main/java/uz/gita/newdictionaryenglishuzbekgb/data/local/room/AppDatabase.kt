package uz.gita.newdictionaryenglishuzbekgb.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.newdictionaryenglishuzbekgb.data.local.room.dao.WordDao
import uz.gita.newdictionaryenglishuzbekgb.data.local.room.entity.WordEntity

/**
Mobile Developer
Creator:Mustafoyev Azamat
 */
@Database(entities = [WordEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        private lateinit var instance: AppDatabase
        fun init(ctx: Context) {
            if (!Companion::instance.isInitialized) {
                instance = Room.databaseBuilder(ctx, AppDatabase::class.java, "dictionary.db")
                    .allowMainThreadQueries()
                    .createFromAsset("dictionary.db")
                    .build()
            }
        }

        fun getInstance() = instance
    }

}