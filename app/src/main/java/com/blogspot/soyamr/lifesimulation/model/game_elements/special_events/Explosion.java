package com.blogspot.soyamr.lifesimulation.model.game_elements.special_events;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class Explosion extends GameObject {
    private int rowIndex = 0;
    private int colIndex = -1;
    final int rowCount;
    final int colCount;
    final Model model;
    private boolean finish = false;
    Bitmap image;
    final int width;
    final int height;


    public Explosion(Model model, Bitmap image, int x, int y, int rowC, int colC) {
        super(null,null);
        this.x = x;
        this.y = y;
        this.rowCount = rowC;
        this.colCount = colC;
        this.image = image;
        this.model = model;

        this.width = this.image.getWidth() / colCount;
        this.height = this.image.getHeight() / rowCount;
    }

    public void update() {
        this.colIndex++;

        if (this.colIndex >= this.colCount) {
            this.colIndex = 0;
            this.rowIndex++;

            if (this.rowIndex >= this.rowCount) {
                this.finish = true;
            }
        }
        model.removeObjectsInThisArea(x, y, x + width, y + height);
    }

    protected Bitmap createSubImageAt(int row, int col) {
        // createBitmap(bitmap, x, y, width, height).
        return Bitmap.createBitmap(image, col * width, row * height, width, height);
    }

    public void draw(Canvas canvas) {
        if (!finish) {
            Bitmap bitmap = this.createSubImageAt(rowIndex, colIndex);
            canvas.drawBitmap(bitmap, this.x, this.y, null);
        }
    }

    @Override
    public void makeMeFamous() {

    }

    @Override
    public void updateAdditionalInfoLocation(float mScaleFactor) {

    }

    @Override
    public void drawAdditionalInfo(Canvas canvas) {

    }

    @Override
    public int getMyColor() {
        return 0;
    }

    public boolean isFinish() {
        return finish;
    }
}
