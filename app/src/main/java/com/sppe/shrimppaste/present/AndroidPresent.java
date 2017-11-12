package com.sppe.shrimppaste.present;

import android.content.Context;
import android.util.Log;

import com.sppe.shrimppaste.base.BaseMvpPresent;
import com.sppe.shrimppaste.data.dao.GankEntry;
import com.sppe.shrimppaste.service.GankServiceImpl;
import com.sppe.shrimppaste.ui.view.AndroidView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by WHF on 2017/11/12.
 */

public class AndroidPresent extends BaseMvpPresent<AndroidView> {

    private static final String TAG = GirlPresent.class.getSimpleName();

    private GankServiceImpl serviceImpl;

    public AndroidPresent(Context context) {
        serviceImpl = new GankServiceImpl(context);
    }

    public void refreshDataFromDb() {

        Observer<List<GankEntry>> observer = new Observer<List<GankEntry>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<GankEntry> gankEntryList) {
                view.refreshData(gankEntryList);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, e.toString());
            }

            @Override
            public void onComplete() {

            }
        };

        serviceImpl.getDbAndoridList()
                .observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void refreshDataFromNet(int page) {

        Observer<List<GankEntry>> observer = new Observer<List<GankEntry>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull List<GankEntry> gankEntryList) {
                view.refreshData(gankEntryList);
                view.stopRefreshing();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.stopRefreshing();
                Log.e("====",e.toString());
            }

            @Override
            public void onComplete() {
            }
        };

        serviceImpl.getNetAndoridList(page)
                .observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}
