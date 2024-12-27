package com.example.database_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database_api.dao.MainListDao
import com.example.database_api.entity.MainListItemEntity


@Database(entities = [MainListItemEntity::class], version = 1, exportSchema = false)
//@TypeConverters(MainListItemConverters::class)
abstract class MainListDatabase : RoomDatabase() {
	abstract fun getMainListDao(): MainListDao
}
const val DATABASE_NAME = "main_list_database"
