package com.will.play.base.widget.tag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc: FlowLayout BindingAdapter
 * <p>
 * Date: 2019-07-22
 * Updater:
 * Update Time: 2019/10/15
 * Update Comments:
 *
 */
class BindingFlowLayoutAdapter<T>(private val list: List<T>?) : BaseTagAdapter<T>(list), BindingCollectionAdapter<T> {

    private var itemBinding: ItemBinding<in T>? = null
    private var inflater: LayoutInflater? = null

    override fun getView(parent: FlowLayout, position: Int, t: T): View {
        requireNotNull(itemBinding) { "itemBinding == null" }
        itemBinding!!.onItemBind(position, list!![position])
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }
        val binding = onCreateBinding(inflater!!, itemBinding!!.layoutRes(), parent)
        onBindBinding(binding, itemBinding!!.variableId(), itemBinding!!.layoutRes(), position, list[position])
        return binding.root
    }

    override fun onCreateBinding(inflater: LayoutInflater, layoutRes: Int, viewGroup: ViewGroup): ViewDataBinding {
        return DataBindingUtil.inflate(inflater, layoutRes, viewGroup, false)
    }

    override fun getItemBinding(): ItemBinding<in T> {
        requireNotNull(itemBinding) { "itemBinding == null" }
        return itemBinding!!
    }

    override fun setItems(items: MutableList<T>?) {
        // do nothing
    }

    override fun getAdapterItem(position: Int): T {
        return list!![position]
    }

    override fun onBindBinding(binding: ViewDataBinding, variableId: Int, layoutRes: Int, position: Int, item: T) {
        val bound = itemBinding!!.bind(binding, item)
        if (bound) {
            binding.executePendingBindings()
        }
    }

    override fun setItemBinding(itemBinding: ItemBinding<in T>) {
        this.itemBinding = itemBinding
    }
}