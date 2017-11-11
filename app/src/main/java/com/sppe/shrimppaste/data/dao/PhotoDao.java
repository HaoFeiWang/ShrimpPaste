package com.sppe.shrimppaste.data.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WHF on 2017/11/5.
 */

public class PhotoDao {

    private Dao<PhotoEntry,?> dao;

    public PhotoDao(Context context){
        dao = DatabaseHelp.getInstance(context).getDao(PhotoEntry.class);
    }

    public void addPhotoEntryList(List<PhotoEntry> photo) {
        try {
            dao.create(photo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PhotoEntry> queryPhotoEntryList(){
        try {
            return dao.queryBuilder().orderBy("publishedAt", false).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
