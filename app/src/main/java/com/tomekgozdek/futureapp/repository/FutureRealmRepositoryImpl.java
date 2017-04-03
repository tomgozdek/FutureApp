package com.tomekgozdek.futureapp.repository;

import com.tomekgozdek.futureapp.model.FutureItem;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Realm implementation of repository
 */

public class FutureRealmRepositoryImpl implements FutureRealmRepository {

    @Override
    public void storeFutureItems(List<FutureItem> list) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for(FutureItem item : list){
            realm.copyFromRealm(item);
        }
        realm.commitTransaction();
    }

    @Override
    public void storeFutureItemsAsync(final List<FutureItem> list, final FutureRealmRepository.RepositoryCallback callback) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                            realm.copyToRealmOrUpdate(list);
                    }
                }, new Realm.Transaction.OnSuccess(){

                    @Override
                    public void onSuccess() {
                        callback.onTransactionCompleted(true);
                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        callback.onTransactionCompleted(false);
                    }
                });
    }

    @Override
    public List<FutureItem> getFutureItems() {
        RealmQuery<FutureItem> query = Realm.getDefaultInstance().where(FutureItem.class);
        RealmResults<FutureItem> resultQuery = query.findAllSorted("orderId");
        return resultQuery.subList(0, resultQuery.size()-1);
    }

    @Override
    public FutureItem getFutureItemByOrderId(int orderId){
        RealmQuery<FutureItem> query = Realm.getDefaultInstance().where(FutureItem.class);
        RealmResults<FutureItem> results = query.equalTo("orderId", orderId).findAll();
        return results.size() > 0 ? results.get(0) : null;
    }
}
