import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.database_api.dao.MainListDao
import com.example.database_api.entity.MainListItemEntity

import com.example.database_impl.di.MainListItemConverters

@Database(entities = [MainListItemEntity::class], version = 1, exportSchema = false)
@TypeConverters(MainListItemConverters::class)
abstract class MainListDatabase : RoomDatabase() {

	abstract fun getMainListDao(): MainListDao

	companion object {
		@Volatile
		private var INSTANCE: MainListDatabase? = null

		fun getInstance(context: Context): MainListDatabase {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					MainListDatabase::class.java,
					DATABASE_NAME
				).build()
				INSTANCE = instance
				instance
			}
		}
	}
}

const val DATABASE_NAME = "main_list_database"
