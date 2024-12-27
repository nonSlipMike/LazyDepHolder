package com.example.database_impl

import android.content.Context
import androidx.room.Room
import com.example.database_api.dao.MainListDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule.Declarations::class])
object DatabaseModule {

	@Singleton
	@Provides
	fun provideDatabase(context: Context): MainListDatabase {
		return Room.databaseBuilder(
			context,
			MainListDatabase::class.java,
			DATABASE_NAME
		).build()
	}

	@Provides
	fun provideMainListDao(database: MainListDatabase): MainListDao {
		return database.getMainListDao()
	}


	@Module
	internal abstract class Declarations {
//		@Singleton
//		@Binds
//		abstract fun provideDatabaseProvider(retrofit: DatabaseProviderImpl): DatabaseProvider
	}

}