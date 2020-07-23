package com.will.play.base.widget.tag;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Desc:标签适配器
 * <p>
 * Date: 2019-12-19
 * Updater:
 * Update Time:
 * Update Comments:
 * * @param <T> the type parameter
 *
 * @Author: pengyushan
 */
public abstract class BaseTagAdapter<T> {
    private List<T> mTagDatas;
    private boolean mEnable = true;
    private OnDataChangedListener mOnDataChangedListener;
    private HashSet<Integer> mCheckedPosList = new HashSet<Integer>();

    public BaseTagAdapter(List<T> datas) {
        mTagDatas = datas;
    }

    @Deprecated
    public BaseTagAdapter(T[] datas) {
        mTagDatas = new ArrayList<T>(Arrays.asList(datas));
    }

    /**
     * Desc:设置监听器
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param listener       监听器
     */
    void setOnDataChangedListener(OnDataChangedListener listener) {
        mOnDataChangedListener = listener;
    }

    /**
     * Desc:设置选中的数据
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param poses             位置数据
     */
    @Deprecated
    public void setSelectedList(int... poses) {
        Set<Integer> set = new HashSet<>();
        for (int pos : poses) {
            set.add(pos);
        }
        setSelectedList(set);
    }

    /**
     * Desc:设置选中的数据
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param set            位置数据
     */
    public void setSelectedList(Set<Integer> set) {
        mCheckedPosList.clear();
        if (set != null) {
            mCheckedPosList.addAll(set);
        }
        notifyDataChanged();
    }

    /**
     * Desc: 获取之前选中的列表
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @return hash set
     */
    HashSet<Integer> getPreCheckedList() {
        return mCheckedPosList;
    }

    /**
     * Desc:获取子item数量
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @return int
     */
    public int getCount() {
        return mTagDatas == null ? 0 : mTagDatas.size();
    }

    /**
     * Desc:通知更新
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     */
    void notifyDataChanged() {
        if (mOnDataChangedListener != null) {
            mOnDataChangedListener.onChanged();
        }
    }

    /**
     * Desc:获取当前item
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param position     位置
     * @return t
     */
    T getItem(int position) {
        return mTagDatas.get(position);
    }

    /**
     * Desc:获取当前位置的view
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param parent         父布局
     * @param position       位置
     * @param t              泛型
     * @return view
     */
    public abstract View getView(FlowLayout parent, int position, T t);

    /**
     * Desc:选中item
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param position        位置
     * @param view            布局
     */
    void onSelected(int position, View view) {
    }

    /**
     * Desc:反选中
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param position       位置
     * @param view           布局
     */
    void unSelected(int position, View view) {
    }

    /**
     * Desc:设置选中的位置
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param position         位置
     * @param t                泛型
     * @return boolean
     */
    boolean setSelected(int position, T t) {
        return false;
    }

    /**
     * Desc:设置是否可用
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param flag          标志位
     */
    public void setEnable(boolean flag) {
        mEnable = flag;
        for (int i = 0; i < getCount(); i++) {
            if (getItem(i) instanceof TextView) {
                ((TextView) getItem(i)).setEnabled(mEnable);
            }
        }
    }

    /**
     * Desc:是否可用
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @return boolean
     */
    protected boolean ismEnable() {
        return mEnable;
    }

    interface OnDataChangedListener {

        /**
         * Desc: 监听状态改变
         * <p>
         * Author: [pengyushan]
         * Date: 2019-12-19
         *
         */
        void onChanged();
    }
}
