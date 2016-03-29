package com.troy379.databindingexample.utils;

/**
 * Created by troy379 on 21.03.16.
 */
public class TimerTask extends java.util.TimerTask {

    private Runnable runnable;

    public TimerTask(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        runnable.run();
    }
}
