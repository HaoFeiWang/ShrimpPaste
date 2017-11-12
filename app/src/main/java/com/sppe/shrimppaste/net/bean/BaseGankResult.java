package com.sppe.shrimppaste.net.bean;


import com.sppe.shrimppaste.data.dao.GankEntry;

import java.util.List;

/**
 * Created by WHF on 2017/11/5.
 */

public class BaseGankResult {

    private Boolean error;
    private List<GankEntry> results;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<GankEntry> getResults() {
        return results;
    }

    public void setResults(List<GankEntry> results) {
        this.results = results;
    }
}
