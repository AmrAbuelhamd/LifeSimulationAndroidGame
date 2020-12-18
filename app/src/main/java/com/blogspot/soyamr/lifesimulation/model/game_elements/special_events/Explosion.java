package com.blogspot.soyamr.lifesimulation.model.game_elements.special_events;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class Explosion extends GameObject {
    final int rowCount;
    final int colCount;
    final Model model;
    final int width;
    final int height;
    Bitmap image;
    private int rowIndex = 0;
    private int colIndex = -1;
    private boolean finish = false;


    public Explosion(Model model, Bitmap image, int x, int y, int rowC, int colC) {
        super(null, null);
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
        ++this.colIndex;
        if (this.colIndex >= this.colCount) {
            this.colIndex = 0;
            this.rowIndex++;
            model.removeObjectsInThisArea(x, y, x + width, y + height);
            if (this.rowIndex >= this.rowCount) {
                this.finish = true;
            }
        }
    }

    protected Bitmap createSubImageAt(int row, int col) {
        // createBitmap(bitmap, x, y, width, height).
        if (col == -1)
            col = 0;
        return Bitmap.createBitmap(image, Math.min(col * width, image.getWidth()),
                Math.min(row * height, image.getHeight()), width, height);
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
