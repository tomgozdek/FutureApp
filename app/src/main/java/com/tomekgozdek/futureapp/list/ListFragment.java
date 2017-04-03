package com.tomekgozdek.futureapp.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomekgozdek.futureapp.R;
import com.tomekgozdek.futureapp.model.FutureItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TomekG on 2017-04-03.
 */

public class ListFragment extends Fragment implements ListPresenter.View{

    private ListPresenter mPresenter;

    @BindView(R.id.future_list)
    RecyclerView futureList;
    private FutureItemAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.future_item_list_layout, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new FutureItemAdapter();
        futureList.setLayoutManager(new LinearLayoutManager(getContext()));
        futureList.setAdapter(mAdapter);

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
        mAdapter.setItems(list);
    }
}
