package com.sppe.shrimppaste.service;

import com.sppe.shrimppaste.data.dao.PhotoEntry;
import com.sppe.shrimppaste.net.bean.GirlResult;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by WHF on 2017/11/6.
 */

public interface GankService {

    Observable<List<PhotoEntry>> getGirl(int page);

}
