package com.blogspot.soyamr.lifesimulation;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
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
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread gameThread;

    private final List<Cell> cells = new CopyOnWriteArrayList<>();
    private final List<Creature> creatures = new CopyOnWriteArrayList<>();
    private final Map<String, Plant> plants = new ConcurrentHashMap<>();

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
        for (int i = 0; i < CONST.M; i++) {
            for (int j = 0; j < CONST.N; j++) {
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
        for (int i = 0; i < 50; i++) {
            Plant plant = new Plant();
            plants.put(plant.getKey(), plant);
        }

        Creature.plants = plants;

        Runnable addMorePlants = () -> {
            Plant plant = new Plant();
            plants.put(plant.getKey(), plant);
        };

        Runnable reduceCreatureLife = () -> {
            creatures.forEach(creature -> {
                final boolean isDead = creature.reduceLife();
                if (isDead) {
                    Log.i("one died", "bad!");
                    creatures.remove(creature);
                }
            });
        };
        Runnable updateInfo = () -> {
            Log.i("number of creatures: ", " " + creatures.size());
            Log.i("number of plants: ", " " + plants.size());
            Log.i("----------------", " ------------------------");
        };


        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(addMorePlants, 0, 5, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(reduceCreatureLife, 0, 10, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(updateInfo, 0, 10, TimeUnit.SECONDS);
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


        canvas.restore();
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
        this.gameThread.setRunning(false);
        while (true) {
            // Parent thread must wait until the end of GameThread.
            try {
                this.gameThread.join();
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resume() {
        this.gameThread = new GameThread(this, this.getHolder());
        this.gameThread.setRunning(true);
        this.gameThread.start();
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