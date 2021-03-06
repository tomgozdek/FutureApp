package com.tomekgozdek.futureapp.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

public class ListFragment extends Fragment implements ListPresenter.View, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private ListPresenter mPresenter;

    @BindView(R.id.future_list)
    RecyclerView futureList;

    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private FutureItemAdapter mAdapter;
    private OnItemSelectedListener mCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.future_item_list_layout, container, false);
        ButterKnife.bind(this, view);

        mPresenter = new ListPresenter(this);
        mAdapter = new FutureItemAdapter(this);
        futureList.setLayoutManager(new LinearLayoutManager(getContext()));
        futureList.setAdapter(mAdapter);

        swipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (OnItemSelectedListener) getActivity();
    }

    @Override
    public void loadItems(List<FutureItem> list) {
        mAdapter.setItems(list);
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(View view) {
        mCallback.onItemSelected((Integer) view.getTag());
    }

    @Override
    public void onRefresh() {
        mPresenter.syncData();
    }

    public interface OnItemSelectedListener {
        void onItemSelected(int orderId);
    }
}
