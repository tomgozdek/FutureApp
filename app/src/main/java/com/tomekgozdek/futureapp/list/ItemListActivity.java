package com.tomekgozdek.futureapp.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tomekgozdek.futureapp.R;

import butterknife.ButterKnife;

public class ItemListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        ButterKnife.bind(this);

        if(ButterKnife.findById(this, R.id.list_frag_container) != null){
            ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.list_frag_container);

            if(listFragment == null){
                listFragment = new ListFragment();
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.list_frag_container, listFragment)
                    .commit();

            ListPresenter listPresenter = new ListPresenter(listFragment);
            listFragment.setPresenter(listPresenter);
        }
    }
}
