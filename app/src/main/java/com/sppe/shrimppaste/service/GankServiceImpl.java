package com.sppe.shrimppaste.service;

import android.content.Context;

import com.sppe.shrimppaste.data.dao.GankEntry;
import com.sppe.shrimppaste.data.dao.GankDao;
import com.sppe.shrimppaste.net.GankNetManager;
import com.sppe.shrimppaste.net.bean.BaseGankResult;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WHF on 2017/11/6.
 */

public class GankServiceImpl implements GankService {

    private static final String TAG = GankServiceImpl.class.getSimpleName();

    private GankDao gankDao;

    public GankServiceImpl(Context context) {
        gankDao = new GankDao(context);
    }

    @Override
    public Observable<List<GankEntry>> getNetGirlList(final int page) {
        return GankNetManager.getInstance().getGirl(page)
                .map(new Function<BaseGankResult, List<GankEntry>>() {
                    @Override
                    public List<GankEntry> apply(@NonNull BaseGankResult baseGankResult) throws Exception {
                        List<GankEntry> gankEntryList = baseGankResult.getResults();
                        gankDao.addGankList(gankEntryList);
                        return gankDao.queryGirlList();
                    }
                }).subscribeOn(Schedulers.newThread());
    }

    @Override
    public Observable<List<GankEntry>> getDbGirlList() {
        return Observable.just(true).map(new Function<Boolean, List<GankEntry>>() {
            @Override
            public List<GankEntry> apply(@NonNull Boolean b) throws Exception {
                return gankDao.queryGirlList();
            }
        }).subscribeOn(Schedulers.newThread());
    }

    @Override
    public Observable<GankEntry> getRandowDbGirl() {
        return Observable.just(true).map(new Function<Boolean, GankEntry>() {
            @Override
            public GankEntry apply(@NonNull Boolean b) throws Exception {
                return gankDao.queryRandowGirl();
            }
        }).subscribeOn(Schedulers.newThread());
    }

    @Override
    public Observable<List<GankEntry>> getNetAndoridList(int page) {
        return GankNetManager.getInstance().getAndroid(page)
                .map(new Function<BaseGankResult, List<GankEntry>>() {
                    @Override
                    public List<GankEntry> apply(@NonNull BaseGankResult baseGankResult) throws Exception {
                        List<GankEntry> gankEntryList = baseGankResult.getResults();


                        gankDao.addGankList(gankEntryList);
                        return gankDao.queryAndroidList();
                    }
                }).subscribeOn(Schedulers.newThread());
    }

    @Override
    public Observable<List<GankEntry>> getDbAndoridList() {
        return Observable.just(true).map(new Function<Boolean, List<GankEntry>>() {
            @Override
            public List<GankEntry> apply(@NonNull Boolean b) throws Exception {
                return gankDao.queryAndroidList();
            }
        }).subscribeOn(Schedulers.newThread());
    }

    @Override
    public Observable<List<GankEntry>> getNetIosList(int page) {
        return GankNetManager.getInstance().getIos(page)
                .map(new Function<BaseGankResult, List<GankEntry>>() {
                    @Override
                    public List<GankEntry> apply(@NonNull BaseGankResult baseGankResult) throws Exception {
                        List<GankEntry> gankEntryList = baseGankResult.getResults();
                        gankDao.addGankList(gankEntryList);
                        return gankDao.queryIosList();
                    }
                }).subscribeOn(Schedulers.newThread());
    }

    @Override
    public Observable<List<GankEntry>> getDbIosList() {
        return Observable.just(true).map(new Function<Boolean, List<GankEntry>>() {
            @Override
            public List<GankEntry> apply(@NonNull Boolean b) throws Exception {
                return gankDao.queryIosList();
            }
        }).subscribeOn(Schedulers.newThread());
    }
}
