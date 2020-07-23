package com.will.play.base.widget.tag;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.will.play.base.R;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Desc:流式布局
 * <p>
 * Date: 2019-12-19
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
public class TagFlowLayout extends FlowLayout
        implements BaseTagAdapter.OnDataChangedListener {

    private static final String TAG = "TagFlowLayout";
    private static final String KEY_CHOOSE_POS = "key_choose_pos";
    private static final String KEY_DEFAULT = "key_default";
    private BaseTagAdapter mTagAdapter;
    /**
     * -1为不限制数量
     */
    private int mSelectedMax = -1;
    private boolean mEnable;
    /**
     * 是否支持反选
     */
    private boolean revertCheckEnable = true;
    private Set<Integer> mSelectedView = new HashSet<>();
    private OnSelectListener mOnSelectListener;
    private OnTagClickListener mOnTagClickListener;

    public TagFlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TagFlowLayout);
        mSelectedMax = ta.getInt(R.styleable.TagFlowLayout_max_select, -1);
        ta.recycle();
    }

    public TagFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagFlowLayout(Context context) {
        this(context, null);
    }

    /**
     * Desc:dp 转px
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param context 上下文
     * @param dpValue dp数值
     * @return int
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            TagView tagView = (TagView) getChildAt(i);
            if (tagView.getVisibility() == View.GONE) {
                continue;
            }
            if (tagView.getTagView().getVisibility() == View.GONE) {
                tagView.setVisibility(View.GONE);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * Desc:选中的监听
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param onSelectListener 监听器
     */
    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    /**
     * Desc:选中事件
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param onTagClickListener 选中事件回调
     */
    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        mOnTagClickListener = onTagClickListener;
    }

    /**
     * Desc:通过id获取位置
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param id
     * @return int
     */
    public int getPositionById(int id) {
        return id;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        mEnable = enabled;
    }

    public void setRevertCheckEnable(boolean revertCheckEnable) {
        this.revertCheckEnable = revertCheckEnable;
    }

    /**
     * Desc:设置选中的item，然后再清除数据，设置选中的item用的是position，我们要的是id，所以要设置再清除
     * <p>
     * Author: [pengyushan]
     * Date: 2019-06-14
     *
     * @param list
     */
    public void setSelected(Set<Integer> list) {


        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            mSelectedView.add(getPositionById(it.next()));
        }
        mTagAdapter.setSelectedList(mSelectedView);
        mTagAdapter.notifyDataChanged();

        mSelectedView.clear();
        while (it.hasNext()) {
            mSelectedView.add(it.next());
        }
    }

    @SuppressWarnings("ResourceType")
    private void changeAdapter() {
        removeAllViews();
        BaseTagAdapter adapter = mTagAdapter;
        TagView tagViewContainer = null;
        HashSet preCheckedList = mTagAdapter.getPreCheckedList();
        for (int i = 0; i < adapter.getCount(); i++) {
            View tagView = adapter.getView(this, i, adapter.getItem(i));

            tagViewContainer = new TagView(getContext());
            tagView.setDuplicateParentStateEnabled(true);
            if (tagView.getLayoutParams() != null) {
                tagViewContainer.setLayoutParams(tagView.getLayoutParams());


            } else {
                MarginLayoutParams lp = new MarginLayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);
                lp.setMargins(dip2px(getContext(), 5),
                        dip2px(getContext(), 5),
                        dip2px(getContext(), 5),
                        dip2px(getContext(), 5));
                tagViewContainer.setLayoutParams(lp);
            }
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            tagView.setLayoutParams(lp);
            tagViewContainer.addView(tagView);
            addView(tagViewContainer);

            if (preCheckedList.contains(i)) {
                setChildChecked(i, tagViewContainer);
            }

            if (mTagAdapter.setSelected(i, adapter.getItem(i))) {
                setChildChecked(i, tagViewContainer);
            }
            if (mEnable) {
                final TagView finalTagViewContainer = tagViewContainer;
                final int position = i;
                tagView.setClickable(false);
                tagViewContainer.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doSelect(finalTagViewContainer, position);
                        if (mOnTagClickListener != null) {
                            mOnTagClickListener.onTagClick(finalTagViewContainer, position,
                                    TagFlowLayout.this);
                        }
                    }
                });
            }
        }
        mSelectedView.addAll(preCheckedList);
    }

    /**
     * Desc:最大选择个数
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param count
     */
    public void setMaxSelectCount(int count) {
        if (mSelectedView.size() > count) {
            Log.w(TAG, "you has already select more than " + count + " views , so it will be clear .");
            mSelectedView.clear();
        }
        mSelectedMax = count;
    }

    /**
     * Desc:获取选中列表
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @return set
     */
    public Set<Integer> getSelectedList() {
        return new HashSet<>(mSelectedView);
    }

    /**
     * Desc:设置子item 选中
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param position 位置
     * @param view     布局
     */
    private void setChildChecked(int position, TagView view) {
        view.setChecked(true);
        mTagAdapter.onSelected(position, view.getTagView());
    }

    /**
     * Desc:设置子item 不选中
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param position 位置
     * @param view     布局
     */
    private void setChildUnChecked(int position, TagView view) {
        view.setChecked(false);
        mTagAdapter.unSelected(position, view.getTagView());
    }

    /**
     * Desc:获取当前id
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param position 位置
     * @return int
     */
    public int getId(int position) {
        return position;
    }

    /**
     * Desc:执行选中的方法
     * <p>
     * Author: pengyushan
     * Date: 2019-12-19
     *
     * @param child    视图
     * @param position 位置
     */
    private void doSelect(TagView child, int position) {
        if (!child.isChecked()) {
            //处理max_select=1的情况
            if (mSelectedMax == 1 && mSelectedView.size() == 1) {
                Iterator<Integer> iterator = mSelectedView.iterator();
                Integer preIndex = iterator.next();
                TagView pre = (TagView) getChildAt(preIndex);
                setChildUnChecked(preIndex, pre);
                setChildChecked(position, child);

                mSelectedView.remove(getId(preIndex));
                mSelectedView.add(getId(position));
            } else {
                if (mSelectedMax > 0 && mSelectedView.size() >= mSelectedMax) {
                    return;
                }
                setChildChecked(position, child);
                mSelectedView.add(getId(position));
            }
        } else {
            if (revertCheckEnable) {
                setChildUnChecked(position, child);
                mSelectedView.remove(getId(position));
            }

        }
        if (mOnSelectListener != null) {
            mOnSelectListener.onSelected(new HashSet<Integer>(mSelectedView));
        }
    }

    public Set<Integer> getSelectedData() {
        return mSelectedView;
    }

    public BaseTagAdapter getAdapter() {
        return mTagAdapter;
    }

    public void setAdapter(BaseTagAdapter adapter) {
        mTagAdapter = adapter;
        mTagAdapter.setOnDataChangedListener(this);
        mSelectedView.clear();
        changeAdapter();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DEFAULT, super.onSaveInstanceState());

        String selectPos = "";
        if (!mSelectedView.isEmpty()) {
            for (int key : mSelectedView) {
                selectPos += key + "|";
            }
            selectPos = selectPos.substring(0, selectPos.length() - 1);
        }
        bundle.putString(KEY_CHOOSE_POS, selectPos);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            String mSelectPos = bundle.getString(KEY_CHOOSE_POS);
            if (!TextUtils.isEmpty(mSelectPos)) {
                String[] split = mSelectPos.split("\\|");
                for (String pos : split) {
                    int index = Integer.parseInt(pos);
                    mSelectedView.add(index);

                    TagView tagView = (TagView) getChildAt(index);
                    if (tagView != null) {
                        setChildChecked(index, tagView);
                    }
                }

            }
            super.onRestoreInstanceState(bundle.getParcelable(KEY_DEFAULT));
            return;
        }
        super.onRestoreInstanceState(state);
    }


    @Override
    public void onChanged() {
        mSelectedView.clear();
        changeAdapter();
    }


    public interface OnSelectListener {
        /**
         * Desc:执行选中的方法
         * <p>
         * Author: pengyushan
         * Date: 2019-12-19
         *
         * @param selectPosSet 选中的数据
         */
        void onSelected(Set<Integer> selectPosSet);
    }

    public interface OnTagClickListener {
        /**
         * Desc:执行选中的方法
         * <p>
         * Author: pengyushan
         * Date: 2019-12-19
         *
         * @param view     选中的数据
         * @param parent   父布局
         * @param position 位置
         */
        void onTagClick(View view, int position, FlowLayout parent);
    }
}
