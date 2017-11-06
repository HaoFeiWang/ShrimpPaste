package com.sppe.shrimppaste.service;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.sppe.shrimppaste.data.dao.PhotoDao;
import com.sppe.shrimppaste.data.dao.PhotoEntry;
import com.sppe.shrimppaste.net.GankNetManager;
import com.sppe.shrimppaste.net.bean.GirlResult;

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

    private PhotoDao photoDao;

    public GankServiceImpl(Context context) {
        photoDao = new PhotoDao(context);
    }

    @Override
    public Observable<List<PhotoEntry>> getNetGirl(int page) {
        return GankNetManager.getInstance().getGirl(page)
                .map(new Function<GirlResult, List<PhotoEntry>>() {
                    @Override
                    public List<PhotoEntry> apply(@NonNull GirlResult girlResult) throws Exception {
                        List<PhotoEntry> photoEntryList = girlResult.getResults();
                        Log.i(TAG,photoEntryList.toString());
                        photoDao.addPhotoEntryList(photoEntryList);
                        return photoEntryList;
                    }
                }).subscribeOn(Schedulers.newThread());
    }

    public Observable<List<PhotoEntry>> getDbGirl() {
        return Observable.just(true).map(new Function<Boolean, List<PhotoEntry>>() {
            @Override
            public List<PhotoEntry> apply(@NonNull Boolean b) throws Exception {
                return photoDao.queryPhotoEntryList();
            }
        }).subscribeOn(Schedulers.newThread());
    }
}
