package com.sppe.shrimppaste.present;

import android.content.Context;
import android.util.Log;

import com.sppe.shrimppaste.base.BaseMvpPresent;
import com.sppe.shrimppaste.data.contacts.Contacts;
import com.sppe.shrimppaste.data.dao.PhotoEntry;
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

    private static final String TAG = Contacts.LOG_TAG + GirlPresent.class.getSimpleName();

    private Context context;
    private GankServiceImpl serviceImpl;

    public GirlPresent(Context context) {
        this.context = context;
        serviceImpl = new GankServiceImpl(context);
    }

    public void refreshDataFromDb() {
        Log.i(TAG, "refresh data from db!");
        serviceImpl
                .getDbGirlList()
                .map(getMapper())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<String> urlList) {
                        view.refreshDataSuccess(urlList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "refresh data from db error = " + e.toString());
                        view.refreshDataError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void refreshDataFromNet(int page) {
        Log.i(TAG, "refresh data from net!");
        serviceImpl
                .getNetGirl(page)
                .map(getMapper())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull List<String> urlList) {
                        view.refreshDataSuccess(urlList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "refresh data from net error = " + e.toString());
                        view.refreshDataError();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @android.support.annotation.NonNull
    private Function<List<PhotoEntry>, List<String>> getMapper() {
        return new Function<List<PhotoEntry>, List<String>>() {
            @Override
            public List<String> apply(@NonNull List<PhotoEntry> photoEntries) throws Exception {
                List<String> list = new ArrayList<>();
                for (PhotoEntry photo : photoEntries) {
                    list.add(photo.getUrl());
                }
                return list;
            }
        };
    }
}
