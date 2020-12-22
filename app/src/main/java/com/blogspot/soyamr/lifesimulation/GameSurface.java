package com.blogspot.soyamr.lifesimulation;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Plant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.blogspot.soyamr.lifesimulation.Const.Direction.DOWN;
import static com.blogspot.soyamr.lifesimulation.Const.Direction.LEFT;
import static com.blogspot.soyamr.lifesimulation.Const.Direction.RIGHT;
import static com.blogspot.soyamr.lifesimulation.Const.Direction.UP;


public class GameSurface extends SurfaceView implements SurfaceHolder.Callback, Controller {
    final int updateInfoThreshold = 50;
    private final ScaleListener scaleListener;
    Rect clipBoundsCanvas;


    Model model;
    int uith = 0;
    boolean isClick = false;
    private GameThread gameThread;

    public GameSurface(Context context) {
        super(context);

        // Make Game Surface focusable so it can handle events.
        this.setFocusable(true);

        // Set callback.
        this.getHolder().addCallback(this);

        scaleListener = new ScaleListener(this);
        model = new Model(context);

    }

    public void update() {
        //updateLogInfo();
        model.update(clipBoundsCanvas, scaleListener.mScaleFactor);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.save();
        canvas.scale(scaleListener.mScaleFactor, scaleListener.mScaleFactor, scaleListener.focusX, scaleListener.focusY);
        canvas.translate(scaleListener.mPosX, scaleListener.mPosY);
        clipBoundsCanvas = canvas.getClipBounds();
//        model.update(clipBoundsCanvas, scaleListener.mScaleFactor);

        model.draw(canvas);
//        drawWhite(canvas);
//        drawmyalgorithm(canvas);

        canvas.restore();
    }

    public void updateLogInfo() {
        if (uith < updateInfoThreshold) {
            ++uith;
        } else {
            model.updateLogInfo();
            uith = 0;
        }
    }




    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        resume();
        setWillNotDraw(false);
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        pause();
    }

    public void pause() {
        this.gameThread.executor.shutdown();
    }

    public void resume() {
        this.gameThread = new GameThread(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleListener.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isClick = true;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (isClick) {
                    int x = (int) (event.getX() / scaleListener.mScaleFactor + clipBoundsCanvas.left);
                    int y = (int) (event.getY() / scaleListener.mScaleFactor + clipBoundsCanvas.top);


                    int rangeNumberX = x / Const.CELL_WIDTH;

                    int lowerBoundRangeX = rangeNumberX * Const.CELL_WIDTH;

                    int rangeNumberY = y / Const.CELL_HEIGHT;
                    int lowerBoundRangeY = rangeNumberY * Const.CELL_HEIGHT;

                    GameObject gameObject = Utils.searchAroundAnimal(Const.USER_CLICK_SEARCH_RANG
                            , lowerBoundRangeX - Const.CELL_WIDTH,
                            lowerBoundRangeY - Const.CELL_HEIGHT, model,
                            List.of(Type.RABBIT, Type.FOX, Type.WOLF, Type.PIG
                                    , Type.MOUSE, Type.PERSON, Type.BEAR, Type.DEER,
                                    Type.LION, Type.RACCOON,Type.APPLE,Type.CARROT,Type.OAT),
                            GenderEnum.BOTH).stream().findFirst().orElse(null);

//                    model.setFamousAnimal(gameObject);
                    if (gameObject != null) {
                        gameObject.makeMeFamous();
                    } else {
                        model.setFamousAnimal(null);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                isClick = false;
                break;
            default:
                break;
        }
        return true;
    }

}