package com.sppe.shrimppaste.data;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by WHF on 2017/11/5.
 */

public class PhotoDao {

    private Dao<PhotoEntry,?> dao;

    public PhotoDao(Context context){
        dao = DatabaseHelp.getInstance(context).getDao(PhotoEntry.class);
    }


    public void addPhotoEntryList(PhotoEntry photo) {
        try {
            dao.create(photo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PhotoEntry> queryPhotoEntryList(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
