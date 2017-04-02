package com.tomekgozdek.futureapp.repository;

import com.tomekgozdek.futureapp.model.FutureItem;

import java.util.List;

/**
 * Created by TomekG on 2017-04-02.
 */

public interface FutureRealmRepository {

    /**
     * Stores list of {@link FutureItem} synchronously. Check also
     * {@link #storeFutureItemsAsync(List, RepositoryCallback)} for asynchronous version.
     *
     * @param list list of {@link FutureItem} objects
     */
    void storeFutureItems(List<FutureItem> list);

    /**
     * Stores list of {@link FutureItem} asynchronously.
     * @param list list of {@link FutureItem} objects
     * @param callback {@link RepositoryCallback} object to get result of transaction
     */
    void storeFutureItemsAsync(List<FutureItem> list, RepositoryCallback callback);

    /**
     * Returns list of {@link FutureItem} sorted by orderId
     *
     * @return list
     */
    List<FutureItem> getFutureItems();

    /**
     * Returns {@link FutureItem} specifed by orderId.
     *
     * @param orderId the id of {@FutureItem} object
     *
     * @return {@link FutureItem} object or null if there was no element for provided id.
     */
    FutureItem getFutureItemByOrderId(int orderId);

    /**
     * Repository callback to get results of asynchronous repository transactions.
     */
    interface RepositoryCallback {
        void onTransactionCompleted(boolean success);
    }
}
