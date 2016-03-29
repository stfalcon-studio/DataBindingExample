package com.troy379.databindingexample.binding;

import android.databinding.BindingAdapter;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.troy379.databindingexample.R;
import com.troy379.databindingexample.binding.fields.ObservableString;
import com.troy379.databindingexample.binding.fields.RecyclerConfiguration;
import com.troy379.databindingexample.utils.TextChangeListener;

/**
 * Created by troy379 on 15.03.16.
 */
public final class BindingAdapters {
    private BindingAdapters() { throw new AssertionError(); }

    @BindingAdapter("android:src")
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(url)
                .placeholder(R.drawable.bg_image_holder)
                .into(view);
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("android:text")
    public static void bindEditText(EditText view,
                                    final ObservableString observableString) {
        Pair<ObservableString, TextChangeListener> pair = (Pair) view.getTag(R.id.bound_observable);

        if (pair == null || pair.first != observableString) {
            if (pair != null) view.removeTextChangedListener(pair.second);

            TextChangeListener watcher = new TextChangeListener(
                    (s, start, before, count) -> observableString.set(s.toString()));

            view.setTag(R.id.bound_observable, new Pair<>(observableString, watcher));
            view.addTextChangedListener(watcher);
        }
        String newValue = observableString.get();
        if (!view.getText().toString().equals(newValue))
            view.setText(newValue);
    }

    @BindingAdapter("app:onClick")
    public static void bindOnClick(View view, final Runnable runnable) {
        view.setOnClickListener(v -> runnable.run());
    }
}
