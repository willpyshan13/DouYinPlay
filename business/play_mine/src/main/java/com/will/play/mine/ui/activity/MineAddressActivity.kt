package com.will.play.mine.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.google.gson.Gson
import com.will.habit.base.BaseActivity
import com.will.habit.utils.Utils
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityAddressBinding
import com.will.play.mine.entity.JsonBean
import com.will.play.mine.ui.viewmodel.MineAddressViewModel
import com.will.play.mine.utils.JsonParseUtils
import org.json.JSONArray

/**
 * Desc:我的地址页面
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineAddressActivity : BaseActivity<MineActivityAddressBinding, MineAddressViewModel>() {
    private var options1Items: List<JsonBean> = java.util.ArrayList()
    private val options2Items = java.util.ArrayList<java.util.ArrayList<String>>()
    private val options3Items = java.util.ArrayList<java.util.ArrayList<java.util.ArrayList<String>>>()

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_address
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        initJsonData()
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.showPickerView.observe(this, Observer {
            showPickerView()
        })
    }

    private fun showPickerView() { // 弹出选择器
        val pvOptions: OptionsPickerView<Any> = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, options2, options3, v -> //返回的分别是三个级别的选中位置
            val opt1tx = if (options1Items.isNotEmpty()) options1Items[options1].pickerViewText else ""
            val opt2tx = if (options2Items.size > 0
                    && options2Items[options1].isNotEmpty()) options2Items[options1][options2] else ""
            val opt3tx = if (options2Items.size > 0 && options3Items[options1].size > 0 && options3Items[options1][options2].size > 0) options3Items[options1][options2][options3] else ""
            val tx = opt1tx + opt2tx + opt3tx
            Toast.makeText(this, tx, Toast.LENGTH_SHORT).show()
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build<Any>()

        pvOptions.setPicker(options1Items, options2Items as List<MutableList<Any>>?, options3Items as List<MutableList<MutableList<Any>>>?) //三级选择器
        pvOptions.show()
    }

    private fun initJsonData() { //解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         */
        val JsonData: String? = JsonParseUtils.getJson(Utils.getContext(), "province.json") //获取assets目录下的json文件数据
        val jsonBean: ArrayList<JsonBean> = parseData(JsonData) //用Gson 转成实体
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean
        for (i in jsonBean.indices) { //遍历省份
            val cityList = ArrayList<String>() //该省的城市列表（第二级）
            val province_AreaList = ArrayList<ArrayList<String>>() //该省的所有地区列表（第三极）
            for (c in 0 until jsonBean[i].cityList.size) { //遍历该省份的所有城市
                val cityName: String = jsonBean[i].cityList[c].name
                cityList.add(cityName) //添加城市
                val city_AreaList = ArrayList<String>() //该城市的所有地区列表
                city_AreaList.addAll(jsonBean[i].cityList[c].area)
                province_AreaList.add(city_AreaList) //添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            options2Items.add(cityList)
            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList)
        }
    }

    private fun parseData(result: String?): ArrayList<JsonBean> { //Gson 解析
        val detail = ArrayList<JsonBean>()
        try {
            val data = JSONArray(result)
            val gson = Gson()
            for (i in 0 until data.length()) {
                val entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean::class.java)
                detail.add(entity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return detail
    }
}