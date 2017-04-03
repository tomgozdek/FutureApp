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
    private FutureItem mCurrentFutureItem;

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

    @Override
    public void onDestroy() {

    }

    private void loadOrderId() {
        FutureRealmRepository repository = new FutureRealmRepositoryImpl();
        mCurrentFutureItem = repository.getFutureItemByOrderId(mOrderId);

        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM, yyyy");

        if(mCurrentFutureItem != null) {
            mView.loadItemDetails(mCurrentFutureItem, sdf.format(mCurrentFutureItem.getModificationDate()));
            mView.loadImage(mCurrentFutureItem.getImageUrl());
        } else {
            mView.onError("No such order");
        }
    }

    public void moreDetailsSelected() {
        mView.openFutureItemUrl(mCurrentFutureItem.getUrl());
    }

    interface View extends BasicView<DetailPresenter> {
        void loadItemDetails(FutureItem item, String date);
        void loadImage(String url);
        void onError(String msg);
        void openFutureItemUrl(String url);
    }
}
