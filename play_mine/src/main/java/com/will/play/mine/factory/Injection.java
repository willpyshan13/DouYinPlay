package com.will.play.mine.factory;

import com.will.habit.http.RetrofitClient;
import com.will.play.mine.repository.source.local.LocalDataSourceImpl;
import com.will.play.mine.repository.LoginRepository;
import com.will.play.mine.repository.source.HttpDataSource;
import com.will.play.mine.repository.source.LocalDataSource;
import com.will.play.mine.repository.source.http.HttpDataSourceImpl;
import com.will.play.mine.repository.source.http.service.DemoApiService;


/**
 * 注入全局的数据仓库，可以考虑使用Dagger2。（根据项目实际情况搭建，千万不要为了架构而架构）
 * Created by goldze on 2019/3/26.
 */
public class Injection {
    public static LoginRepository provideDemoRepository() {
        //网络API服务
        DemoApiService apiService = RetrofitClient.getInstance().create(DemoApiService.class);
        //网络数据源
        HttpDataSource httpDataSource = HttpDataSourceImpl.getInstance(apiService);
        //本地数据源
        LocalDataSource localDataSource = LocalDataSourceImpl.getInstance();
        //两条分支组成一个数据仓库
        return LoginRepository.getInstance(httpDataSource, localDataSource);
    }
}
