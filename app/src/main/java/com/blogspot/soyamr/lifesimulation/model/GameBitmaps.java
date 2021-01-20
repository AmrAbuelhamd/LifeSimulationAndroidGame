package com.blogspot.soyamr.lifesimulation.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.blogspot.soyamr.lifesimulation.R;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class GameBitmaps {
    public Bitmap granaryImg;
    public Bitmap homeImg;
    public Bitmap rabbitImg;
    public Bitmap foxImg;
    public Bitmap lionImg;
    public Bitmap wolfImg;
    public Bitmap deerImg;
    public Bitmap mouseImg;
    public Bitmap bearImg;
    public Bitmap pigImg;
    public Bitmap raccoonImg;
    public Bitmap personImg;
    public Bitmap appleImg;
    public Bitmap oatImg;
    public Bitmap carrotImg;
    public Bitmap rabbitImgF;
    public Bitmap foxImgF;
    public Bitmap lionImgF;
    public Bitmap wolfImgF;
    public Bitmap deerImgF;
    public Bitmap mouseImgF;
    public Bitmap bearImgF;
    public Bitmap pigImgF;
    public Bitmap raccoonImgF;
    public Bitmap personImgF;
    Context context;

    public GameBitmaps(Context context) {
        this.context = context;
        granaryImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.granary);
        granaryImg = Bitmap.createScaledBitmap(granaryImg, Dimensions.granaryWidth, Dimensions.granaryHeight, false);

        homeImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.house2);
        homeImg = Bitmap.createScaledBitmap(homeImg, GameObject.width * 2, GameObject.height * 2, false);

        appleImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple);
        appleImg = Bitmap.createScaledBitmap(appleImg, GameObject.width, GameObject.height, false);

        oatImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.oat);
        oatImg = Bitmap.createScaledBitmap(oatImg, GameObject.width, GameObject.height, false);

        carrotImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.carrot);
        carrotImg = Bitmap.createScaledBitmap(carrotImg, GameObject.width, GameObject.height, false);

        rabbitImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabbit2);
        rabbitImg = Bitmap.createScaledBitmap(rabbitImg, GameObject.width, GameObject.height, false);

        foxImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.fox);
        foxImg = Bitmap.createScaledBitmap(foxImg, GameObject.width, GameObject.height, false);

        lionImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.lion);
        lionImg = Bitmap.createScaledBitmap(lionImg, GameObject.width, GameObject.height, false);

        wolfImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.wolf);
        wolfImg = Bitmap.createScaledBitmap(wolfImg, GameObject.width, GameObject.height, false);

        deerImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.deer);
        deerImg = Bitmap.createScaledBitmap(deerImg, GameObject.width, GameObject.height, false);

        mouseImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.mouse);
        mouseImg = Bitmap.createScaledBitmap(mouseImg, GameObject.width, GameObject.height, false);

        bearImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.bear);
        bearImg = Bitmap.createScaledBitmap(bearImg, GameObject.width, GameObject.height, false);

        pigImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.pig);
        pigImg = Bitmap.createScaledBitmap(pigImg, GameObject.width, GameObject.height, false);

        raccoonImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.raccoon);
        raccoonImg = Bitmap.createScaledBitmap(raccoonImg, GameObject.width, GameObject.height, false);

        personImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.person);
        personImg = Bitmap.createScaledBitmap(personImg, GameObject.width, GameObject.height, false);

        rabbitImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabbit2_f);
        rabbitImgF = Bitmap.createScaledBitmap(rabbitImgF, GameObject.width, GameObject.height, false);

        foxImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.fox_f);
        foxImgF = Bitmap.createScaledBitmap(foxImgF, GameObject.width, GameObject.height, false);

        lionImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.lion_f);
        lionImgF = Bitmap.createScaledBitmap(lionImgF, GameObject.width, GameObject.height, false);

        wolfImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.wolf_f);
        wolfImgF = Bitmap.createScaledBitmap(wolfImgF, GameObject.width, GameObject.height, false);

        deerImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.deer_f);
        deerImgF = Bitmap.createScaledBitmap(deerImgF, GameObject.width, GameObject.height, false);

        mouseImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.mouse_f);
        mouseImgF = Bitmap.createScaledBitmap(mouseImgF, GameObject.width, GameObject.height, false);

        bearImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.bear_f);
        bearImgF = Bitmap.createScaledBitmap(bearImgF, GameObject.width, GameObject.height, false);

        pigImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.pig_f);
        pigImgF = Bitmap.createScaledBitmap(pigImgF, GameObject.width, GameObject.height, false);

        raccoonImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.raccoon_f);
        raccoonImgF = Bitmap.createScaledBitmap(raccoonImgF, GameObject.width, GameObject.height, false);

        personImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.person_f);
        personImgF = Bitmap.createScaledBitmap(personImgF, GameObject.width, GameObject.height, false);

    }
}
