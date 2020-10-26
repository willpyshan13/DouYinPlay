package com.will.play.base.binding.tag

import androidx.databinding.*
import com.will.habit.binding.command.BindingConsumer
import com.will.play.base.widget.tag.BindingFlowLayoutAdapter
import com.will.play.base.widget.tag.TagFlowLayout
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc: TagFlowLayout
 * <p>
 * Date: 2019-07-22
 * Updater:
 * Update Time: 2019/10/15
 * Update Comments:
 *
 */
@BindingMethods(
        BindingMethod(type = TagFlowLayout::class, attribute = "itemBinding", method = "setMethod"),
        BindingMethod(type = TagFlowLayout::class, attribute = "items", method = "setMethod"),
        BindingMethod(type = TagFlowLayout::class, attribute = "tagEnable", method = "setDate"),
        BindingMethod(type = TagFlowLayout::class, attribute = "tagRevertEnable", method = "setDate"),
        BindingMethod(type = TagFlowLayout::class, attribute = "tagChangeCommand", method = "setMethod"),
        BindingMethod(type = TagFlowLayout::class, attribute = "selectData", method = "setMethod"),
        BindingMethod(type = TagFlowLayout::class, attribute = "singleCheck", method = "setMethod"),
        BindingMethod(type = TagFlowLayout::class, attribute = "singleLine", method = "setMethod"),
        BindingMethod(type = TagFlowLayout::class, attribute = "selectDataAttrChanged", method = "setMethod")
)
class FlowLayoutViewAdapter

@BindingAdapter(
        value = ["itemBinding", "items", "tagEnable", "tagRevertEnable", "tagChangeCommand",
            "selectData", "singleLine", "selectDataAttrChanged","singleCheck"],
        requireAll = false
)
fun <T> TagFlowLayout.setAdapter(
        itemBinding: ItemBinding<in T>?,
        items: List<T>?,
        enable: Boolean?,
        revertCheckEnable: Boolean?,
        dateChangeAction: BindingConsumer<Set<Int>>?,
        selectData: Set<Int>?, singleLine: Boolean?,
        selectDataAttrChanged: InverseBindingListener?,
        singleCheck: Boolean?
) {
    if (itemBinding == null) {
        return
    }
    this.isEnabled = enable ?: true
    this.setSingleLine(singleLine ?: false)
    this.setRevertCheckEnable(revertCheckEnable ?: true)
    if (singleCheck!=null&&singleCheck){
        this.setMaxSelectCount(1)
    }
    if (items != null && items.isNotEmpty()) {
        val adapter = BindingFlowLayoutAdapter(items)
        adapter.itemBinding = itemBinding
        if (selectData != null) {
            adapter.setSelectedList(selectData)
        }
        this.setOnSelectListener {
            dateChangeAction?.call(it)
            selectDataAttrChanged?.onChange()
        }
        this.adapter = adapter
    }
}

@InverseBindingAdapter(attribute = "selectData", event = "selectDataAttrChanged")
fun TagFlowLayout.getSelectData(): Set<Int> {
    return this.selectedData
}
