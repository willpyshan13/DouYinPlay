package com.will.play.mine.repository.source;

import com.will.habit.http.BaseResponse;
import com.will.play.mine.entity.DemoEntity;

import io.reactivex.rxjava3.core.Observable;

/**
 * Created by goldze on 2019/3/26.
 */
public interface HttpDataSource {
    //模拟登录
    Observable<Object> login();

    //模拟上拉加载
    Observable<DemoEntity> loadMore();

    Observable<BaseResponse<DemoEntity>> demoGet();

    Observable<BaseResponse<DemoEntity>> demoPost(String catalog);


}
