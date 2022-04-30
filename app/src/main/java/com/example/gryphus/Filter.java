package com.example.gryphus;

import android.graphics.drawable.Drawable;

public class Filter {
private String type;
private Drawable icon;

public Filter(String type) { this.type = type;}

    public Filter(String name, Drawable icon) {
        this.type = type;
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public Drawable getIcon() {
        return icon;
    }
}
