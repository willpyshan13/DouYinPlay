package com.will.habit.ui.network;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.will.habit.BR;
import com.will.habit.R;
import com.will.habit.app.AppViewModelFactory;
import com.will.habit.databinding.FragmentNetworkBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.will.habit.base.BaseFragment;
import com.will.habit.utils.MaterialDialogUtils;
import com.will.habit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * Created by goldze on 2017/7/17.
 * 网络请求列表界面
 */

public class NetWorkFragment extends BaseFragment<FragmentNetworkBinding, NetWorkViewModel> {
    @Override
    public void initParam() {
        super.initParam();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_network;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public NetWorkViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(NetWorkViewModel.class);
    }

    @Override
    public void initData() {
        //请求网络数据
        viewModel.requestNetWork();
    }

    @Override
    public void initViewObservable() {
        //监听下拉刷新完成
        viewModel.uc.finishRefreshing.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                //结束刷新
                binding.twinklingRefreshLayout.finishRefreshing();
            }
        });
        //监听上拉加载完成
        viewModel.uc.finishLoadmore.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                //结束刷新
                binding.twinklingRefreshLayout.finishLoadmore();
            }
        });
        //监听删除条目
        viewModel.deleteItemLiveData.observe(this, new Observer<NetWorkItemViewModel>() {
            @Override
            public void onChanged(@Nullable final NetWorkItemViewModel netWorkItemViewModel) {
                int index = viewModel.getItemPosition(netWorkItemViewModel);
                //删除选择对话框
                MaterialDialogUtils.showBasicDialog(getContext(), "提示", "是否删除【" + netWorkItemViewModel.entity.get().getName() + "】？ position：" + index)
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                ToastUtils.showShort("取消");
                            }
                        }).onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        viewModel.deleteItem(netWorkItemViewModel);
                    }
                }).show();
            }
        });
    }
}
