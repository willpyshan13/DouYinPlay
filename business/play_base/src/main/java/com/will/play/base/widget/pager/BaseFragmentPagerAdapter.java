package com.will.play.base.widget.pager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Desc:tab viewpager 基类
 * <p>
 * Date: 2019-03-26
 * Copyright: Copyright (c) 2010-2019
 * Updater:
 * Update Time:
 * Update Comments:
 *
 */
public class BaseFragmentPagerAdapter extends FragmentStatePagerAdapter {
    /**
     * ViewPager要填充的fragment列表
     */
    private List<Fragment> list;
    /**
     * tab中的title文字列表
     */
    private List<String> title;

    /**
     * 使用构造方法来将数据传进去
     * @param fm 控制器
     * @param list 集合
     * @param title 标题
     */
    public BaseFragmentPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> title) {
        super(fm);
        this.list = list;
        this.title = title;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {//获得position中的fragment来填充
        return list.get(position);
    }
    @NonNull
    @Override
    public int getCount() {//返回FragmentPager的个数
        return list.size();
    }

    /**
     * FragmentPager的标题,如果重写这个方法就显示不出tab的标题内容
     * @param position 位置
     * @return 标题
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
