package com.lingrixin.donetwork.utils;

import android.view.View;

/**
 * Created by songy on 2017/4/9.
 */

public class V {
    public static void c(View.OnClickListener l, View... views) {
        if (l != null && views != null && views.length > 0) {
            for (View view : views) {
                view.setOnClickListener(l);
            }
        }
    }
}
