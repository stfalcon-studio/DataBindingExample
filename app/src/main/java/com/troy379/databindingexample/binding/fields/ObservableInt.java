package com.troy379.databindingexample.binding.fields;

/**
 * Created by troy379 on 21.03.16.
 */
public class ObservableInt extends android.databinding.ObservableInt {

    public void increment() {
        set(get() + 1);
    }

    public void decrement() {
        set(get() - 1);
    }
}
