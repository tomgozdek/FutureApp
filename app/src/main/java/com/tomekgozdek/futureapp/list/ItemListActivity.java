package com.tomekgozdek.futureapp.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.tomekgozdek.futureapp.R;
import com.tomekgozdek.futureapp.detail.DetailActivity;
import com.tomekgozdek.futureapp.detail.DetailFragment;
import com.tomekgozdek.futureapp.detail.DetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemListActivity extends AppCompatActivity implements ListFragment.OnItemSelectedListener {

    /** Flag indication that details can be show together with item list, in other words we got
     * master-detail layout
     */
    private boolean showDetails = false;

    @Nullable @BindView(R.id.no_item_prompt)
    TextView msgPrompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        ButterKnife.bind(this);

        showDetails = isMasterDetailLayout();
        if(ButterKnife.findById(this, R.id.list_frag_container) != null){
            ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.list_frag_container);

            if(listFragment == null){
                listFragment = new ListFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.list_frag_container, listFragment)
                        .commit();
            }


            ListPresenter listPresenter = new ListPresenter(listFragment);
            listFragment.setPresenter(listPresenter);
        }
    }

    private boolean isMasterDetailLayout() {
        return ButterKnife.findById(this, R.id.detail_frag_container) != null;
    }

    @Override
    public void onItemSelected(int orderId) {
        showItemDetails(orderId);
    }

    private void showItemDetails(int orderId) {
        //if we are not in master-detail layout just start another activity with detail fragment
        if(!showDetails) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_ORDER_ID, orderId);
            startActivity(intent);
        } else {
            msgPrompt.setVisibility(View.GONE);

            DetailFragment detailFragment = new DetailFragment();
            DetailPresenter detailPresenter = new DetailPresenter(orderId, detailFragment);
            detailFragment.setPresenter(detailPresenter);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_frag_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
