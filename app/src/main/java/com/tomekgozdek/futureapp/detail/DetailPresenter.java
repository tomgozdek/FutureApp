package com.tomekgozdek.futureapp.detail;

import android.support.annotation.NonNull;

import com.tomekgozdek.futureapp.model.FutureItem;
import com.tomekgozdek.futureapp.presenter.BasicView;
import com.tomekgozdek.futureapp.presenter.Presenter;
import com.tomekgozdek.futureapp.repository.FutureRealmRepository;
import com.tomekgozdek.futureapp.repository.FutureRealmRepositoryImpl;

import java.text.SimpleDateFormat;

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

    @Override
    public void onResume() {
        loadOrderId();
    }

    private void loadOrderId() {
        FutureRealmRepository repository = new FutureRealmRepositoryImpl();
        FutureItem item = repository.getFutureItemByOrderId(mOrderId);

        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM, yyyy");
        if(item != null) {
            mView.loadItemDetails(item, sdf.format(item.getModificationDate()));
            mView.loadImage(item.getImageUrl());
        } else {
            mView.onError("Wrong order id");
        }
    }

    interface View extends BasicView {
        void loadItemDetails(FutureItem item, String date);
        void loadImage(String url);
        void onError(String msg);
    }
}
