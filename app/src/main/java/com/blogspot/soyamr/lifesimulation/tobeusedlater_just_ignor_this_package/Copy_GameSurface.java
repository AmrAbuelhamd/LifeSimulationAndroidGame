package com.blogspot.soyamr.lifesimulation.tobeusedlater_just_ignor_this_package;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.blogspot.soyamr.lifesimulation.CONST;
import com.blogspot.soyamr.lifesimulation.Cell;
import com.blogspot.soyamr.lifesimulation.Creature;
import com.blogspot.soyamr.lifesimulation.GameThread;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Copy_GameSurface extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread gameThread;


    //    private final List<ChibiCharacter> chibiList = new ArrayList<>();
//    private final List<Explosion> explosionList = new ArrayList<>();

    private final Map<Integer, Cell> cells = new LinkedHashMap<Integer, Cell>();
    private final List<Creature> creatures = new ArrayList<>();

//    private static final int MAX_STREAMS = 100;
//    private int soundIdExplosion;
//    private int soundIdBackground;
//
//    private boolean soundPoolLoaded;
//    private SoundPool soundPool;

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

    public Context context;

    public Copy_GameSurface(Context context) {
        super(context);

        // Make Game Surface focusable so it can handle events.
        this.setFocusable(true);

        // Set callback.
        this.getHolder().addCallback(this);

        this.context = context;
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());

//        this.initSoundPool();
    }

//    private void initSoundPool() {
//
//        AudioAttributes audioAttrib = new AudioAttributes.Builder()
//                .setUsage(AudioAttributes.USAGE_GAME)
//                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                .build();
//
//        SoundPool.Builder builder = new SoundPool.Builder();
//        builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);
//
//        this.soundPool = builder.build();
//
//        // When SoundPool load complete.
//        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
//            @Override
//            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//                soundPoolLoaded = true;
//
//                // Playing background sound.
//                playSoundBackground();
//            }
//        });
//
////        // Load the sound background.mp3 into SoundPool
////        this.soundIdBackground= this.soundPool.load(this.getContext(), R.raw.background,1);
////
////        // Load the sound explosion.wav into SoundPool
////        this.soundIdExplosion = this.soundPool.load(this.getContext(), R.raw.explosion,1);
//
//
//    }
//
//    public void playSoundExplosion() {
//        if (this.soundPoolLoaded) {
//            float leftVolumn = 0.8f;
//            float rightVolumn = 0.8f;
//            // Play sound explosion.wav
//            int streamId = this.soundPool.play(this.soundIdExplosion, leftVolumn, rightVolumn, 1, 0, 1f);
//        }
//    }
//
//    public void playSoundBackground() {
//        if (this.soundPoolLoaded) {
//            float leftVolumn = 0.8f;
//            float rightVolumn = 0.8f;
//            // Play sound background.mp3
//            int streamId = this.soundPool.play(this.soundIdBackground, leftVolumn, rightVolumn, 1, -1, 1f);
//        }
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
////        if (event.getAction() == MotionEvent.ACTION_DOWN) {
////
////            int x = (int) event.getX();
////            int y = (int) event.getY();
////
////            Iterator<ChibiCharacter> iterator = this.chibiList.iterator();
////            while (iterator.hasNext()) {
////                ChibiCharacter chibi = iterator.next();
////                if (chibi.getX() < x && x < chibi.getX() + chibi.getWidth()
////                        && chibi.getY() < y && y < chibi.getY() + chibi.getHeight()) {
////                    // Remove the current element from the iterator and the list.
////                    iterator.remove();
////
////                    // Create Explosion object.
////                    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.explosion);
////                    Explosion explosion = new Explosion(this, bitmap, chibi.getX(), chibi.getY());
////
////                    this.explosionList.add(explosion);
////                }
////            }
////
////
////            for (ChibiCharacter chibi : chibiList) {
////                int movingVectorX = x - chibi.getX();
////                int movingVectorY = y - chibi.getY();
////                chibi.setMovingVector(movingVectorX, movingVectorY);
////            }
////            return true;
////        }
//        return false;
//    }

    public void update() {
//        for (Explosion explosion : this.explosionList) {
//            explosion.update();
//        }
//        for (ChibiCharacter chibi : chibiList) {
//            chibi.update();
//        }
        for (Creature creature : creatures) {
            creature.update();
        }

//        Iterator<Explosion> iterator = this.explosionList.iterator();
//        while (iterator.hasNext()) {
//            Explosion explosion = iterator.next();
//
//            if (explosion.isFinish()) {
//                // If explosion finish, Remove the current element from the iterator & list.
//                iterator.remove();
//            }
//        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

//        for (ChibiCharacter chibi : chibiList) {
//            chibi.draw(canvas);
//        }
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor, focusX, focusY);
        canvas.translate(mPosX, mPosY);

