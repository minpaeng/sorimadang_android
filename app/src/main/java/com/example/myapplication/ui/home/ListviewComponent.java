package com.example.myapplication.ui.home;

import android.graphics.drawable.Drawable;

public class ListviewComponent {
    private String gameName;
    private String gameExp;
    private int gameImage;

    public ListviewComponent(String gameName, String gameExp, int gameImage) {
        this.gameName = gameName;
        this.gameExp = gameExp;
        this.gameImage = gameImage;
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


}
