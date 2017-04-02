package com.tomekgozdek.futureapp.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tomekgozdek.futureapp.R;
import com.tomekgozdek.futureapp.databinding.FutureItemDetailLayoutBinding;
import com.tomekgozdek.futureapp.model.FutureItem;
import com.tomekgozdek.futureapp.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TomekG on 2017-04-02.
 */

public class DetailFragment extends Fragment implements DetailPresenter.View{

    private Presenter mPresenter;
    private FutureItemDetailLayoutBinding viewBinding;

    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.description)
    TextView descriptionView;

    @BindView(R.id.error)
    TextView errorView;

    @BindView(R.id.details)
    ViewGroup detailsContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater,R.layout.future_item_detail_layout,container,false);
        View view = viewBinding.getRoot();
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        if(presenter != null) {
            mPresenter = presenter;
        } else {
            throw new NullPointerException("Presenter cannot be null");
        }
    }

    @Override
    public void loadItemDetails(FutureItem item, String date) {
        if(item != null) {
            errorView.setVisibility(View.GONE);
            detailsContainer.setVisibility(View.VISIBLE);
            viewBinding.setFutureItem(item);
            viewBinding.setDate(date);
        }
    }

    @Override
    public void loadImage(String url) {
        Picasso.with(getContext())
                .load(url)
                .into(imageView);
    }

    @Override
    public void onError(String msg) {
        errorView.setVisibility(View.VISIBLE);
        detailsContainer.setVisibility(View.GONE);
        errorView.setText(msg);
    }

}