//        drawHeart(canvas);
//        drawName(canvas);

        for (Map.Entry<Integer, Cell> entry : cells.entrySet()) {
            entry.getValue().draw(canvas);
        }

        for (Creature creature : creatures) {
            creature.draw(canvas);
        }

        canvas.restore();
//        for (Explosion explosion : this.explosionList) {
//            explosion.draw(canvas);
//        }

    }

    private void drawName(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(500);
        canvas.drawText("Amr", (float) (CONST.SCREEN_WIDTH / 3.0), (float) (CONST.SCREEN_HEIGHT / 2.0), paint);
    }

    private void drawHeart(Canvas canvas) {
        Path path;

        Paint paint;
        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        float width = CONST.SCREEN_WIDTH;
        float height = CONST.SCREEN_HEIGHT;


        // Starting point
        path.moveTo(width / 2, height / 5);

        // Upper left path
        path.cubicTo(5 * width / 14, 0,
                0, height / 15,
                width / 28, 2 * height / 5);

        // Lower left path
        path.cubicTo(width / 14, 2 * height / 3,
                3 * width / 7, 5 * height / 6,
                width / 2, height);

        // Lower right path
        path.cubicTo(4 * width / 7, 5 * height / 6,
                13 * width / 14, 2 * height / 3,
                27 * width / 28, 2 * height / 5);

        // Upper right path
        path.cubicTo(width, height / 15,
                9 * width / 14, 0,
                width / 2, height / 5);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        Bitmap chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.chibi1);
//        ChibiCharacter chibi1 = new ChibiCharacter(this, chibiBitmap1, 100, 50);
//
//        Bitmap chibiBitmap2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.chibi2);
//        ChibiCharacter chibi2 = new ChibiCharacter(this, chibiBitmap2, 300, 150);
        //create creatures
       /// Cell.defineColors();
        for (int i = 0; i < 100; i++) {
//            Copy_Creature creature = new Copy_Creature();
         //   creatures.add(creature);
        }
        //create cells
        int ctr = 0;
        for (int i = 0; i < CONST.M; i++) {
            for (int j = 0; j < CONST.N; j++) {
                Cell cell = new Cell(i, j);
                cells.put(ctr, cell);

            }
        }
//        android.view.ViewGroup.LayoutParams lp = this.getLayoutParams();
//        lp.width = Const.SCREEN_WIDTH; // required width
//        lp.height = Const.SCREEN_HEIGHT; // required height
//        this.setLayoutParams(lp);
//        this.chibiList.add(chibi1);
//        this.chibiList.add(chibi2);
        resume();
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        pause();
    }

    public void pause() {
        this.gameThread.setRunning(false);
        while (true) {
            // Papubrent thread must wait until the end of GameThread.
            try {
                this.gameThread.join();
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resume() {
        //this.gameThread = new GameThread(this, this.getHolder());
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        //updateScalingParameters(ev);
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

    private void updateScalingParameters(MotionEvent ev) {

    }

    private class ScaleListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {

            // float x = detector.getFocusX();
            // float y = detector.getFocusY();

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
            Log.v("Hi Zoom", "Factor:" + mScaleFactor);
            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.2f, Math.min(mScaleFactor, 2.0f));

            lastFocusX = focusX;
            lastFocusY = focusY;

            invalidate();
            return true;
        }
    }
}