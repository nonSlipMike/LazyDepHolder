package com.example.common.lists

inline fun <K, V> List<K>?.transformWith(atr: (K) -> V): List<V> {
	if (isNullOrEmpty()) return emptyList()
	val resultList = mutableListOf<V>()
	forEach { resultList.add(atr(it)) }
	return resultList
}


open class Message(val atr: Int = 0)
open class AlarmMessage : Message()
class WarningAlarmMessage : AlarmMessage()

inline fun <reified T> getMyObject(): T =
	T::class.java.getConstructor(Int.javaClass).newInstance(1)

