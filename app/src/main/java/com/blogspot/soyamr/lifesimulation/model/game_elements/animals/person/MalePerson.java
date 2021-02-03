package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.model.GameBitmaps;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.GoingToNearHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.NotSet;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.OneDirection;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.SearchPartner;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.WaitHome;

import java.util.List;

public class MalePerson extends Person {

    static Paint womenColor = new Paint();

    static {
        womenColor.setColor(Color.YELLOW);
        womenColor.setStyle(Paint.Style.STROKE);
        womenColor.setStrokeWidth(10);
    }

    public final int HOUSE_VISION = ANIMAL_FOOD_VISION_RANG * 2;
    public HusbandCallbacks wifeCallbacks;
    public State goingToNearHome = new GoingToNearHome();
    public State oneDirection = new OneDirection();
    public State waitHome = new WaitHome();
    public State noteSet = new NotSet();
    public State searchPartner = new SearchPartner();

    @Override
    public void addChild() {
        throw new RuntimeException("man should give birth");
    }

    public void buildHome(int x, int y) {
        homeSweetHome = new HomeSweetHome.Builder()
                .setCoordinates(x, y)
                .setModel(model)
                .build();
        wifeCallbacks.setWifeHome(homeSweetHome);
        setHomeRect();
    }

    @Override
    public void drawAdditionalInfo(Canvas canvas) {
        super.drawAdditionalInfo(canvas);
        if (isMarried) {
            canvas.drawBitmap(
                    GameBitmaps.crown, wifeCallbacks.getRect().left -
                            (float) Math.abs(wifeCallbacks.getRect().width() - GameBitmaps.crown.getWidth()) / 2,
                    wifeCallbacks.getRect().top - GameBitmaps.crown.getHeight(),
                    null);
            canvas.drawRect(wifeCallbacks.getRect().left - Const.CELL_WIDTH / 2.0F,
                    wifeCallbacks.getRect().top - Const.CELL_HEIGHT / 2F-30,
                    wifeCallbacks.getRect().right + Const.CELL_WIDTH / 2F,
                    wifeCallbacks.getRect().bottom + Const.CELL_HEIGHT / 2F
                    , womenColor);
            homeSweetHome.drawAdditionalInfo(canvas);
        }
    }

    @Override
    public State getOneDirectionState() {
        return oneDirection;
    }

    @Override
    public State getWaitHomeState() {
        return waitHome;
    }

    @Override
    public State getNotSetState() {
        return noteSet;
    }

    public void showMyHome() {
        model.addHome(homeSweetHome);
    }

    public static final class Builder extends Person.Builder<MalePerson, MalePerson.Builder> {
        protected MalePerson createObject() {
            return new MalePerson();
        }


        protected Builder thisObject() {
            setType(Type.PERSON);
            object.genderEnum = GenderEnum.MALE;
            setImage(object.type.getImage(object.genderEnum));
            setFoodTypeList(List.of(Type.PIG, Type.RABBIT));
            return this;
        }
    }
}
