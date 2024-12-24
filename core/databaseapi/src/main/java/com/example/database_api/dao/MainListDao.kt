package com.example.database_api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database_api.entity.MainListItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MainListDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertItems(items: List<MainListItemEntity>)

	@Query("SELECT * FROM main_list_table")
	fun getAllItems(): Flow<List<MainListItemEntity>>

	@Query("DELETE FROM main_list_table")
	suspend fun clearItems()
}
