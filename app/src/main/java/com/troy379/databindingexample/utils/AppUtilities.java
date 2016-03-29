package com.troy379.databindingexample.utils;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.troy379.databindingexample.R;
import com.troy379.databindingexample.binding.fields.ObservableString;
import com.troy379.databindingexample.databinding.ItemInputDialogBinding;

/**
 * Created by troy379 on 16.03.16.
 */
public final class AppUtilities {
    private AppUtilities() { throw new AssertionError(); }

    public static void showSnackbar(Context context, @StringRes int stringRes, boolean isLong) {
        showSnackbar(context, context.getString(stringRes), isLong);
    }

    public static void showSnackbar(Context context, String text, boolean isLong) {
        if (context instanceof Activity) {
            View rootView = ((ViewGroup)((Activity)context).findViewById(android.R.id.content)).getChildAt(0);
            if (rootView instanceof CoordinatorLayout)
                showSnackbar((CoordinatorLayout)rootView, text, isLong);
        }
    }

    public static void showSnackbar(CoordinatorLayout layout, @StringRes int stringRes, boolean isLong) {
        showSnackbar(layout, layout.getResources().getString(stringRes), isLong);
    }

    public static void showSnackbar(CoordinatorLayout layout, String text, boolean isLong) {
        Snackbar snack = Snackbar.make(layout, text,
                isLong ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT);
        snack.setAction(android.R.string.ok, v -> {} );
        snack.show();
    }

    public static void showInputDialog(Context context, ObservableString text) {
        String oldText = text.get();
        ItemInputDialogBinding dialogBinding = DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.item_input_dialog, null, false);
        dialogBinding.setText(text);
        int margin = (int) context.getResources().getDimension(R.dimen.activity_horizontal_margin);

        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setPositiveButton(android.R.string.ok, null);
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> text.set(oldText));
        builder.setOnCancelListener(dialog -> text.set(oldText));
        builder.setView(dialogBinding.getRoot(), margin, margin, margin, margin);
        builder.show();
    }
}
