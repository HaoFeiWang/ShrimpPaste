package com.sppe.shrimppaste.service;

import com.sppe.shrimppaste.data.dao.GankEntry;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by WHF on 2017/11/6.
 */

public interface GankService {

    Observable<List<GankEntry>> getNetGirlList(int page);

    Observable<List<GankEntry>> getDbGirlList();

    Observable<GankEntry> getRandowDbGirl();

    Observable<List<GankEntry>> getNetAndoridList(int page);

    Observable<List<GankEntry>> getDbAndoridList();

    Observable<List<GankEntry>> getNetIosList(int page);

    Observable<List<GankEntry>> getDbIosList();
}
