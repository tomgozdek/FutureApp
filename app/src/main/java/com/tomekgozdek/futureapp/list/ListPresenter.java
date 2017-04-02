package com.tomekgozdek.futureapp.list;

import com.tomekgozdek.futureapp.model.FutureItem;
import com.tomekgozdek.futureapp.presenter.BasicView;
import com.tomekgozdek.futureapp.presenter.Presenter;

import java.util.List;

/**
 * Created by TomekG on 2017-04-03.
 */

public class ListPresenter implements Presenter {

    private final View mView;

    public ListPresenter(View view){
        mView = view;
    }
    @Override
    public void onResume() {
    }

    interface View extends BasicView<ListPresenter>{
        void loadItems(List<FutureItem> list);
    }
}
