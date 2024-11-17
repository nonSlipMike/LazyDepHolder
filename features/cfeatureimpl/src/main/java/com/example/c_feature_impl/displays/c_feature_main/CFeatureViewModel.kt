package com.example.c_feature_impl.displays.c_feature_main

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.c_feature_impl.displays.c_feature_main.state.GameUiState
import com.example.c_feature_impl.repositories.Screen2Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import okhttp3.internal.wait
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

@OptIn(FlowPreview::class)
class CFeatureViewModel(
	val repository: Screen2Repository
) : ViewModel() {
	// Game UI state
	private val _uiState = MutableStateFlow(GameUiState())
	val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

	fun log(atr: String) {
		Log.d("corutune", atr)

		var x:AtomicBoolean
		//x.set()

		mutableMapOf<String, Int>().apply {
			this["asd"]
			this.forEach {

			}
		}
		mutableSetOf<String>().apply {
			this.add("ad")
		}

		mutableListOf<String>()
		var s = ""
		val s1 = 's'
		s.isHaveChar('s')
		s.plus("as")
		s.length
		s = s1.toString()
		s.contains("asd")
		//for (i in s.indices){}


		fun lengthOfLongestSubstring(s: String): Int {
			if (s.length == 1) return 1

			var buffer = ""//w
			var currentSubstr = "" //wke

			for(i in s.indices){ // 5
				if ( buffer.isHaveChar(s[i]) ){ // содержит w? yes
					if(currentSubstr.length < buffer.length){
						currentSubstr = buffer
					}
					buffer = s[i].toString()
				}else{
					buffer = buffer.plus(s[i])  //добавить e
				}
			}
			if (currentSubstr.isEmpty() or (buffer.length>currentSubstr.length)) return buffer.length
			return currentSubstr.length
		}


		fun String.isHaveChar(char: Char): Boolean {
			forEach {
				if (it == char) return true
			}
			return false
		}
		lengthOfLongestSubstring("dvdf")

	}

	fun String.isHaveChar(char: Char): Boolean {
		forEach {
			if (it == char) return true
		}
		return false
	}

	//val flowList = arrayListOf("A", "B", "C", "D", "E").asFlow()

	init {

		var asd: Int

		for (i in 10..100) {
			asd = 10

		}


		//вернуть список уникальных повторяющихся последоватльностей: [abaadcdababbbacdccdd]
		//пример ответа "a", "b", "aa", "d", "c", "bbb" ...

		val list0 = arrayListOf("abaadcdababbbacdccdd")

		try {
			var word = ""
			val list1 = mutableListOf<String>()
			for (i in 1..100) {
				if (list1.size == 0) {
					list1.add(list0[i])
				}

				//list0 = a
				list1.forEach {
					//list1 = a

					word += list0[i]
					//word = a

					// a == a => false
					if (!it.equals(list0[i])) {
						list1.add(list0[i])
						word = ""
					}


				}

				list1.contains(word)

			}


		} catch (_: Exception) {

		}


		class Store() {
			var products = 0
			var allSize = 0

			@Synchronized
			fun getItem() {
				if ((allSize >= 10) and (products <= 0)) return
				try {
					while (products > 0) {
						products--
					}

					log("item was got")
					log("$products")
				} catch (e: InterruptedException) {
					log("ERROR")
				}
				notify()
			}

			@Synchronized
			fun putItem() {
				if (allSize >= 10) return
				try {
					while (products <= 3) {
						products++
						allSize++
						if (allSize >= 10) {
							log("$products")
							return
						}
					}

					log("item was put")
					log("$products")
				} catch (e: InterruptedException) {
					log("ERROR")
				}
				wait()
			}
		}

		val store = Store()

		thread(start = true, block = {
			(1..10).forEach {
				log("thread1 putter work")
				store.putItem()
			}
		})

		thread(start = true, block = {
			(1..10).forEach {
				log("thread2 getter work")
				store.getItem()
				Thread.sleep(1000)
			}
		})

		val emScope = CoroutineScope(Job())
		emScope.launch { }
		viewModelScope.launch {

			val flow1 = flow {
				repeat(3) {
					emit(it)
				}
			}

			val flow2 = (1..3).asFlow()//.onEach { delay(1000) }

			val flow3 = (1..3).asFlow().onEach { delay(50) }

			flow1.flatMapConcat { value ->
//				flow {
//					emit(value + 2)
//				}
				flow2.onEach { }
			}.collect {
				Log.d("cor1", "collect : $it")
			}


			flow1.transform { number ->
				emit(number)
				Log.d("cor1", "transform 1: $this")
				emit(number)
				Log.d("cor1", "transform 2 : $this")

			}.collectLatest {
				delay(20000)
				Log.d("cor1", "collect : $it")

			}

			flow1.onEach {
				Log.d("cor1", "onEach : $it")
				delay(200)

			}.buffer().collect {
				Log.d("cor1", "collect : $it")
				delay(600)
			}


			flow1.transform { number ->
				emit(number)
				emit(number)
			}.zip(flow2) { flowA, flowb ->// объединит 1 к 1
				//Log.d("corutune1", "zip :obj1 = $flowA , obj2 = $flowb, ${flowA + flowb}")
				flowA + flowb
			}.combine(flow3) { flowAB, flowC ->
				flowAB + flowC
				//Log.d("corutune1", "combine :obj1 = $flowAB , obj2 = $flowC, ${flowAB + flowC}")
			}.map {
				it.toString()
			}.collect {
				Log.d("corutune1", "collect: $it")
				//delay(1000)
			}



//			data class Person(val name: String) {
//				var age: Int = 0
//			}
//
//			class A {
//				lateinit var first: Model
//				var second: Model? = null
//			}
//
//			fun main() {
//				val a = A()
//				a.first.get()
//				a.second?.get()
//			}


			val job = launch(Job()) {
				launch(SupervisorJob(coroutineContext[Job])) { // child coroutine
					repeat(8) {
						log("first coroutine isActive$isActive")
						delay(1000)
					}
//					repeat(8) {
//						log("second coroutine isActive$isActive")
//						delay(1000)
//					}
				}
			}
			dataQuery().collect {
				log(it)
			}
			delay(5000)

			job.cancel()

		}
	}

	private suspend fun dataQuery(): Flow<String> {

		return flow {
			for (i in 0..10) {
				delay(500)
				emit(i.toString())
			}
		}
	}


	fun storeItemToList(item: String) {
		var intItem = 0
		try {
			intItem = item.toInt()
		} catch (e: Exception) {
			_uiState.update { currentState ->
				currentState.copy(
					lastIncrease = "error",
				)
			}
			return
		}
		_uiState.update { currentState ->
			currentState.copy(
				lastIncrease = item,
				sum = currentState.sum + intItem,
				items = currentState.items.putItem(item)
			)
		}
	}
}


@Immutable
data class ItemsCollection(
	//обязательно не изменяемый лист чтобы избежать ошибок рекомпозиции
	val collection: List<String> = arrayListOf()
)

fun ItemsCollection.putItem(item: String): ItemsCollection {
	val newCollection: MutableList<String> = arrayListOf()
	collection.forEach {
		newCollection.add(it)
	}
	newCollection.add(item)
	return ItemsCollection(newCollection)
}