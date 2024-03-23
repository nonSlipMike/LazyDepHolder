package com.example.a_feature_impl.reststubs


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class UsersOnWorkResponse(
	@SerializedName("users")
	val users: List<User?>? = null
) : Parcelable {

	fun foo(): Int {
		TODO("not implemented yet")
	}

	@Parcelize
	data class User(
		@SerializedName("address")
		val address: String? = null,
		@SerializedName("age")
		val age: String? = null,
		@SerializedName("currentJob")
		val currentJob: CurrentJob? = null,
		@SerializedName("firstName")
		val firstName: String? = null,
		@SerializedName("gender")
		val gender: String? = null,
		@SerializedName("hairColor")
		val hairColor: String? = null,
		@SerializedName("id")
		val id: String? = null,
		@SerializedName("jobs")
		val jobs: List<Job?>? = null,
		@SerializedName("lastName")
		val lastName: String? = null,
		@SerializedName("phone")
		val phone: String? = null
	) : Parcelable {

		@Parcelize
		data class CurrentJob(
			@SerializedName("salary")
			val salary: String? = null,
			@SerializedName("title")
			val title: String? = null
		) : Parcelable

		@Parcelize
		data class Job(
			@SerializedName("salary")
			val salary: String? = null,
			@SerializedName("title")
			val title: String? = null
		) : Parcelable
	}
}