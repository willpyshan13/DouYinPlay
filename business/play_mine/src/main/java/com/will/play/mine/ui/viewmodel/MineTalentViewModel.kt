package com.will.play.mine.ui.viewmodel

import android.app.Application
import android.graphics.Color
import android.os.Bundle
import android.provider.SyncStateContract
import android.widget.Toast
import androidx.databinding.ObservableField
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.google.gson.Gson
import com.will.habit.base.BaseViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.extection.launch
import com.will.habit.utils.StringUtils
import com.will.habit.utils.Utils
import com.will.play.base.constant.Constants
import com.will.play.mine.R
import com.will.play.mine.entity.JsonBean
import com.will.play.mine.entity.MineTalentDataInfoEntity
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.ui.activity.MineReportActivity
import com.will.play.mine.ui.activity.MineSaleRecordActivity
import com.will.play.mine.utils.JsonParseUtils
import org.json.JSONArray

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
    val headUrl = ObservableField("")
    val name = ObservableField("")

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
            dismissDialog()
        },{
            dismissDialog()
        })
    }


    val onApplyClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
        }

    })

    val onReportClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            startActivity(MineReportActivity::class.java)
        }

    })

    val onLikeClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
        }

    })
}