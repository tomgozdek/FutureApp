package com.tomekgozdek.futureapp.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tomekgozdek.futureapp.R;

import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ORDER_ID = "extra_order_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        if(ButterKnife.findById(this, R.id.detail_frag_container) != null){
            DetailFragment fragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag_container);

            if(fragment == null){
                fragment = new DetailFragment();
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.detail_frag_container, fragment)
                    .commit();

            int orderId = getIntent().getIntExtra(EXTRA_ORDER_ID, -1);
            DetailPresenter presenter = new DetailPresenter(orderId, fragment);
            fragment.setPresenter(presenter);
        }
    }
}
