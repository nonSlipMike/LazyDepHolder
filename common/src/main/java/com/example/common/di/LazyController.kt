package com.example.common.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

/**
 * этот клас инициализирует объект и отдавает его лениво
 * при этом ЕСЛИ объект никем не используется (нет сильных ссылок )
 * ТО он будет доступен для сбора GC через 1 секунду
 * СОЗДАНИЕ этого объекта будет выполнено ЗАНОВО
 *
 * Важно!! если хотите использовать объект без пересоздания
 * когда на него перестают ссылаться другие то используйте
 * класс LazyControllerSingleton
 */
open class LazyController<T> {
	private var lazyObject: WeakReference<Lazy<T>>? = null
	protected lateinit var setLazyInstanceFunction: () -> T
	protected var strongRefInstance: Lazy<T>? = null

	fun setLazyInstance(setLazyInstanceFunction: () -> T) {
		this.setLazyInstanceFunction = setLazyInstanceFunction
	}

	open fun getLazyInstance(): T {
		if (lazyObject == null || lazyObject?.get() == null) {
			strongRefInstance =
				lazy { setLazyInstanceFunction() } // Инициализация strongRef
			lazyObject = WeakReference(strongRefInstance)
			CoroutineScope(Job()).launch {
				delay(1000) // Задержка на 1 секунду
				//кейс маловероятный но возможно удаление при очень быcтрой работе gc
				//чтобы gc не собрал его сразу делаем сильную ссылку и очищаем ее через секунду
				strongRefInstance = null // Удаление strongRef
			}
		}
		return lazyObject!!.get()!!.value
			?: throw IllegalArgumentException(
				"instance not yet initialized ( need to use method .setLazyInstance() first )"
			)
	}
}