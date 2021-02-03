package com.blogspot.soyamr.lifesimulation.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.blogspot.soyamr.lifesimulation.R;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;


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
//        granaryImg = Bitmap.createScaledBitmap(granaryImg, Dimensions.granaryWidth, Dimensions.granaryHeight, false);

        homeImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.house, options);
//        homeImg = Bitmap.createScaledBitmap(homeImg, GameObject.width * 2, GameObject.height * 2, false);

        appleImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple, options);
//        appleImg = Bitmap.createScaledBitmap(appleImg, GameObject.width, GameObject.height, false);

        oatImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.oat, options);
//        oatImg = Bitmap.createScaledBitmap(oatImg, GameObject.width, GameObject.height, false);

        carrotImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.carrot, options);
//        carrotImg = Bitmap.createScaledBitmap(carrotImg, GameObject.width, GameObject.height, false);

        rabbitImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabbit2, options);
//        rabbitImg = Bitmap.createScaledBitmap(rabbitImg, GameObject.width, GameObject.height, false);

        foxImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.fox, options);
//        foxImg = Bitmap.createScaledBitmap(foxImg, GameObject.width, GameObject.height, false);

        lionImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.lion, options);
//        lionImg = Bitmap.createScaledBitmap(lionImg, GameObject.width, GameObject.height, false);

        wolfImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.wolf, options);
//        wolfImg = Bitmap.createScaledBitmap(wolfImg, GameObject.width, GameObject.height, false);

        deerImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.deer, options);
//        deerImg = Bitmap.createScaledBitmap(deerImg, GameObject.width, GameObject.height, false);

        mouseImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.mouse, options);
//        mouseImg = Bitmap.createScaledBitmap(mouseImg, GameObject.width, GameObject.height, false);

        bearImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.bear, options);
//        bearImg = Bitmap.createScaledBitmap(bearImg, GameObject.width, GameObject.height, false);

        pigImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.pig, options);
//        pigImg = Bitmap.createScaledBitmap(pigImg, GameObject.width, GameObject.height, false);

        raccoonImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.raccoon, options);
//        raccoonImg = Bitmap.createScaledBitmap(raccoonImg, GameObject.width, GameObject.height, false);

        personImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.person, options);
//        personImg = Bitmap.createScaledBitmap(personImg, GameObject.width, GameObject.height, false);

        rabbitImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabbit2_f, options);
//        rabbitImgF = Bitmap.createScaledBitmap(rabbitImgF, GameObject.width, GameObject.height, false);

        foxImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.fox_f, options);
//        foxImgF = Bitmap.createScaledBitmap(foxImgF, GameObject.width, GameObject.height, false);

        lionImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.lion_f, options);
//        lionImgF = Bitmap.createScaledBitmap(lionImgF, GameObject.width, GameObject.height, false);

        wolfImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.wolf_f, options);
//        wolfImgF = Bitmap.createScaledBitmap(wolfImgF, GameObject.width, GameObject.height, false);

        deerImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.deer_f, options);
//        deerImgF = Bitmap.createScaledBitmap(deerImgF, GameObject.width, GameObject.height, false);

        mouseImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.mouse_f, options);
//        mouseImgF = Bitmap.createScaledBitmap(mouseImgF, GameObject.width, GameObject.height, false);

        bearImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.bear_f, options);
//        bearImgF = Bitmap.createScaledBitmap(bearImgF, GameObject.width, GameObject.height, false);

        pigImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.pig_f, options);
//        pigImgF = Bitmap.createScaledBitmap(pigImgF, GameObject.width, GameObject.height, false);

        raccoonImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.raccoon_f, options);
//        raccoonImgF = Bitmap.createScaledBitmap(raccoonImgF, GameObject.width, GameObject.height, false);

        personImgF = BitmapFactory.decodeResource(context.getResources(), R.drawable.person_f, options);
        arrow = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow, options);
        crown = BitmapFactory.decodeResource(context.getResources(), R.drawable.crown, options);
        danger = BitmapFactory.decodeResource(context.getResources(), R.drawable.danger, options);
//        personImgF = Bitmap.createScaledBitmap(personImgF, GameObject.width, GameObject.height, false);

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


//        for (int i = 1; i < 26; i++) {
//            bitmap[i] = Bitmap.createScaledBitmap(bitmap[i],
//                    8000, 8000, false);
//        }
        return bitmap;
    }
}
