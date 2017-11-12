package com.sppe.shrimppaste.net;

import android.content.Context;

import com.sppe.shrimppaste.adapter.GirlAdapter;
import com.sppe.shrimppaste.data.dao.GankEntry;
import com.sppe.shrimppaste.net.bean.BaseGankResult;
import com.sppe.shrimppaste.data.dao.GankDao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WHF on 2017/11/5.
 */

public class GankNetManager implements GankNetService {

    private static final String BASE_GANK_URL = "http://gank.io/api/";

    private Retrofit retrofit;
    private GankNetService gankService;

    private static GankNetManager gankNetManager;

    private GankNetManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_GANK_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        gankService = retrofit.create(GankNetService.class);
    }

    public static GankNetManager getInstance() {
        if (gankNetManager == null) {
            synchronized (GankNetManager.class) {
                if (gankNetManager == null) {
                    gankNetManager = new GankNetManager();
                }
            }
        }
        return gankNetManager;
    }

    @Override
    public Observable<BaseGankResult> getGirl(int page) {
        return gankService.getGirl(page);
    }

    @Override
    public Observable<BaseGankResult> getAndroid(int page) {
        return gankService.getAndroid(page);
    }

    @Override
    public Observable<BaseGankResult> getIos(int page) {
        return gankService.getIos(page);
    }
}
