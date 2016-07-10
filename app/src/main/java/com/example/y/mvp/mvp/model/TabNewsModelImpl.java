package com.example.y.mvp.mvp.model;

import com.example.y.mvp.NewsTabNameInfo;
import com.example.y.mvp.mvp.Bean.BaseBean;
import com.example.y.mvp.network.MySubscriber;
import com.example.y.mvp.network.NetWorkRequest;
import com.example.y.mvp.utils.LogUtils;
import com.example.y.mvp.utils.db.NewsTabNameDbUtils;

import java.util.List;


/**
 * by 12406 on 2016/5/14.
 */
public class TabNewsModelImpl implements BaseModel.TabNewsModel {

    private final List<NewsTabNameInfo> newsTabNameInfo = NewsTabNameDbUtils.getNewsTabName();


    @Override
    public void netWork(final BaseDataBridge.TabNewsData tabNewsData) {

        if (newsTabNameInfo.isEmpty()) {
            NetWorkRequest.tabNews(new MySubscriber<BaseBean.TabNewsBean>() {
                @Override
                public void onError(Throwable e) {
                    tabNewsData.error();
                    LogUtils.i(e.getMessage());
                }

                @SuppressWarnings("unchecked")
                @Override
                public void onNext(BaseBean.TabNewsBean tabNewsBean) {
                    tabNewsData.addData(tabNewsBean.getInfo());
                }
            });
        } else {
            tabNewsData.addData(newsTabNameInfo);
        }

    }
}
