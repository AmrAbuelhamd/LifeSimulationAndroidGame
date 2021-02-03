package com.blogspot.soyamr.lifesimulation.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.blogspot.soyamr.lifesimulation.R;


public class GameBitmaps {
    public static Bitmap granaryImg;
    public static Bitmap homeImg;
    public static Bitmap rabbitImg;
    public static Bitmap foxImg;
    public static Bitmap lionImg;
    public static Bitmap wolfImg;
    public static Bitmap deerImg;
    public static Bitmap mouseImg;
    public static Bitmap bearImg;
    public static Bitmap pigImg;
    public static Bitmap raccoonImg;
    public static Bitmap personImg;
    public static Bitmap appleImg;
    public static Bitmap oatImg;
    public static Bitmap carrotImg;
    public static Bitmap rabbitImgF;
    public static Bitmap foxImgF;
    public static Bitmap lionImgF;
    public static Bitmap wolfImgF;
    public static Bitmap deerImgF;
    public static Bitmap mouseImgF;
    public static Bitmap bearImgF;
    public static Bitmap pigImgF;
    public static Bitmap raccoonImgF;
    public static Bitmap personImgF;
    public static Bitmap arrow;
    public static Bitmap crown;
    public static Bitmap danger;
    Context context;

    BitmapFactory.Options options = new BitmapFactory.Options();

    public GameBitmaps(Context context) {
        this.context = context;
        options.inScaled = false;
        granaryImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.granary, options);
        homeImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.house, options);
        appleImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple, options);
        oatImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.oat, options);
        carrotImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.carrot, options);
        rabbitImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabbit2, options);
        foxImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.fox, options);
        lionImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.lion, options);
        wolfImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.wolf, options);
        deerImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.deer, options);
        mouseImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.mouse, options);
        bearImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.bear, options);
        pigImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.pig, options);
        raccoonImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.raccoon, options);
        personImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.person, options);
        rabbitImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabbit2_f, options);
        foxImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.fox_f, options);
        lionImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.lion_f, options);
        wolfImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.wolf_f, options);
        deerImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.deer_f, options);
        mouseImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.mouse_f, options);
        bearImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.bear_f, options);
        pigImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.pig_f, options);
        raccoonImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.raccoon_f, options);
        personImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.person_f, options);
        arrow = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow, options);
        crown = BitmapFactory.decodeResource(context.getResources(), R.drawable.crown, options);
        danger = BitmapFactory.decodeResource(context.getResources(), R.drawable.danger, options);
    }

    Bitmap[] createPhotos() {
        Bitmap[] bitmap;
        bitmap = new Bitmap[26];
        bitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_1, options);
        bitmap[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_2, options);
        bitmap[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_3, options);
        bitmap[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_4, options);
        bitmap[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_5, options);
        bitmap[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_6, options);
        bitmap[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_7, options);
        bitmap[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_8, options);
        bitmap[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_9, options);
        bitmap[10] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_10, options);
        bitmap[11] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_11, options);
        bitmap[12] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_12, options);
        bitmap[13] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_13, options);
        bitmap[14] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_14, options);
        bitmap[15] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_15, options);
        bitmap[16] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_16, options);
        bitmap[17] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_17, options);
        bitmap[18] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_18, options);
        bitmap[19] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_19, options);
        bitmap[20] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_20, options);
        bitmap[21] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_21, options);
        bitmap[22] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_22, options);
        bitmap[23] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_23, options);
        bitmap[24] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_24, options);
        bitmap[25] = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_part_25, options);
        return bitmap;
    }
}
