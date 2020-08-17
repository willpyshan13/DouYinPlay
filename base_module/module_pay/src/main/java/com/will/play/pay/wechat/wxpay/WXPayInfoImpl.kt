package com.will.play.pay.wechat.wxpay

import com.will.play.pay.base.IPayInfo

class WXPayInfoImpl : IPayInfo {
    /**
     * sign : ECE311C3DF76E009E6F37F05C350625F
     * timestamp : 1474886901
     * partnerid : 1391669502
     * package : Sign=WXPay
     * appid : wx46a24ab145becbde
     * nonceStr : 0531a4a42fa846fe8a7563847cd24c2a
     * prepayId : wx20160926184820acbd9357100240402425
     */
    var sign: String? = null
    var timestamp: String? = null
    var partnerid: String? = null
    var packageValue: String? = "Sign=WXPay"
    var appid: String? = null
    var nonceStr: String? = null
    var prepayId: String? = null
}