package com.tomekgozdek.futureapp.repository;

import com.tomekgozdek.futureapp.BuildConfig;
import com.tomekgozdek.futureapp.model.FutureItem;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.log.RealmLog;

import static org.junit.Assert.*;

/**
 * Created by TomekG on 2017-04-02.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
@PrepareForTest({Realm.class, RealmLog.class})
public class FutureRealmRepositoryTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();
    Realm mockRealm;

    @Before
    public void setup(){
        PowerMockito.mockStatic(RealmLog.class);
        PowerMockito.mockStatic(Realm.class);

        Realm mockRealm = PowerMockito.mock(Realm.class);
        Mockito.when(Realm.getDefaultInstance()).thenReturn(mockRealm);
        this.mockRealm = mockRealm;
    }

    @Test
    public void creatingFutureItemShouldWork(){
        FutureItem item = new FutureItem();
        Mockito.when(mockRealm.createObject(FutureItem.class)).thenReturn(item);

        FutureItem actualItem = mockRealm.createObject(FutureItem.class);
        Assert.assertThat(actualItem, CoreMatchers.is(item));
    }


    @Test
    public void checkIfTransactionWasExecuted(){

        List<FutureItem> list = new ArrayList<FutureItem>();
        list.add(new FutureItem());

        FutureRealmRepository repository = new FutureRealmRepositoryImpl();
        repository.storeFutureItems(list);

        Mockito.verify(mockRealm, Mockito.times(1)).beginTransaction();
        Mockito.verify(mockRealm, Mockito.times(1)).commitTransaction();
    }

}