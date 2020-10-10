package com.will.play.pick.ui.viewmodel

import android.Manifest
import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.DiffUtil
import com.will.habit.base.BaseViewModel
import com.will.habit.binding.collection.DiffObservableArrayList
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.constant.ConstantConfig
import com.will.habit.extection.AuthException
import com.will.habit.extection.launch
import com.will.habit.http.DownloadProgress
import com.will.habit.http.RetrofitClient
import com.will.habit.http.VideoDownLoadManager
import com.will.habit.utils.SPUtils
import com.will.habit.utils.StringUtils
import com.will.habit.utils.ToastUtils
import com.will.play.aop.permission.annotation.NeedPermission
import com.will.play.base.utils.PackageUtils
import com.will.play.base.web.WebViewActivity
import com.will.play.base.web.WebViewPath
import com.will.play.pick.R
import com.will.play.pick.BR
import com.will.play.pick.entity.PickGoodDetailRespEntity
import com.will.play.pick.entity.TaskInfo
import com.will.play.pick.repository.PickRepository
import com.will.play.pick.ui.activity.PickStoreDetailActivity
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:商品详情页
 *
 * Date: 2020-07-11 18:03
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: zhuanghongzhan
 */
class PickGoodsDetailViewModel(application: Application, private val goodId: String) : BaseViewModel<PickRepository>(application) {

    val showVideoCollect = ObservableInt(View.INVISIBLE)

    var data: PickGoodDetailRespEntity? = null
    val uiChange = UiChangeObservable()

    /**
     * 用来存放数据的viewModel
     */
    val dataViewModel = PickGoodsDetailDataViewModel(application)

    /**
     * 复制链接用
     */
    var copyUrl: String = ""


    val tagItemBinding = ItemBinding.of<String>(BR.tagText, R.layout.item_pick_good_detail_tag_item_layout)

    val tagItemList = ObservableArrayList<String>()

    val itemList = DiffObservableArrayList(object : DiffUtil.ItemCallback<PickDataItem>() {
        override fun areItemsTheSame(oldItem: PickDataItem, newItem: PickDataItem): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: PickDataItem, newItem: PickDataItem): Boolean {
            return true
        }
    })


    val itemBinding = ItemBinding.of<PickDataItem>(BR.viewModel, R.layout.fragment_pick_item)

    /**
     * 复制链接
     */
    val onCopyClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            uiChange.copyEvent.value=copyUrl
        }
    })

    /**
     * 测试跳转到搜索页面
     */
    val onTestClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {

        }
    })

    /**
     * 跳转商家详情
     */
    val onShopClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            val bundle = Bundle().apply {
                putString(ConstantConfig.STORE_ID,"${data?.taskInfo?.user_id}")
            }
            startActivity(PickStoreDetailActivity::class.java,bundle)
        }
    })


    /**
     * 领取实物
     */
    val onCollectionClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            uiChange.showShareDialog.call()
        }
    })


    /**
     * 领取视频
     */
    val onVideo = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            if (data!=null){
                getDownloadVideo()
            }
        }
    })

    fun getDouyinUserinfo(authCode:String){
        launch({
            model.douyinAuth(authCode)
            getDownloadVideo()
        },{
            if (it is AuthException){
                if(it.message!=null) {
                    ToastUtils.showShort(it.message!!)
                }
                val bundle = Bundle().apply {
                    putString(WebViewPath.URL,"${RetrofitClient.baseTbkUrl}${SPUtils.instance.getString(ConstantConfig.TOKEN)}${RetrofitClient.baseTbkUrlView}")
                }
                startActivity(WebViewActivity::class.java,bundle)
            }
        })
    }

    @NeedPermission(value = [Manifest.permission.WRITE_EXTERNAL_STORAGE])
    private fun getDownloadVideo(){
        launch({
            showDialog()
            val data = model.getTaskDownload(goodId)
            //"http://test.weizhiyx.com/Upload/image/5f4a5b8a18558.mp4"
            VideoDownLoadManager.downloadVideo(data.source_text,downloadProgress,getApplication())
        },{
            dismissDialog()
            if (it is AuthException){
                if(it.message!=null) {
                    ToastUtils.showShort(it.message!!)
                }
                if(it.responseCode.equals("300")) {
                    uiChange.douyinLogin.call()
                }else{
                    val bundle = Bundle().apply {
                        putString(WebViewPath.URL,"${RetrofitClient.baseTbkUrl}${SPUtils.instance.getString(ConstantConfig.TOKEN)}${RetrofitClient.baseTbkUrlView}")
                    }
                    startActivity(WebViewActivity::class.java,bundle)
                }
            }
        })
    }

    private val downloadProgress = object :DownloadProgress(){
        override fun onSuccess(url:String) {
            dismissDialog()
            showVideoCollect.set(View.VISIBLE)
            uiChange.showShareDialog.value = data!!.copy_url
        }

        override fun onFail() {
            dismissDialog()
        }

    }


    /**
     * 开通vip的对话框
     */
    val onVipDialogClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            uiChange.vipDialog.call()
        }
    })

    /**
     * 分享对话框
     */
    val onShareDialogClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            uiChange.vipDialog.call()
        }
    })


    init {
        tagItemList.add("佣金 ")
        tagItemList.add("原价 ")
        tagItemList.add("佣金比 ")
    }

    override fun onCreate() {
        super.onCreate()
        setTitleText(StringUtils.getStringResource(R.string.pick_good_detail_title))
        getGoodDetail()
        getRecommendVideo()
    }

    private fun getRecommendVideo(){
        launch({
            val recommendList = model.getTaskRecommend()
            val dataList = recommendList.taskLists.map { PickDataItem(this,it) }
            itemList.submit(dataList,false)
        })
    }


    /**
     * Desc:获取商品详情
     * <p>
     * Author: zhuanghongzhan
     * Date: 2020-08-22
     */
    private fun getGoodDetail() {
        launch({
            showDialog()
            data = model.getGoodDetail(goodId)
            val detailInfo = data?.taskInfo
            copyUrl = data?.copy_url ?: ""
            createTagItems(detailInfo)
            dataViewModel.setData(detailInfo)
            dismissDialog()
        }, {
            dismissDialog()
        })
    }

    /**
     * Desc:创建标题下面的tag item
     * <p>
     * Author: zhuanghongzhan
     * Date: 2020-08-25
     * @param detailInfo TaskInfo?
     */
    private fun createTagItems(detailInfo: TaskInfo?) {
        detailInfo ?: return
        tagItemList.clear()
        tagItemList.add("佣金${detailInfo.max_commission}")
        tagItemList.add("原价${detailInfo.reserve_price}")
        tagItemList.add("佣金比${detailInfo.max_commission_rate_text}")
    }


    class UiChangeObservable {
        val vipDialog = SingleLiveEvent<Unit>()

        val copyEvent=SingleLiveEvent<String>()

        val showShareDialog = SingleLiveEvent<String>()

        val douyinLogin = SingleLiveEvent<Void>()
    }


}