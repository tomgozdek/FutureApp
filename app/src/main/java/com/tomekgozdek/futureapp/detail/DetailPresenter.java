package com.tomekgozdek.futureapp.detail;

import android.support.annotation.NonNull;

import com.tomekgozdek.futureapp.presenter.BasicView;
import com.tomekgozdek.futureapp.presenter.Presenter;

/**
 * DetailPresenter managing detail order view.
 *
 * Created by TomekG on 2017-04-02.
 */

public class DetailPresenter implements Presenter{

    private final View mView;
    private int mOrderId;

    public DetailPresenter(int orderId, @NonNull View view) {
        mOrderId = orderId;

        if(view == null){
            throw new NullPointerException("View cannot be null");
        } else {
            mView = view;
        }
    }

    interface View extends BasicView {

    }
}
