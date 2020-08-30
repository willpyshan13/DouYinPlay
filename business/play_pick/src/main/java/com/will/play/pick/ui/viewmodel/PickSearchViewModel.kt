package com.will.play.pick.ui.viewmodel

import android.app.Application
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.BaseViewModel
import com.will.habit.binding.collection.DiffObservableArrayList
import com.will.habit.extection.launch
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.pick.R
import com.will.play.pick.BR
import com.will.play.pick.repository.PickRepository
import com.will.play.pick.repository.PickSearchRepository
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:搜索页面viewModel
 *
 * Date: 2020-07-12
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @author: zhuanghongzhan
 */
class PickSearchViewModel(context: Application,val typeId:String) : BaseListViewModel<PickRepository,PickSearchItemViewModel>(context) {

    override fun getDiffItemCallback(): DiffUtil.ItemCallback<PickSearchItemViewModel> {
        return object :DiffUtil.ItemCallback<PickSearchItemViewModel>(){
            override fun areItemsTheSame(oldItem: PickSearchItemViewModel, newItem: PickSearchItemViewModel): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: PickSearchItemViewModel, newItem: PickSearchItemViewModel): Boolean {
                return false
            }

        }
    }

    init {
        loadInit()
    }

    override fun showEmptyState() {
    }

    override fun getItemBinding(): ItemBinding<PickSearchItemViewModel> {
        return ItemBinding.of(BR.viewModel, R.layout.item_pick_search_layout)
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun loadData(pageIndex: Int, loadCallback: LoadCallback<PickSearchItemViewModel>) {
        launch({
            showDialog()
            val data = model.getTaskIndex(pageIndex,typeId)
            val dataList = data.taskLists.map { PickSearchItemViewModel(this,it) }
            loadCallback.onSuccess(dataList,pageIndex,data.counter)
            dismissDialog()
        },{
            dismissDialog()
        })
    }

}
