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

import com.blogspot.soyamr.lifesimulation.model.game_elements.Animal;
import com.blogspot.soyamr.lifesimulation.model.types.Species;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Plant;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.blogspot.soyamr.lifesimulation.Const.Direction.DOWN;
import static com.blogspot.soyamr.lifesimulation.Const.Direction.LEFT;
import static com.blogspot.soyamr.lifesimulation.Const.Direction.RIGHT;
import static com.blogspot.soyamr.lifesimulation.Const.Direction.UP;


public class GameSurface extends SurfaceView implements SurfaceHolder.Callback, Controller {
    private final ScaleListener scaleListener;
    private GameThread gameThread;
    Rect clipBoundsCanvas;


    Model model;

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
        updateLogInfo();
        model.update(clipBoundsCanvas, scaleListener.mScaleFactor);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.save();
        canvas.scale(scaleListener.mScaleFactor, scaleListener.mScaleFactor, scaleListener.focusX, scaleListener.focusY);
        canvas.translate(scaleListener.mPosX, scaleListener.mPosY);
        clipBoundsCanvas = canvas.getClipBounds();

        model.draw(canvas);
//        drawWhite(canvas);
//        drawmyalgorithm(canvas);

        canvas.restore();
    }

    final int updateInfoThreshold = 50;
    int uith = 0;

    public void updateLogInfo() {
        if (uith < updateInfoThreshold) {
            ++uith;
        } else {
            model.updateLogInfo();
            uith = 0;
        }
    }


    private void drawmyalgorithm(Canvas canvas) {
        Plant nearestPlant = null;
        Paint paint;

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        final int[][] moveDirection = new int[][]{
                {0, -1},
                {1, -1},
                {1, 0},
                {1, 1},
                {0, 1},
                {-1, 1},
                {-1, 0},
                {-1, -1},
        };
        int x = Const.FIELD_WIDTH / 2;
        int y = Const.FIELD_HEIGHT / 2;

        for (int i = 0; i < 100; i++) {
            int width = Animal.width * (i + 1);
            int height = Animal.height * (i + 1);
            for (int j = 0; j < moveDirection.length; j++) {
                int nextCellX = x + moveDirection[j][0] * width;
                int nextCellY = y + moveDirection[j][1] * height;
                String plantKey = nextCellX + " " + nextCellY;
                //once found any plant, this means it's the closes no need to search further.
                canvas.drawRect(new Rect(nextCellX, nextCellY, nextCellX + Const.CELL_HEIGHT, nextCellY + Const.CELL_HEIGHT), paint);

            }
        }
    }

    private void drawWhite(Canvas canvas) {

        // The input matrix  dimension
        int i = 0;
        int matrixLength = 100;
        int numberOfElements = matrixLength * matrixLength;
        Map<String, Integer> mask = new LinkedHashMap<>();
        int y = Const.FIELD_HEIGHT / 2;
        int x = Const.FIELD_WIDTH / 2;
        Paint paint;
        Const.Direction nextDirection = LEFT;
        //The number of elements of the input matrix
        mask.put(y + " " + x, 1);

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        // The matrix mask help to decide which is the next direction or the next element to pick from the input matrix
        // All the values of the mask are initialized to zero.

        //The first element of the output(the spiral) is always the middle element of the input matrix


        // Each time an element from the input matrix is added to the output spiral, the corresponding element in the mask is set to 1

        // The first direction is always to the left

        // This is a counter to loop through all the elements of the input matrix only one time


        while (i++ < numberOfElements - 1) {
            // Check which direction to go (left, down, right or up)
            switch (nextDirection) {

                case LEFT:
                    // From the input matrix, take the number at the left of the current position
                    // (which is the middle of the input matrix) and add it to the spiral
                    x -= Const.CELL_WIDTH;
                    //Update the mask
                    mask.put(y + " " + x, 1);
                    canvas.drawRect(new Rect(x, y, x + Const.CELL_HEIGHT, y + Const.CELL_HEIGHT), paint);

                    // Decide which direction(or element in the input matrix) to take next.
                    // After moving to the left, you only have two choices : keeping the same direction or moving down
                    // To know which direction to take, check the mask
                    if (mask.containsKey((int) (y + Const.CELL_HEIGHT) + " " + x)) {
                        nextDirection = LEFT;
                    } else {
                        nextDirection = DOWN;
                    }
                    break;

                case DOWN:
                    y += Const.CELL_HEIGHT;

                    canvas.drawRect(new Rect(x, y, x + Const.CELL_HEIGHT, y + Const.CELL_HEIGHT), paint);

                    //Update the mask
                    mask.put(y + " " + x, 1);
                    if (mask.containsKey(y + " " + (int) (x + Const.CELL_WIDTH))) {
                        nextDirection = DOWN;
                    } else {
                        nextDirection = RIGHT;
                    }
                    break;

                case RIGHT:
                    x += Const.CELL_WIDTH;
                    canvas.drawRect(new Rect(x, y, x + Const.CELL_HEIGHT, y + Const.CELL_HEIGHT), paint);


                    //Update the mask
                    mask.put(y + " " + x, 1);
                    if (mask.containsKey((int) (y - Const.CELL_HEIGHT) + " " + x)) {
                        nextDirection = RIGHT;
                    } else {
                        nextDirection = UP;
                    }
                    break;

                case UP:
                    y -= Const.CELL_HEIGHT;
                    canvas.drawRect(new Rect(x, y, x + Const.CELL_HEIGHT, y + Const.CELL_HEIGHT), paint);

                    //Update the mask
                    mask.put(y + " " + x, 1);
                    if (mask.containsKey(y + " " + (int) (x - Const.CELL_WIDTH))) {
                        nextDirection = UP;
                    } else {
                        nextDirection = LEFT;
                    }
                    break;
            }
        }

        y = Const.FIELD_HEIGHT / 2;
        x = Const.FIELD_WIDTH / 2;
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(x, y, x + Const.CELL_HEIGHT, y + Const.CELL_HEIGHT), paint);

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        resume();
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

    boolean isClick = false;

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

                    Animal animal = (Animal) Utils.searchAroundAnimal(Const.USER_CLICK_SEARCH_RANG
                            , lowerBoundRangeX - Const.CELL_WIDTH,
                            lowerBoundRangeY - Const.CELL_HEIGHT,
                            model, Species.ANIMAL).stream().findFirst().orElse(null);

                    model.setFamousAnimal(animal);

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