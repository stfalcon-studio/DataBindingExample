package com.troy379.databindingexample;

import android.os.Handler;

import com.troy379.databindingexample.data.models.Counters;
import com.troy379.databindingexample.data.models.User;

import java.util.ArrayList;
import java.util.Arrays;

public final class Demo {
    private Demo() { throw new AssertionError(); }

    public static final int LOADING_SHORT = 1000;
    public static final int LOADING_LONG = 3000;

    private static final String[] PHOTOS = {
            "http://cp91279.biography.com/1000509261001/1000509261001_1822909398001_BIO-Biography-29-Innovators-Mark-Zuckerberg-115956-SF.jpg",
            "http://www.taxjusticeblog.org/images/zuckerberg.jpg",
            "http://www.bizpacreview.com/wp-content/uploads/2013/03/mark-zuckerberg.jpg",
            "http://a.abcnews.go.com/images/Technology/gty_mark_zuckerberg_ll_111101_wmain.jpg",
            "http://a.abcnews.go.com/images/Technology/gty_mark_zuckerberg_tie_jc_141219_4x3_992.jpg",
            "http://images2.persianblog.ir/6832_3NuwrsgD.jpg",
            "http://forum.theworldnewsmedia.org/uploads/monthly_2016_02/56cc09ae54f68_MARKZ-INBARCELONA-MESSE.jpg.2724d3cbd6e7de34cc121537405d22f8.jpg"
    };

    private static final String NAME = "Mark";
    private static final String SURNAME = "Zuckerberg";
    private static final String AVATAR = "http://latimesblogs.latimes.com/.a/6a00d8341c630a53ef0153907522cc970b-800wi";
    private static final String STATUS = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
    private static final Counters COUNTERS = new Counters(PHOTOS.length * 3, 1000, 123119);
    private static final boolean IS_ONLINE = false;
    private static final boolean IS_FRIEND = false;

    public static User getUser() {
        return new User(NAME, SURNAME, AVATAR, STATUS, COUNTERS, IS_ONLINE, IS_FRIEND);
    }

    public static ArrayList<String> getPhotos() {
        ArrayList<String> photos = new ArrayList<>();

        photos.addAll(Arrays.asList(PHOTOS));
        photos.addAll(Arrays.asList(PHOTOS));
        photos.addAll(Arrays.asList(PHOTOS));

        return photos;
    }

    public static void simulateLoading(Runnable onLoaded, boolean isLong) {
        new Handler().postDelayed(onLoaded, isLong ? LOADING_LONG : LOADING_SHORT);
    }
}
