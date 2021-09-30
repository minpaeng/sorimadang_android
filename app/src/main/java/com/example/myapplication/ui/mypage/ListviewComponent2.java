package com.example.myapplication.ui.mypage;

public class ListviewComponent2 {
    private String instName;
    private String instExp;
    private int instImage;
    private int instColor;

    public ListviewComponent2(String instName, String instExp, int instImage, int instColor) {
        this.instName = instName;
        this.instExp = instExp;
        this.instImage = instImage;
        this.instColor = instColor;
    }

    public String getInstName() {
        return instName;
    }

    public String getInstExp() {
        return instExp;
    }

    public int getInstImage() {
        return instImage;
    }

    public int setBackgound() {
        return instColor;
    }

}
