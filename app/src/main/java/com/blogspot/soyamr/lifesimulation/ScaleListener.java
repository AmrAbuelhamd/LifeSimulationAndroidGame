package com.blogspot.soyamr.lifesimulation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

public class ScaleListener extends
        ScaleGestureDetector.SimpleOnScaleGestureListener {

    private static final int INVALID_POINTER_ID = -1;

    public float mPosX;
    public float mPosY;

    public float mLastTouchX;
    public float mLastTouchY;
    public int mActivePointerId = INVALID_POINTER_ID;

    private final ScaleGestureDetector mScaleDetector;
    public float mScaleFactor = 1.f;

    public float focusX;
    public float focusY;

    private float lastFocusX = -1;
    private float lastFocusY = -1;

    private final GameSurface gameSurface;

    ScaleListener(GameSurface gameSurface) {
        this.gameSurface = gameSurface;
        mScaleDetector = new ScaleGestureDetector(gameSurface.getContext(), this);
    }

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

        gameSurface.invalidate();
        return true;
    }

    @SuppressLint("ClickableViewAccessibility")
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

                    gameSurface.invalidate();
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
}
