package com.blogspot.soyamr.lifesimulation;

import android.content.Context;
import android.graphics.Canvas;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.blogspot.soyamr.lifesimulation.tobeusedlater.ChibiCharacter;
import com.blogspot.soyamr.lifesimulation.tobeusedlater.Explosion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread gameThread;

//    private final List<ChibiCharacter> chibiList = new ArrayList<>();
//    private final List<Explosion> explosionList = new ArrayList<>();

    private final Map<String, Cell> cells = new LinkedHashMap<>();
    private final List<Creature> creatures = new ArrayList<>();

//    private static final int MAX_STREAMS = 100;
//    private int soundIdExplosion;
//    private int soundIdBackground;

//    private boolean soundPoolLoaded;
//    private SoundPool soundPool;

    private ScaleGestureDetector SGD;
    private Context context;
    private boolean isSingleTouch;
    private float width=0, height = 0;
    private float scale = 1f;
    private float minScale = 1f;
    private float maxScale = 5f;
    int left, top, right, bottom;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (width == 0 && height == 0) {
            width = 5000;
            height = 5000;
            this.left = left;
            this.right = right;
            this.top = top;
            this.bottom = bottom;
        }

    }

    private void init() {
        setOnTouchListener(new MyTouchListeners());
        SGD = new ScaleGestureDetector(context, new ScaleListener());
        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

            }
        });
    }

    public GameSurface(Context context) {
        super(context);
        this.context = context;
        // Make Game Surface focusable so it can handle events.
        this.setFocusable(true);

        // Set callback.
        this.getHolder().addCallback(this);
        init();


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
//
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            scrollTo(505, 0);
//            return true;
//        }
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

        for (Map.Entry<String, Cell> entry : cells.entrySet()) {
            entry.getValue().draw(canvas);
        }

        for (Creature creature : creatures) {
            creature.draw(canvas);
        }

//        for (Explosion explosion : this.explosionList) {
//            explosion.draw(canvas);
//        }

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        Bitmap chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.chibi1);
//        ChibiCharacter chibi1 = new ChibiCharacter(this, chibiBitmap1, 100, 50);
//
//        Bitmap chibiBitmap2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.chibi2);
//        ChibiCharacter chibi2 = new ChibiCharacter(this, chibiBitmap2, 300, 150);
        Cell.defineColors();
        for (int i = 0; i < 20; i++) {
            Creature creature = new Creature();
            creatures.add(creature);
        }
        for (int i = 0; i < Const.M; i++) {
            for (int j = 0; j < Const.N; j++) {
                Cell cell = new Cell(i, j);
                String key = cell.getKey();
                cells.put(key, cell);
            }
        }


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
        this.gameThread = new GameThread(this, this.getHolder());
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    private class MyTouchListeners implements View.OnTouchListener {

        float dX, dY;

        MyTouchListeners() {
            super();
        }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            SGD.onTouchEvent(event);
            if (event.getPointerCount() > 1) {
                isSingleTouch = false;
            } else {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    isSingleTouch = true;
                }
            }
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    dX = GameSurface.this.getX() - event.getRawX();
                    dY = GameSurface.this.getY() - event.getRawY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (isSingleTouch) {
                        GameSurface.this.animate()
                                .x(event.getRawX() + dX)
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();
                        checkDimension(GameSurface.this);
                    }
                    break;
                default:
                    return true;
            }
            return true;
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Log.e("onGlobalLayout: ", scale + " " + width + " " + height);
            scale *= detector.getScaleFactor();
            scale = Math.max(minScale, Math.min(scale, maxScale));

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    (int) (width * scale), (int) (height * scale));
            GameSurface.this.setLayoutParams(params);
            checkDimension(GameSurface.this);
            return true;
        }
    }

    private void checkDimension(View vi) {
        if (vi.getX() > left) {
            vi.animate()
                    .x(left)
                    .y(vi.getY())
                    .setDuration(0)
                    .start();
        }

        if ((vi.getWidth() + vi.getX()) < right) {
            vi.animate()
                    .x(right - vi.getWidth())
                    .y(vi.getY())
                    .setDuration(0)
                    .start();
        }

        if (vi.getY() > top) {
            vi.animate()
                    .x(vi.getX())
                    .y(top)
                    .setDuration(0)
                    .start();
        }

        if ((vi.getHeight() + vi.getY()) < bottom) {
            vi.animate()
                    .x(vi.getX())
                    .y(bottom - vi.getHeight())
                    .setDuration(0)
                    .start();
        }
    }
}