package com.will.habit.widget.recycleview

import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.widget.recycleview.FooterStatus.Companion.STATUS_LOADING
import com.will.habit.widget.recycleview.FooterStatus.Companion.STATUS_NO_MORE
import com.will.habit.widget.recycleview.footer.FooterUtils
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * Desc: 支持加载更多样式的Adapter
 * <p>
 */
open class LoadMoreBindingRecyclerViewAdapter<T> : BindingRecyclerViewAdapter<T>() {

    /**
     * 是否可上拉加载
     */
    private var enableLoadMore = true
    /**
     * 是否需要展示无更多数据
     */
    private var enableShowNoMore = true
    /**
     * 加载状态
     */
    private var footerStatus = ObservableInt(FooterStatus.STATUS_NONE)
    /**
     * 无更多数据
     */
    private val noMoreFooter by lazy(LazyThreadSafetyMode.NONE) { FooterUtils.getFooterProvider().createNoMoreFooter() }
    /**
     * 加载中
     */
    private val loadingFooter by lazy(LazyThreadSafetyMode.NONE) { FooterUtils.getFooterProvider().createLoadingFooter() }

    override fun getItemViewType(position: Int): Int {
        if (hasFooter() && position == itemCount - 1) {
            return getFooterLayout()
        }
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    override fun getItemId(position: Int): Long {
        if (hasFooter() && position == itemCount - 1) {
            return RecyclerView.NO_ID
        }
        return super.getItemId(position)
    }

    override fun onCreateViewHolder(binding: ViewDataBinding): RecyclerView.ViewHolder {
        if (noMoreFooter.handle(binding) || loadingFooter.handle(binding)) {
            return BindingViewHolder(binding)
        }
        return super.onCreateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (hasFooter() && position == itemCount - 1) {
            val binding = DataBindingUtil.getBinding<ViewDataBinding>(holder.itemView)
            binding?.executePendingBindings()
            if (binding != null) {
                if (noMoreFooter.handle(binding)) {
                    noMoreFooter.onBindView(binding)
                } else if (loadingFooter.handle(binding)) {
                    loadingFooter.onBindView(binding)
                }
            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    /**
     * Desc: 设置Footer状态
     */
    fun setFooterStatus(@FooterStatus status: Int) {
        if (itemCount == 0 || footerStatus.get() == status) {
            return
        }
        when (status) {
            FooterStatus.STATUS_NONE -> {
                if (hasFooter()) {
                    notifyItemRemoved(itemCount - 1)
                }
                footerStatus.set(status)
                detachedFooter()
            }
            STATUS_NO_MORE -> {
                if (enableShowNoMore && hasFooter()) {
                    // 支持显示无更多数据且当前有footer，notifyItemChanged
                    footerStatus.set(status)
                    notifyItemChanged(itemCount - 1)
                } else if (enableShowNoMore && !hasFooter()) {
                    // 支持显示无更多数据且当前无footer，notifyItemInserted
                    footerStatus.set(status)
                    notifyItemInserted(itemCount - 1)
                } else if (hasFooter()) {
                    // 不支持显示无更多数据且当前有footer，notifyItemRemoved
                    notifyItemRemoved(itemCount - 1)
                    detachedFooter()
                }
                footerStatus.set(status)
            }
            else -> {
                if (hasFooter()) {
                    footerStatus.set(status)
                    notifyItemChanged(itemCount - 1)
                } else {
                    footerStatus.set(status)
                    notifyItemInserted(itemCount - 1)
                }
            }
        }
    }

    /**
     * Desc: footer回收
     */
    private fun detachedFooter() {
        loadingFooter.onDetached()
        if (enableShowNoMore) {
            noMoreFooter.onDetached()
        }
    }

    /**
     * Desc: 获取Footer状态
     */
    fun getFooterStatus(): Int {
        return footerStatus.get()
    }

    /**
     * Desc: 是否可上拉加载
     */
    fun setLoadMoreEnable(loadMoreEnable: Boolean) {
        this.enableLoadMore = loadMoreEnable
    }

    /**
     * Desc: 当前是否可加载更多
     */
    fun canLoadMore(): Boolean {
        return footerStatus.get() == FooterStatus.STATUS_NONE && enableLoadMore
    }

    /**
     * Desc: 是否显示Footer
     */
    fun hasFooter(): Boolean {
        if (footerStatus.get() == STATUS_LOADING) {
            return true
        }
        if (footerStatus.get() == STATUS_NO_MORE && enableShowNoMore) {
            return true
        }
        return false
    }

    private fun getFooterLayout() = when (footerStatus.get()) {
        STATUS_LOADING -> loadingFooter.getLayoutResId()
        STATUS_NO_MORE -> noMoreFooter.getLayoutResId()
        else -> -1
    }

    /**
     * Desc: 获取数据ItemCount
     */
    fun getBaseItemCount() = if (hasFooter()) itemCount - 1 else itemCount

    @CallSuper
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val manager = recyclerView.layoutManager
        if (manager is GridLayoutManager) {
            val defaultSizeLookUp = manager.spanSizeLookup
            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position >= getBaseItemCount()) {
                        manager.spanCount
                    } else {
                        defaultSizeLookUp?.getSpanSize(position) ?: 1
                    }
                }
            }
        }
    }

    @CallSuper
    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        val lp = holder.itemView.layoutParams
        if (lp is StaggeredGridLayoutManager.LayoutParams) {
            val layoutPosition = holder.layoutPosition
            if (layoutPosition >= getBaseItemCount()) {
                lp.isFullSpan = true
            }
        }
    }

    /**
     * Desc: 是否需要展示无更多数据的样式
     */
    fun setEnableShowNoMore(enableShowNoMore: Boolean) {
        this.enableShowNoMore = enableShowNoMore
    }

    private class BindingViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}