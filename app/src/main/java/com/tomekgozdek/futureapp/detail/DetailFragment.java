package com.tomekgozdek.futureapp.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomekgozdek.futureapp.R;
import com.tomekgozdek.futureapp.presenter.Presenter;

/**
 * Created by TomekG on 2017-04-02.
 */

public class DetailFragment extends Fragment implements DetailPresenter.View{

    private Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.future_item_detail_layout, container, false);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        if(presenter != null) {
            mPresenter = presenter;
        } else {
            throw new NullPointerException("Presenter cannot be null");
        }
    }
}
