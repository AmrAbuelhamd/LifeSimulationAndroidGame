package com.blogspot.soyamr.lifesimulation;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.blogspot.soyamr.lifesimulation.Direction.DOWN;
import static com.blogspot.soyamr.lifesimulation.Direction.LEFT;
import static com.blogspot.soyamr.lifesimulation.Direction.RIGHT;
import static com.blogspot.soyamr.lifesimulation.Direction.UP;


public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread gameThread;

    public final List<Cell> cells = new CopyOnWriteArrayList<>();
    public final List<Creature> creatures = new CopyOnWriteArrayList<>();
    public final Map<String, Plant> plants = new ConcurrentHashMap<>();

    private static final int INVALID_POINTER_ID = -1;

    private float mPosX;
    private float mPosY;

    private float mLastTouchX;
    private float mLastTouchY;
    private int mActivePointerId = INVALID_POINTER_ID;

    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

    private float focusX;
    private float focusY;

    private float lastFocusX = -1;
    private float lastFocusY = -1;


    public GameSurface(Context context) {
        super(context);

        // Make Game Surface focusable so it can handle events.
        this.setFocusable(true);

        // Set callback.
        this.getHolder().addCallback(this);

        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());

        init();
    }

    void init() {//todo divide this to three functions


        //create cells
        List<Cell> tempCells = new ArrayList<>();
        for (int i = 0; i < Const.M; i++) {
            for (int j = 0; j < Const.N; j++) {
                Cell cell = new Cell(i, j);
                tempCells.add(cell);
            }
        }
        cells.addAll(tempCells);
        //create creatures
        List<Creature> tempCreatures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Creature creature = new Creature();
            tempCreatures.add(creature);
        }
        creatures.addAll(tempCreatures);
        //create plants
        for (int i = 0; i < 100; i++) {
            Plant plant = new Plant();
            plants.put(plant.getKey(), plant);
        }

        Creature.plants = plants;


    }

    public void update() {
        for (Creature creature : creatures) {
            creature.update();//it should update, everything todo update shuold do everything , just if i can
            if (plants.containsKey(creature.getKey())) {//inside the creature, creature->surface todo creature should do this
                plants.remove(creature.getKey());
                creature.increaseLife();
            }
        }
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor, focusX, focusY);
        canvas.translate(mPosX, mPosY);


        //  List<> dd = model.getCells();//it should be imutable todo create model class
        for (Cell cell : cells) {
            cell.draw(canvas);
        }

        for (Creature creature : creatures) {
            creature.draw(canvas);
        }
        for (Map.Entry<String, Plant> entry : plants.entrySet()) {
            entry.getValue().draw(canvas);
        }

//        drawWhite(canvas);
//        drawmyalgorithm(canvas);

        canvas.restore();
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
        int x = Const.SCREEN_WIDTH / 2;
        int y = Const.SCREEN_HEIGHT / 2;

        for (int i = 0; i < 100; i++) {
            int width = Creature.width * (i + 1);
            int height = Creature.height * (i + 1);
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
        int y = Const.SCREEN_HEIGHT / 2;
        int x = Const.SCREEN_WIDTH / 2;
        Paint paint;
        Direction nextDirection = LEFT;
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

        y = Const.SCREEN_HEIGHT / 2;
        x = Const.SCREEN_WIDTH / 2;
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.

        mScaleDetector.onTouchEvent(ev);

        final int action = ev.getAction();
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {

                final float x = ev.getX() / mScaleFactor;
                final float y = ev.getY() / mScaleFactor;
                mLastTouchX = x;
                mLastTouchY = y;
                mActivePointerId = ev.getPointerId(0);

                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final int pointerIndex = ev.findPointerIndex(mActivePointerId);
                final float x = ev.getX(pointerIndex) / mScaleFactor;
                final float y = ev.getY(pointerIndex) / mScaleFactor;

                // Only move if the ScaleGestureDetector isn't processing a gesture.
                if (!mScaleDetector.isInProgress()) {

                    final float dx = x - mLastTouchX;
                    final float dy = y - mLastTouchY;
                    mPosX += dx;
                    mPosY += dy;

                    invalidate();
                }

                mLastTouchX = x;
                mLastTouchY = y;

                break;
            }

            case MotionEvent.ACTION_UP:

            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {

                final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                final int pointerId = ev.getPointerId(pointerIndex);
                if (pointerId == mActivePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = ev.getX(newPointerIndex) / mScaleFactor;
                    mLastTouchY = ev.getY(newPointerIndex) / mScaleFactor;
                    mActivePointerId = ev.getPointerId(newPointerIndex);
                }
                break;
            }
        }
        return true;
    }


    private class ScaleListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {

            lastFocusX = -1;
            lastFocusY = -1;

            return true;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            focusX = detector.getFocusX();
            focusY = detector.getFocusY();

            if (lastFocusX == -1)
                lastFocusX = focusX;
            if (lastFocusY == -1)
                lastFocusY = focusY;

            mPosX += (focusX - lastFocusX);
            mPosY += (focusY - lastFocusY);
            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.2f, Math.min(mScaleFactor, 2.0f));

            lastFocusX = focusX;
            lastFocusY = focusY;

            invalidate();
            return true;
        }
    }
}