package com.example.myapplication.ui.home;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

public class ListviewComponent {
    private String gameName;
    private String gameExp;
    private int gameImage;
    private int color;

    public ListviewComponent(String gameName, String gameExp, int gameImage, int color) {
        this.gameName = gameName;
        this.gameExp = gameExp;
        this.gameImage = gameImage;
        this.color = color;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameExp() {
        return gameExp;
    }

    public int getGameImage() {
        return gameImage;
    }

    public int setBackgound() {
        return color;
    }


}
