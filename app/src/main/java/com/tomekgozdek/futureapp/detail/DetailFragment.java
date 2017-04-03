package com.tomekgozdek.futureapp.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tomekgozdek.futureapp.R;
import com.tomekgozdek.futureapp.databinding.FutureItemDetailLayoutBinding;
import com.tomekgozdek.futureapp.model.FutureItem;
import com.tomekgozdek.futureapp.web.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by TomekG on 2017-04-02.
 */

public class DetailFragment extends Fragment implements DetailPresenter.View{

    private DetailPresenter mPresenter;
    private FutureItemDetailLayoutBinding viewBinding;

    public static final String EXTRA_ORDER_ID = "extra_order_id";

    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.description)
    TextView descriptionView;

    @BindView(R.id.error)
    TextView errorView;

    @BindView(R.id.details)
    ViewGroup detailsContainer;

    /** The orderId of item which details will be displayed in this fragment */
    private int mOrderId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater,R.layout.future_item_detail_layout,container,false);
        View view = viewBinding.getRoot();
        ButterKnife.bind(this, view);

        mOrderId = savedInstanceState != null
                        ? savedInstanceState.getInt(EXTRA_ORDER_ID, 0)
                        : getArguments().getInt(EXTRA_ORDER_ID, 0);

        mPresenter = new DetailPresenter(mOrderId, this);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_ORDER_ID, mOrderId);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
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

    @Override
    public void openFutureItemUrl(String url) {
        Intent intent = new Intent(getContext(), WebViewActivity.class);
        intent.putExtra(WebViewActivity.EXTRA_URL, url);
        getContext().startActivity(intent);
    }

    @OnClick(R.id.details)
    public void moreDetailsSelected(){
        mPresenter.moreDetailsSelected();
    }
}
