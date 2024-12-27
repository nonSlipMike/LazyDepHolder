package com.example.database_api.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//@Parcelize
@Entity(tableName = MAIN_LIST_DB_NAME)
data class MainListItemEntity(
	@PrimaryKey(autoGenerate = true) val id: Int,
	@ColumnInfo(name = "name") val name: String?,
	@ColumnInfo(name = "myValue")val value: Double?,
)// : Parcelable

const val MAIN_LIST_DB_NAME = "main_list_table"
