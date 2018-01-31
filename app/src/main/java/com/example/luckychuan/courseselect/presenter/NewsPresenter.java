package com.example.luckychuan.courseselect.presenter;

import com.example.luckychuan.courseselect.bean.BaseBean;
import com.example.luckychuan.courseselect.bean.BaseBeanArray;
import com.example.luckychuan.courseselect.bean.News;
import com.example.luckychuan.courseselect.bean.NewsContent;
import com.example.luckychuan.courseselect.model.Callback;
import com.example.luckychuan.courseselect.model.NewModelImpl;
import com.example.luckychuan.courseselect.model.NewsModel;
import com.example.luckychuan.courseselect.view.NewsView;

/**
 * Created by Luckychuan on 2018/1/31.
 */

public class NewsPresenter extends BasePresenter {

    private NewsModel mModel;
    private NewsView mView;

    public NewsPresenter(NewsView newsView){
        mView = newsView;
        mModel = new NewModelImpl();
    }

    public void requestNewsTitle(String userKey,int page){
        mView.showProgressbar();
        mModel.requestNewsTitle(userKey,page, new Callback<BaseBeanArray<News>>() {
            @Override
            public void onNext(BaseBeanArray<News> bean) {
                mView.hideProgressbar();
                if(bean.isSuccess()){
                    mView.onResponse(bean.getDatas());
                }else{
                    mView.onFail(bean.getError());
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(String errorMsg) {
                mView.hideProgressbar();
                mView.onError(errorMsg);
            }
        });
    }

    public void requestNewsContent(String userKey,int id){
        mView.showProgressbar();
        mModel.requestNewsContent(userKey, id, new Callback<BaseBeanArray<NewsContent>>() {
            @Override
            public void onNext(BaseBeanArray<NewsContent>bean) {
                mView.hideProgressbar();
                if(bean.isSuccess()){
                    mView.onResponse(bean.getDatas());
                }else{
                    mView.onFail(bean.getError());
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(String errorMsg) {
                mView.hideProgressbar();
                mView.onError(errorMsg);
            }
        });
    }

}
