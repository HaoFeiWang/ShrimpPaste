package com.sppe.shrimppaste.present;

import android.content.Context;
import android.util.Log;

import com.sppe.shrimppaste.base.BaseMvpPresent;
import com.sppe.shrimppaste.data.dao.GankEntry;
import com.sppe.shrimppaste.service.GankServiceImpl;
import com.sppe.shrimppaste.ui.view.GirlView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by WHF on 2017/11/5.
 */

public class GirlPresent extends BaseMvpPresent<GirlView> {

    private static final String TAG = GirlPresent.class.getSimpleName();

    private GankServiceImpl serviceImpl;

    public GirlPresent(Context context) {
        serviceImpl = new GankServiceImpl(context);
    }

    public void refreshDataFromDb() {

        Observer<List<String>> observer = new Observer<List<String>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<String> urlList) {
                view.refreshData(urlList);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, e.toString());
            }

            @Override
            public void onComplete() {

            }
        };

        serviceImpl.getDbGirlList().map(new Function<List<GankEntry>, List<String>>() {
            @Override
            public List<String> apply(@NonNull List<GankEntry> photoEntries) throws Exception {
                List<String> list = new ArrayList<>();
                for (GankEntry photo : photoEntries) {
                    list.add(photo.getUrl());
                }
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void refreshDataFromNet(int page) {

        Observer<List<String>> observer = new Observer<List<String>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull List<String> urlList) {
                view.refreshData(urlList);
                view.stopRefreshing();
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };

        serviceImpl.getNetGirlList(page).map(new Function<List<GankEntry>, List<String>>() {
            @Override
            public List<String> apply(@NonNull List<GankEntry> photoEntries) throws Exception {
                List<String> list = new ArrayList<>();
                for (GankEntry photo : photoEntries) {
                    list.add(photo.getUrl());
                }
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}
