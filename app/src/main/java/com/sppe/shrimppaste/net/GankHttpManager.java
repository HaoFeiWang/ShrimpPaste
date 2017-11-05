package com.sppe.shrimppaste.net;

import android.content.Context;

import com.sppe.shrimppaste.adapter.GirlAdapter;
import com.sppe.shrimppaste.data.GirlResult;
import com.sppe.shrimppaste.data.PhotoDao;
import com.sppe.shrimppaste.data.PhotoEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WHF on 2017/11/5.
 */

public class GankHttpManager {

    private static final String BASE_GANK_URL = "http://gank.io/api/";

    private Retrofit retrofit;
    private GankService gankService;

    private static GankHttpManager gankHttpManager;

    private GankHttpManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_GANK_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        gankService = retrofit.create(GankService.class);
    }

    public static GankHttpManager getInstance() {
        if (gankHttpManager == null) {
            synchronized (GankHttpManager.class) {
                if (gankHttpManager == null) {
                    gankHttpManager = new GankHttpManager();
                }
            }
        }
        return gankHttpManager;
    }


    public void getImages(int page, final Context context, final GirlAdapter adapter) {

        Observer<PhotoEntry> observer = new Observer<PhotoEntry>() {
            PhotoDao dao = new PhotoDao(context);
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull PhotoEntry photoEntry) {
                dao.addPhotoEntryList(photoEntry);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                getDataFromLocal(adapter,context);
            }
        };

        gankService.getGirl(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<GirlResult, ObservableSource<PhotoEntry>>() {
                    @Override
                    public ObservableSource<PhotoEntry> apply(@NonNull GirlResult girlResult) throws Exception {
                        return Observable.fromIterable(girlResult.getResults());
                    }
                })
                .subscribe(observer);
    }


    public void getDataFromLocal(final GirlAdapter adapter, final Context context) {

        Observer<String> observer = new Observer<String>() {
            List<String> imageUrlList = new ArrayList<>();
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                imageUrlList.add(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                adapter.addImageUrl(imageUrlList);
            }
        };


        Observable.create(new ObservableOnSubscribe<List<PhotoEntry>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<PhotoEntry>> e) throws Exception {
                e.onNext(new PhotoDao(context).queryPhotoEntryList());
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.newThread())
                .flatMap(new Function<List<PhotoEntry>, ObservableSource<PhotoEntry>>() {
                    @Override
                    public ObservableSource<PhotoEntry> apply(@NonNull List<PhotoEntry> photoEntries)
                            throws Exception {
                        return Observable.fromIterable(photoEntries);
                    }
                })
                .map(new Function<PhotoEntry, String>() {
                    @Override
                    public String apply(@NonNull PhotoEntry photoEntry) throws Exception {
                        return photoEntry.getUrl();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
