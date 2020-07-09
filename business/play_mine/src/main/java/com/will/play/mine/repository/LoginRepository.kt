package com.will.play.mine.repository

import androidx.annotation.VisibleForTesting
import com.will.habit.base.BaseModel
import com.will.habit.http.BaseResponse
import com.will.play.mine.entity.DemoEntity
import com.will.play.mine.repository.source.HttpDataSource
import com.will.play.mine.repository.source.LocalDataSource
import io.reactivex.rxjava3.core.Observable

/**
 * MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repositor）
 */
class LoginRepository private constructor(private val mHttpDataSource: HttpDataSource,
                                          private val mLocalDataSource: LocalDataSource) : BaseModel<Any>(), HttpDataSource, LocalDataSource {
    override fun login(): Observable<Any> {
        return mHttpDataSource.login()
    }

    override fun loadMore(): Observable<DemoEntity> {
        return mHttpDataSource.loadMore()
    }

    override fun demoGet(): Observable<BaseResponse<DemoEntity>> {
        return mHttpDataSource.demoGet()
    }

    override fun demoPost(catalog: String): Observable<BaseResponse<DemoEntity>> {
        return mHttpDataSource.demoPost(catalog)
    }

    override fun saveUserName(userName: String) {
        mLocalDataSource.saveUserName(userName)
    }

    override fun savePassword(password: String) {
        mLocalDataSource.savePassword(password)
    }

    override fun getUserName(): String {
        return mLocalDataSource.userName
    }

    override fun getPassword(): String {
        return mLocalDataSource.password
    }

    companion object {
        @Volatile
        private var INSTANCE: LoginRepository? = null
        @JvmStatic
        fun getInstance(httpDataSource: HttpDataSource,
                        localDataSource: LocalDataSource): LoginRepository? {
            if (INSTANCE == null) {
                synchronized(LoginRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = LoginRepository(httpDataSource, localDataSource)
                    }
                }
            }
            return INSTANCE
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}