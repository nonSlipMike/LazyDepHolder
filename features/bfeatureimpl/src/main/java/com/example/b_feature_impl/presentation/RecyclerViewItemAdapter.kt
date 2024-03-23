package com.example.b_feature_impl.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.b_feature_impl.R

sealed class Item {
	data class Type1(val id: Int, val name: String) : Item()
	data class Type2(val id: Int, val description: String) : Item()
}
class RecyclerViewItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	private val differ: AsyncListDiffer<Item> = AsyncListDiffer(this, ItemDiffCallback())

	fun submitList(newList: List<Item>) {
		differ.submitList(newList)
	}

	override fun getItemViewType(position: Int): Int {
		return when (differ.currentList[position]) {
			is Item.Type1 -> VIEW_TYPE_TYPE1
			is Item.Type2 -> VIEW_TYPE_TYPE2
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return when (viewType) {
			VIEW_TYPE_TYPE1 -> {
				val view = LayoutInflater.from(parent.context).inflate(R.layout.b_feature_fragment, parent, false)
				Type1ViewHolder(view)
			}
			VIEW_TYPE_TYPE2 -> {
				val view = LayoutInflater.from(parent.context).inflate(R.layout.b_feature_fragment, parent, false)
				Type2ViewHolder(view)
			}
			else -> throw IllegalArgumentException("Invalid view type")
		}
	}


	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		val item = differ.currentList[position]

		when (holder) {
			is Type1ViewHolder -> holder.bind(item as Item.Type1)
			is Type2ViewHolder -> holder.bind(item as Item.Type2)
		}
	}


//	override fun onBindViewHolder(holder:  RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
//		for (payload in payloads) {
//			when (payload) {
//	         механиз требует проверки!!
//			is Type1ViewHolder -> holder.bind(item as Item.Type1)
//			is Type2ViewHolder -> holder.bind(item as Item.Type2)
//		}
//		}
//	}

	override fun getItemCount(): Int {
		return differ.currentList.size
	}

	inner class Type1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: Item.Type1) {
			// Привязка данных к представлению элемента списка типа 1
		}
	}

	inner class Type2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: Item.Type2) {
			// Привязка данных к представлению элемента списка типа 2
		}
	}

	class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
		override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
			return oldItem == newItem
		}

		override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
			return oldItem == newItem
		}
	}

	companion object {

		private const val VIEW_TYPE_TYPE1 = 1
		private const val VIEW_TYPE_TYPE2 = 2


	//статик не привязан к адаптору его можно юзать как общий класс

		// Обновление айтема на определенной позиции
		fun <T1 : RecyclerView.ViewHolder, T2> RecyclerView.Adapter<T1>.updateItem(
			list: MutableList<T2>,
			position: Int,
			newItem: T2
		) {
			list[position] = newItem
			notifyItemChanged(position)
		}

		// Обновление диапазона на определенной позиции
		fun <T1 : RecyclerView.ViewHolder, T2> RecyclerView.Adapter<T1>.updateByPayloads(
			position: Int,
			payloads: List<T2>
		) {
			notifyItemChanged(position,payloads)
			//onBindViewHolder(_,_,payloads)
			// вызов переопределенного метода для обновления диапазона
		}

		// Вставка нового айтема на определенную позицию
		fun <T1 : RecyclerView.ViewHolder, T2> RecyclerView.Adapter<T1>.insertItem(
			list: MutableList<T2>,
			position: Int,
			newItem: T2
		) {
			list.add(position, newItem)
			notifyItemInserted(position)
		}


		// Удаление айтема на определенной позиции
		fun <T1 : RecyclerView.ViewHolder, T2> RecyclerView.Adapter<T1>.removeItem(
			list: MutableList<T2>,
			position: Int,
		) {
			list.removeAt(position)
			notifyItemRemoved(position)
		}

		// Обновление диапазона айтемов
		fun <T1 : RecyclerView.ViewHolder, T2> RecyclerView.Adapter<T1>.insertItem(
			startPosition: Int,
			itemCount: Int
		) {
			notifyItemRangeChanged(startPosition, itemCount)
		}
	}
}


/*

а вот так это выглядит в compose

sealed class Item {
	data class Type1(val id: Int, val name: String) : Item()
	data class Type2(val id: Int, val description: String) : Item()
}

@Composable
fun ItemList(items: List<Item>) {
	LazyColumn {
		items(items) { item ->
			when (item) {
				is Item.Type1 -> Type1Item(item)
				is Item.Type2 -> Type2Item(item)
			}
		}
	}
}

@Composable
fun Type1Item(item: Item.Type1) {
	// Визуализация элемента списка типа 1
	Text(text = item.name)
}

@Composable
fun Type2Item(item: Item.Type2) {
	// Визуализация элемента списка типа 2
	Text(text = item.description)
}


val items = listOf(Item.Type1(1, "Item 1"), Item.Type2(2, "Description 2"), Item.Type1(3, "Item 3"))

@Composable
fun MyScreen() {
	ItemList(items = items)

}

*/
