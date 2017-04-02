package com.tomekgozdek.futureapp.detail;

import android.support.annotation.NonNull;

import com.tomekgozdek.futureapp.model.FutureItem;
import com.tomekgozdek.futureapp.presenter.BasicView;
import com.tomekgozdek.futureapp.presenter.Presenter;
import com.tomekgozdek.futureapp.repository.FutureRealmRepository;
import com.tomekgozdek.futureapp.repository.FutureRealmRepositoryImpl;

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
        mView.loadItemDetails(repository.getFutureItemByOrderId(mOrderId));
    }

    interface View extends BasicView {
        void loadItemDetails(FutureItem item);
    }
}
