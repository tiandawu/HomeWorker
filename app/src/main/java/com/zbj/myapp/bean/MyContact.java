package com.zbj.myapp.bean;

import java.util.ArrayList;

/**
 * Created by tiandawu on 2016/7/6.
 */
public class MyContact {

    private String name;
    private boolean isExpandable;
    private int level;

    private ArrayList<MyContact> childList = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<MyContact> getChildList() {
        if (null == childList) {
            childList = new ArrayList<>();
        }
        return childList;
    }


    public void addChild(MyContact child) {
        if (null == childList) {
            childList = new ArrayList<>();
        }
        childList.add(child);
    }

    public void clearAllChild() {
        if (null == childList) {
            childList = new ArrayList<>();
        }

        childList.clear();
    }

}
