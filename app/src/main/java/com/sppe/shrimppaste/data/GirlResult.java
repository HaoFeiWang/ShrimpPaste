package com.sppe.shrimppaste.data;

import java.util.List;

/**
 * Created by WHF on 2017/11/5.
 */

public class GirlResult {

    private Boolean error;
    private List<PhotoEntry> results;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<PhotoEntry> getResults() {
        return results;
    }

    public void setResults(List<PhotoEntry> results) {
        this.results = results;
    }
}
