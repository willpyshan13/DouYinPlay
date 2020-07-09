package com.will.habit.widget.recycleview.footer

/**
 * Desc:
 */
interface FooterProvider {

    /**
     * Desc: 创建加载中样式
     * <p>
     * author: linjiaqiang
     * Date: 2019/12/4
     */
    fun createLoadingFooter(): IFooter = DefaultLoadingFooter()

    /**
     * Desc: 创建无更多样式
     * <p>
     * author: linjiaqiang
     * Date: 2019/12/4
     */
    fun createNoMoreFooter(): IFooter = DefaultNoMoreFooter()
}

object FooterUtils {

    private var footerProvider: FooterProvider? = null

    fun createFooterProvider(footerProvider: FooterProvider) {
        FooterUtils.footerProvider = footerProvider
    }

    fun getFooterProvider(): FooterProvider {
        return footerProvider ?: object : FooterProvider {}
    }

}