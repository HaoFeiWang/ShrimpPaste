package com.sppe.shrimppaste.data.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by WHF on 2017/11/5.
 */

public class GankDao {

    private Dao<GankEntry,?> dao;

    public GankDao(Context context){
        dao = DatabaseHelp.getInstance(context).getDao(GankEntry.class);
    }

    public void addGankList(List<GankEntry> gank) {
        try {
            dao.create(gank);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GankEntry> queryGirlList(){
        return queryGankList(GankType.TYPE_GIRL);
    }

    public GankEntry queryRandowGirl(){
        try {
            List<GankEntry> gankEntryList = dao.queryBuilder().where().eq("type",GankType.TYPE_GIRL).query();
            int size = gankEntryList.size();
            Random random = new Random();
            int position = random.nextInt(size) - 1;
            if (position < 0){
                return null;
            }
            return gankEntryList.get(position);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<GankEntry> queryAndroidList(){
        return queryGankList(GankType.TYPE_ANDOROID);
    }

    public List<GankEntry> queryIosList() {
       return queryGankList(GankType.TYPE_IOS);
    }

    private List<GankEntry> queryGankList(String type) {
        try {
            return dao.queryBuilder().orderBy("publishedAt", false)
                    .where().eq("type",type).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }}
