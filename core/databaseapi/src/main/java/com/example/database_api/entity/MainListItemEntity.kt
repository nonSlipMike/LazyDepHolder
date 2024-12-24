package com.example.database_api.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = MAIN_LIST_DB_NAME)
data class MainListItemEntity(
	@PrimaryKey val id: Int,
	val name: String,
	val value: Double,
) : Parcelable

const val MAIN_LIST_DB_NAME = "main_list_table"
