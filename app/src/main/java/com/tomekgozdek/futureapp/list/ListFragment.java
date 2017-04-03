package com.tomekgozdek.futureapp.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.tomekgozdek.futureapp.R;
import com.tomekgozdek.futureapp.model.FutureItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TomekG on 2017-04-03.
 */

public class ListFragment extends Fragment implements ListPresenter.View, View.OnClickListener{

    private ListPresenter mPresenter;

    @BindView(R.id.future_list)
    RecyclerView futureList;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    private FutureItemAdapter mAdapter;
    private OnItemSelectedListener mCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.future_item_list_layout, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new FutureItemAdapter(this);
        futureList.setLayoutManager(new LinearLayoutManager(getContext()));
        futureList.setAdapter(mAdapter);

        progressBar.setIndeterminate(true);

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

    @Override
    public void showProgress() {
        Log.d("TOMEK", "showProgress: ");
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        Log.d("TOMEK", "hideProgress: ");
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        mCallback.onItemSelected((Integer) view.getTag());
    }

    public interface OnItemSelectedListener {
        void onItemSelected(int orderId);
    }
}
