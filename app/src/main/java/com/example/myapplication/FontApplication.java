package com.example.myapplication;
import java.lang.reflect.Field;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

public class FontApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setDefaultFont(this, "DEFAULT", "imhyeminregular.ttf");
        setDefaultFont(this, "SANS_SERIF", "imhyeminregular.ttf");
        setDefaultFont(this, "SERIF", "imhyeminbold.ttf");
    }

    public static void setDefaultFont(Context ctx,
                                      String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(ctx.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field StaticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            StaticField.setAccessible(true);
            StaticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
