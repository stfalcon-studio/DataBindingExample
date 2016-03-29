package com.troy379.databindingexample.data.models;

/**
 * Created by troy379 on 21.03.16.
 */
public class Counters {

    public Counters(int photos, int friends, int stars) {
        this.photos = photos;
        this.friends = friends;
        this.stars = stars;
    }

    private int photos;
    private int friends;
    private int stars;

    public int getPhotos() {
        return photos;
    }

    public int getFriends() {
        return friends;
    }

    public int getStars() {
        return stars;
    }
}
