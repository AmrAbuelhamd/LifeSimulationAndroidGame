package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject.ANIMAL_WOMEN_VISION_RANG;

public class Male extends Gender {
    private List<GameObject> myCrushes;

    public Male(Animal animal) {
        super(animal);
        myCrushes = new LinkedList<>();
    }

    //todo if she is dead then delete her
    @Override
    public Animal.NextMove takeRequiredActions() {
        return Animal.NextMove.TO_LOVE;
    }

    @Override
    public boolean wannaBeInRelationShip(Type type) {
        return false;
    }

    @Override
    public Animal.NextMove searchForPartner() {
        Animal.NextMove result;
        if ((result = animal.worthSearching()) != Animal.NextMove.NOT_SET) {
            return result;
        }
        if (myCrushes.isEmpty())
            myCrushes = Utils.searchAroundAnimal(ANIMAL_WOMEN_VISION_RANG, animal.getX(), animal.getY(),
                    animal.model, List.of(animal.type),
                    GenderEnum.FEMALE);
        if (myCrushes.isEmpty()) {
            return animal.moveToOneDirectionSetUp();
        }
        //make sure that crushes that i kept in my list still available
        //if not delete it
        Animal target = getNextTarget2();

        if (target == null)
            return Animal.NextMove.MOVE_RANDOMLY;
        //if true this means she already engaged
        //no broken hearts in my game :)
        inRelation = true;
        animal.paint.setStyle(Paint.Style.STROKE);
        animal.paint.setStrokeWidth(10);
        myLove = target;
        myCrushes.remove(myLove);
        return Animal.NextMove.TO_LOVE;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(animal.rect, animal.paint);
    }

    @Override
    public void setRect() {
        animal.rect.set(animal.getX(), animal.getY(), animal.getX() + GameObject.width,
                animal.getY() + GameObject.height);
    }

    Animal getNextTarget2() {
        ListIterator<GameObject> iter = myCrushes.listIterator();
        while (iter.hasNext()) {
            Animal current = (Animal) iter.next();
            if (current.isAlive && current.wannaBeInRelationship(animal.type)) {
                return current;
            } else {
                iter.remove();
            }
        }
        return null;
    }

    @Override
    public void doCeremony() {
        myLove.doCermony();
        myLove = null;
        inRelation = false;
        animal.paint.setStyle(Paint.Style.FILL);
        setIdoNotWant();
    }

    private boolean arrived() {
        boolean arrivedX = false;
        boolean arrivedY = false;
        if (myLove.getX() == animal.getX())
            arrivedX = true;
        if (myLove.getY() == animal.getY())
            arrivedY = true;
        return arrivedX && arrivedY;
    }
}
