package com.tomekgozdek.futureapp.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tomekgozdek.futureapp.R;

import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        if(ButterKnife.findById(this, R.id.detail_frag_container) != null){
            if (savedInstanceState != null) {
                return;
            }

            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.detail_frag_container, fragment)
                    .commit();
        }
    }
}
