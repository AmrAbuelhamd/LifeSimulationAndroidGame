package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person;

import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;

public interface HusbandCallbacks {
    void setWifeHome(HomeSweetHome home);
    boolean wannaMakeLove();

    Rect getRect();
}
