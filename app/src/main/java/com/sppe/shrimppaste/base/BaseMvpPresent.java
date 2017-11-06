package com.sppe.shrimppaste.base;

/**
 * Created by WHF on 2017/11/5.
 */

public class BaseMvpPresent<V extends BaseMvpView>{

    private V view;

    public void attachView(V view){
        this.view = view;
    }

    public void detachView(){
        this.view = null;
    }
}
