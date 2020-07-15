package com.will.play.mine.entity

import com.contrarywind.interfaces.IPickerViewData

/**
 * 省市区的实体类
 */
class ProvinceEntity(var id: Long, var name: String, var description: String, var others: String) : IPickerViewData {

    //这个用来显示在PickerView上面的字符串,PickerView会通过getPickerViewText方法获取字符串显示出来。
    override fun getPickerViewText(): String {
        return name
    }

}