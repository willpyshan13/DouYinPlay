package com.will.play.mine.ui.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PhoneUtils
import com.will.habit.base.BaseViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.extection.PermissionException
import com.will.habit.extection.launch
import com.will.habit.utils.ClipboardUtils
import com.will.habit.utils.ToastUtils
import com.will.play.aop.permission.annotation.NeedPermission
import com.will.play.base.constant.Constants
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.entity.MineTalentDataInfoEntity
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.ui.activity.MineReportActivity
import com.will.play.mine.ui.activity.MineSaleRecordActivity
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:我的地址页面
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineTalentViewModel(application: Application,val talentId:String) : BaseViewModel<MineRepository>(application) {
    val watcherNum = ObservableField("")
    val name = ObservableField("")
    val collect = ObservableBoolean(false)
    val showConfirmMerchant = SingleLiveEvent<String>()

    val showCall = ObservableInt(View.VISIBLE)

    val applyStatus = ObservableField("申请带货")
    /**
     * 顶部数据
     */
    val itemBinding = ItemBinding.of<MineTalentVisitListItem>(BR.viewModel, R.layout.mine_activity_talent_info_visit_item)
    val items = ObservableArrayList<MineTalentVisitListItem>()


    val dataInfo = ObservableField<MineTalentDataInfoEntity>()
    val onSaleRecordClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            startActivity(MineSaleRecordActivity::class.java,Bundle().apply { putString(Constants.COMMON_ID,talentId) })
        }
    })

    override fun onCreate() {
        super.onCreate()
        getDetailInfo()
    }

    /**
     *
     * Desc:获取详细信息
     * <p>
     * Author: pengyushan
     * Date: 2020-10-25
     */
    private fun getDetailInfo(){
        launch({
            showDialog()
            val data = model.getDetailInfo(talentId)
            dataInfo.set(data.dataInfo)
            watcherNum.set("${data.visit_count}")
            //1 表示关注
            collect.set(data.dataInfo.daren_fav_status == 1)
            showCall.set(if (data.dataInfo.daren_view_status>0) View.VISIBLE else View.GONE)
//            applyStatus.set(data.dataInfo.daren_apply_status_name)
            if (data.visitLists.isNotEmpty()){
                val visitList = data.visitLists.map { MineTalentVisitListItem(this,it) }
                items.addAll(visitList)
                items.add(MineTalentVisitListItem(this,""))
            }
            dismissDialog()
        },{
            dismissDialog()
        })
    }

    val onApplyClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            updateMerchant()
        }
    })

    val onCallClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            callPhone()
        }
    })

    val onFinishClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            finish()
        }
    })

    @SuppressLint("MissingPermission")
    @NeedPermission(value = [Manifest.permission.CALL_PHONE])
    private fun callPhone(){
        PhoneUtils.call(dataInfo.get()?.mobile)
    }

    val onWechatCopyClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            ClipboardUtils.copyText(dataInfo.get()?.douyin_no_text)
            ToastUtils.showLong("微信已复制到剪贴板")
        }
    })

    val onDouyinCopyClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            ClipboardUtils.copyText(dataInfo.get()?.wechat_no_text)
            ToastUtils.showLong("抖音号已复制到剪贴板")
        }
    })

    val onReportClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            startActivity(MineReportActivity::class.java)
        }
    })

    /**
     *
     * Desc:升级商家
     * <p>
     * Author: pengyushan
     * Date: 2020-10-25
     */
    fun updateMerchant(){
        launch({
            model.authDarenApply(talentId)
            applyStatus.set("等待同意")
        },{
            if(it is PermissionException){
                showConfirmMerchant.call()
            }
        })
    }


    val onLikeClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            collect()
        }
    })

    private fun collect(){
        launch({
            if (collect.get()){
                collect.set(false)
                model.unCollectUser(talentId)
            }else{
                collect.set(true)
                model.collectUser(talentId)
            }
        })
    }
}