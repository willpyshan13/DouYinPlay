package com.will.habit.widget.recycleview.footer

/**
 * Desc:
 */
interface FooterProvider {

    /**
     * Desc: 创建加载中样式
     * <p>
     */
    fun createLoadingFooter(): IFooter = DefaultLoadingFooter()

    /**
     * Desc: 创建无更多样式
     * <p>
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