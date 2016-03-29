package com.troy379.databindingexample.ui.activities.main;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;

import com.troy379.databindingexample.BR;
import com.troy379.databindingexample.Demo;
import com.troy379.databindingexample.R;
import com.troy379.databindingexample.binding.fields.ObservableBoolean;
import com.troy379.databindingexample.binding.fields.ObservableInt;
import com.troy379.databindingexample.binding.fields.ObservableString;
import com.troy379.databindingexample.binding.fields.RecyclerConfiguration;
import com.troy379.databindingexample.data.models.User;
import com.troy379.databindingexample.ui.adapters.RecyclerBindingAdapter;
import com.troy379.databindingexample.utils.AppUtilities;
import com.troy379.databindingexample.utils.TimerTask;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

/**
 * Created by troy379 on 22.03.16.
 */
public class ProfileViewModel {

    private static final int LAYOUT_HOLDER = R.layout.item_photo;
    private static final int PHOTOS_COLUMN_COUNT = 3;

    public final ObservableString status = new ObservableString();
    public final ObservableBoolean isLoaded = new ObservableBoolean(true);
    public final ObservableBoolean isOnline = new ObservableBoolean();
    public final ObservableBoolean isFriend = new ObservableBoolean();
    public final ObservableInt friendsCount = new ObservableInt();
    public final ObservableInt starsCount = new ObservableInt();
    public final RecyclerConfiguration recyclerConfiguration = new RecyclerConfiguration();
    public User user;

    private Context context;

    public ProfileViewModel(Context context, User user) {
        this.context = context;
        this.user = user;

        status.set(user.getStatus());
        isOnline.set(user.isOnline());
        isFriend.set(user.isFriend());
        friendsCount.set(user.getCounters().getFriends());
        starsCount.set(user.getCounters().getStars());

        initRecycler();
        startTimer();
        Demo.simulateLoading(() -> isOnline.set(true), true);
    }

    public void changeFriendshipStatus() {
        load(() -> {
            if (isFriend.get()) friendsCount.decrement();
            else friendsCount.increment();
            isFriend.set(!isFriend.get());
        });
    }

    public void edit() {
        AppUtilities.showInputDialog(context, status);
    }

    public void showDevMessage() {
        AppUtilities.showSnackbar(context, R.string.dev_message, false);
    }

    private void initRecycler() {
        RecyclerBindingAdapter<String> adapter = getAdapter();

        recyclerConfiguration.setLayoutManager(new GridLayoutManager(context, PHOTOS_COLUMN_COUNT));
        recyclerConfiguration.setItemAnimator(new DefaultItemAnimator());
        recyclerConfiguration.setAdapter(adapter);
    }

    private RecyclerBindingAdapter<String> getAdapter() {
        ArrayList<String> photos = Demo.getPhotos();
        RecyclerBindingAdapter<String> adapter = new RecyclerBindingAdapter<>(LAYOUT_HOLDER, BR.url, photos);
        adapter.setOnItemClickListener((position, item)
                -> AppUtilities.showSnackbar(
                context,
                context.getString(R.string.photo_message, position + 1),
                false));
        return adapter;
    }

    private void startTimer() {
        new Timer().schedule(
                new TimerTask(() -> starsCount.set(starsCount.get() + 1)),
                0, new Random().nextInt(Demo.LOADING_LONG));
    }

    private void load(Runnable onLoaded) {
        isLoaded.set(false);
        Demo.simulateLoading(() -> {
            onLoaded.run();
            isLoaded.set(true);
        }, false);
    }
}
