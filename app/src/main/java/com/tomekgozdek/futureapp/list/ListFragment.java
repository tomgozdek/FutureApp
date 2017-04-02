package com.tomekgozdek.futureapp.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomekgozdek.futureapp.R;
import com.tomekgozdek.futureapp.model.FutureItem;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by TomekG on 2017-04-03.
 */

public class ListFragment extends Fragment implements ListPresenter.View{

    private ListPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.future_item_list_layout, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void setPresenter(ListPresenter presenter) {
        if(presenter != null) {
            mPresenter = presenter;
        } else {
            throw new NullPointerException("Presenter cannot be null");
        }
    }

    @Override
    public void loadItems(List<FutureItem> list) {

    }
}
