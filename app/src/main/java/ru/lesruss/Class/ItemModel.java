package ru.lesruss.Class;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class ItemModel {
    private String Title;
    private Bitmap Background;
    private Intent Intent;


    public ItemModel(String title, Bitmap background, android.content.Intent intent) {
        Title = title;
        Background = background;
        Intent = intent;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Bitmap getBackground() {
        return Background;
    }

    public void setBackground(Bitmap background) {
        Background = background;
    }

    public android.content.Intent getIntent() {
        return Intent;
    }

    public void setIntent(android.content.Intent intent) {
        Intent = intent;
    }
}
