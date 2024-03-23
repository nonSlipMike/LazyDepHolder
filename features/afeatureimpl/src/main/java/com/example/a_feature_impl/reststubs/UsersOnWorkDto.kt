package com.example.a_feature_impl.reststubs


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.example.common.lists.transformWith

fun UsersOnWorkResponse?.toDto() =
	UsersOnWorkDto.getDtoFrom(this)

@Immutable
data class UsersOnWorkDto(
	val users: List<User>
) {

	companion object {
		fun getDtoFrom(response: UsersOnWorkResponse?): UsersOnWorkDto {
			return UsersOnWorkDto(
				users = response?.users.transformWith { User.getDtoFrom(it) }
			)
		}
	}

	data class User(
		val address: String,
		val age: String,
		val currentJob: CurrentJob,
		val firstName: String,
		val gender: String,
		val hairColor: String,
		val id: String,
		val jobs: List<Job>,
		val lastName: String,
		val phone: String,
	) {
		companion object {
			fun getDtoFrom(response: UsersOnWorkResponse.User?): User {
				return User(
					id = response?.id ?: "",
					address = response?.address ?: "",
					age = response?.age ?: "",
					currentJob = CurrentJob.getDtoFrom(response?.currentJob),
					firstName = response?.firstName ?: "",
					gender = response?.firstName ?: "",
					hairColor = response?.hairColor ?: "",
					jobs = response?.jobs.transformWith { Job.getDtoFrom(it) },
					lastName = response?.lastName ?: "",
					phone = response?.phone ?: ""

				)
			}
		}

		data class CurrentJob(
			val salary: String,
			val title: String
		) {
			companion object {
				fun getDtoFrom(response: UsersOnWorkResponse.User.CurrentJob?): CurrentJob {
					return CurrentJob(
						salary = response?.salary ?: "",
						title = response?.title ?: ""
					)
				}
			}
		}

		data class Job(
			val salary: String,
			val title: String
		) {
			companion object {
				fun getDtoFrom(response: UsersOnWorkResponse.User.Job?): Job {
					return Job(
						salary = response?.salary ?: "",
						title = response?.title ?: ""
					)
				}
			}
		}
	}
}