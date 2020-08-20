package com.will.play.mine.entity

import android.os.Parcel
import android.os.Parcelable

data class MineVipDetailEntity(
    val is_first_pay: Boolean,
    val is_visit_pay: Boolean,
    val lists: List<Any>,
    val role_id: Any,
    val upgradeLists: List<UpgradeLists>
)

data class UpgradeLists(
    val can_share: String?,
    val content: String?,
    val content_text: String?,
    val disable: Int,
    val disable_name: String?,
    val disable_text: String?,
    val from_role_id: String?,
    val id: Int,
    val incre_time: String?,
    val name: String?,
    val price: String?,
    val price_default: String?,
    val price_first: String?,
    val price_share: String?,
    val price_visit: String?,
    val sort_index: String?,
    val system_role_name: String?,
    val tag: String?,
    val tb_id: String?,
    val time_create: Int,
    val time_create_text: String?,
    val time_edit: Int,
    val time_login: Int,
    val time_login_text: String?,
    val to_role_id: String?,
    val user_id: Int,
    val price_person: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(can_share)
        parcel.writeString(content)
        parcel.writeString(content_text)
        parcel.writeInt(disable)
        parcel.writeString(disable_name)
        parcel.writeString(disable_text)
        parcel.writeString(from_role_id)
        parcel.writeInt(id)
        parcel.writeString(incre_time)
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(price_default)
        parcel.writeString(price_first)
        parcel.writeString(price_share)
        parcel.writeString(price_visit)
        parcel.writeString(sort_index)
        parcel.writeString(system_role_name)
        parcel.writeString(tag)
        parcel.writeString(tb_id)
        parcel.writeInt(time_create)
        parcel.writeString(time_create_text)
        parcel.writeInt(time_edit)
        parcel.writeInt(time_login)
        parcel.writeString(time_login_text)
        parcel.writeString(to_role_id)
        parcel.writeInt(user_id)
        parcel.writeString(price_person)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UpgradeLists> {
        override fun createFromParcel(parcel: Parcel): UpgradeLists {
            return UpgradeLists(parcel)
        }

        override fun newArray(size: Int): Array<UpgradeLists?> {
            return arrayOfNulls(size)
        }
    }
}