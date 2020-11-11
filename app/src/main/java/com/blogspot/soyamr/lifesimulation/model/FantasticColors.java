package com.blogspot.soyamr.lifesimulation.model;

import android.content.Context;

import com.blogspot.soyamr.lifesimulation.R;

public class FantasticColors {
    public enum TYPE {
        female, male
    }

    private final Context context;

    public FantasticColors(Context context) {
        this.context = context;
    }

    int getColor(TYPE type, int level) {
        switch (type) {
            case female:
                return getFemaleColor(level);
            case male:
                return getMaleColor(level);
            default:
                return 0;//todo return strange color to know that something went wrong
        }
    }

    private int getMaleColor(int level) {
        return context.getColor(R.color.m100);
//        switch (level) {
//            case 100:
//            case 90:
//                return context.getColor(R.color.m100);
//            case 80:
//            case 70:
//                return context.getColor(R.color.m80);
//            case 60:
//            case 50:
//                return context.getColor(R.color.m60);
//            case 40:
//            case 30:
//                return context.getColor(R.color.m40);
//            case 20:
//            case 10:
//                return context.getColor(R.color.m20);
//            default:
//                return context.getColor(R.color.m0);
//        }
    }

    private int getFemaleColor(int level) {
        return context.getColor(R.color.f100);
//
//        switch (level) {
//            case 100:
//            case 90:
//                return context.getColor(R.color.f100);
//            case 80:
//            case 70:
//                return context.getColor(R.color.f80);
//            case 60:
//            case 50:
//                return context.getColor(R.color.f60);
//            case 40:
//            case 30:
//                return context.getColor(R.color.f40);
//            case 20:
//            case 10:
//                return context.getColor(R.color.f20);
//            default:
//                return context.getColor(R.color.f0);
//        }
    }


}
