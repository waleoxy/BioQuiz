package com.olawaleotubu.biologyquiz;

public class Category {
    private int catitle;
    private int catImgId;

    public Category() {

    }
    public Category(int catitle, int catImgId) {
        this.catitle = catitle;
        this.catImgId = catImgId;
    }

    public int getCatitle() {
        return catitle;
    }

    public void setCatitle(int catitle) {
        this.catitle = catitle;
    }

    public int getCatImgId() {
        return catImgId;
    }

    public void setCatImgId(int catImgId) {
        this.catImgId = catImgId;
    }
}
