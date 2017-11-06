package com.sppe.shrimppaste.net;

import com.sppe.shrimppaste.net.bean.GirlResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by WHF on 2017/11/5.
 */

public interface GankNetService {

    @GET("data/福利/10/{page}")
    Observable<GirlResult> getGirl(@Path("page")int page);

    @GET("/data/Android/10/{page}")
    List<GirlResult> getAndroid(@Path("page")int page);

    @GET("/data/iOS/10/{page}")
    List<GirlResult> getIos(@Path("page")int page);

}
