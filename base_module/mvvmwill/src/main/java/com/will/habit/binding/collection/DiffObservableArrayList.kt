package com.will.habit.binding.collection

import androidx.databinding.ListChangeRegistry
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback

/**
 * Desc: 支持差异更新的ObservableArrayList
 * <p>
 */
class DiffObservableArrayList<T>(private val callback: DiffUtil.ItemCallback<T>, private val detectMoves: Boolean = true) : ArrayList<T>(), ObservableList<T> {

    @Transient
    private var listeners: ListChangeRegistry = ListChangeRegistry()
    private var shouldNotify = true

    override fun removeOnListChangedCallback(callback: ObservableList.OnListChangedCallback<out ObservableList<T>>?) {
        listeners.remove(callback)
    }

    override fun addOnListChangedCallback(callback: ObservableList.OnListChangedCallback<out ObservableList<T>>?) {
        listeners.add(callback)
    }

    /**
     * Desc: 提交数据
     * <p>
     * @param newData 新数据
     * @param append true:addAll；false: clear + addAll
     */
    fun submit(newData: List<T>, append: Boolean) {
        if (append) {
            addAll(newData)
            return
        }
        // 如果第一个item不是同一个数据，直接整个列表刷新，避免第一个Item insert后被顶到屏幕外
        if (isNotEmpty() && newData.isNotEmpty() && !callback.areItemsTheSame(this[0], newData[0])) {
            clear()
            addAll(newData)
            return
        }
        val diffResult = doCalculateDiff(this, newData)
        shouldNotify = false
        clear()
        addAll(newData)
        shouldNotify = true
        diffResult.dispatchUpdatesTo(ObservableListUpdateCallback())
    }

    /**
     * Desc: 对比更新
     * <p>
     * @Deprecated
     */
    @Deprecated("use submit(newData, false) instead", ReplaceWith("submit(newData, false)"))
    fun update(newData: List<T>) {
        submit(newData, false)
    }

    override fun add(element: T): Boolean {
        super.add(element)
        notifyInserted(size - 1, 1)
        return true
    }

    override fun add(index: Int, element: T) {
        super.add(index, element)
        notifyInserted(index, 1)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        val oldSize = size
        val added = super.addAll(elements)
        if (added && shouldNotify) {
            notifyInserted(oldSize, size - oldSize)
        }
        return added
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        val added = super.addAll(index, elements)
        if (added && shouldNotify) {
            notifyInserted(index, elements.size)
        }
        return added
    }

    override fun clear() {
        val oldSize = size
        super.clear()
        if (oldSize != 0 && shouldNotify) {
            notifyRemoved(0, oldSize)
        }
    }

    override fun removeAt(index: Int): T {
        val removeAt = super.removeAt(index)
        notifyRemoved(index, 1)
        return removeAt
    }

    override fun remove(element: T): Boolean {
        val index = indexOf(element)
        return if (index >= 0) {
            removeAt(index)
            true
        } else {
            false
        }
    }

    override fun set(index: Int, element: T): T {
        val set = super.set(index, element)
        listeners.notifyChanged(this, index, 1)
        return set
    }

    override fun removeRange(fromIndex: Int, toIndex: Int) {
        super.removeRange(fromIndex, toIndex)
        if (shouldNotify) {
            notifyRemoved(fromIndex, toIndex - fromIndex)
        }
    }

    private fun doCalculateDiff(oldItems: List<T>, newItems: List<T>?): DiffUtil.DiffResult {
        return DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return oldItems.size
            }

            override fun getNewListSize(): Int {
                return newItems?.size ?: 0
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = oldItems[oldItemPosition]
                val newItem = newItems!![newItemPosition]
                return if (oldItem != null && newItem != null) {
                    callback.areItemsTheSame(oldItem, newItem)
                } else oldItem == null && newItem == null
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = oldItems[oldItemPosition]
                val newItem = newItems!![newItemPosition]
                return if (oldItem != null && newItem != null) {
                    callback.areContentsTheSame(oldItem, newItem)
                } else true
            }

            override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
                // No Support
                return null
            }
        }, detectMoves)
    }

    internal inner class ObservableListUpdateCallback : ListUpdateCallback {

        override fun onChanged(position: Int, count: Int, payload: Any?) {
            notifyChanged(position, count)
        }

        override fun onInserted(position: Int, count: Int) {
            notifyInserted(position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            notifyRemoved(position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            listeners.notifyMoved(this@DiffObservableArrayList, fromPosition, toPosition, 1)
        }
    }

    /**
     * Desc: 数据删除
     * <p>
     */
    private fun notifyRemoved(position: Int, count: Int) {
        listeners.notifyRemoved(this, position, count)
    }

    /**
     * Desc: 数据插入
     * <p>
     */
    private fun notifyInserted(position: Int, count: Int) {
        listeners.notifyInserted(this, position, count)
    }

    /**
     * Desc: 数据有变化
     */
    private fun notifyChanged(position: Int, count: Int) {
        listeners.notifyChanged(this, position, count)
    }
}