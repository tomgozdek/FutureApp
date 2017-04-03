package com.tomekgozdek.futureapp.list;

import com.tomekgozdek.futureapp.api.ApiClient;
import com.tomekgozdek.futureapp.api.ApiResponse;
import com.tomekgozdek.futureapp.api.FutureService;
import com.tomekgozdek.futureapp.model.FutureItem;
import com.tomekgozdek.futureapp.presenter.BasicView;
import com.tomekgozdek.futureapp.presenter.Presenter;
import com.tomekgozdek.futureapp.repository.FutureRealmRepository;
import com.tomekgozdek.futureapp.repository.FutureRealmRepositoryImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TomekG on 2017-04-03.
 */

public class ListPresenter implements Presenter {

    private final View mView;

    public ListPresenter(View view){
        mView = view;
    }
    @Override
    public void onResume() {
        loadItemsFromRepository();
    }

    private void downloadItems() {
        mView.showProgress();
        FutureService service = ApiClient.getApiClient().create(FutureService.class);

        Call<ApiResponse> request = service.listFutureItems();
        request.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful()){
                    storeItems(response.body().getData());
                } else {
                    //TODO handle failure case
                    mView.hideProgress();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                //TODO handle failure case
                mView.hideProgress();
            }
        });
    }

    private void storeItems(List<FutureItem> list) {
        FutureRealmRepository repository = new FutureRealmRepositoryImpl();
        repository.storeFutureItemsAsync(list, new FutureRealmRepository.RepositoryCallback() {
            @Override
            public void onTransactionCompleted(boolean success) {
                if(success) {
                    loadItemsFromRepository();
                }
            }
        });
    }

    private void loadItemsFromRepository(){
        FutureRealmRepository repository = new FutureRealmRepositoryImpl();
        if(repository.getFutureItems().isEmpty()){
            downloadItems();
        } else {
            mView.loadItems(repository.getFutureItems());
            mView.hideProgress();
        }
    }

    interface View extends BasicView<ListPresenter>{
        void loadItems(List<FutureItem> list);
        void showProgress();
        void hideProgress();
    }
}
