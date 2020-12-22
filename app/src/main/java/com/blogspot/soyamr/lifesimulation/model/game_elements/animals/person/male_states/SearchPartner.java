package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.FemalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.MalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

import java.util.List;

public class SearchPartner implements State {
    @Override
    public void update(Person person) {
        MalePerson p = (MalePerson) person;
        List<GameObject> myPotentialWives = Utils.searchAroundAnimal(Person.ANIMAL_WOMEN_VISION_RANG,
                person.getX(), person.getY(), person.model, List.of(person.type), GenderEnum.FEMALE);

        for (GameObject female : myPotentialWives) {
            p.wifeCallbacks = ((FemalePerson) female).wannaBeInRelationShip();
            if (p.wifeCallbacks != null)
                break;
        }
        if (p.wifeCallbacks == null) {
            p.moveRandomly();
            p.currentState = p.oneDirection;
            return;
        }
        //here i have a wife
        p.isMarried = true;
        searchHome(p);//just return state
    }

    //toask should all methods launched from update return value to update, or they can take
    // decisions directly
    private void searchHome(MalePerson p) {
        //build home
        //1- search near homes. 2- take decision and build
        HomeSweetHome nearestHome = (HomeSweetHome) Utils.searchAroundAnimal(p.HOUSE_VISION, p.getX(), p.getY(),
                p.model, List.of(Type.HOME), GenderEnum.BOTH).stream().findFirst().orElse(null);

        if (nearestHome == null) {
            p.buildHome(p.getX(),p.getY());
            p.showMyHome();
            p.currentState = p.waitHome;
            return;
        }
        int newX;
        int newY;

        int i = 2;
        HomeSweetHome home;
        do {
            int rand = Utils.getRandom(0, Person.moveDirection.length);
            int[] direction = Person.moveDirection[rand];
            newX = nearestHome.getX() + direction[0] * Person.width * i;
            newY = nearestHome.getY() + direction[1] * Person.height * i;
            i *= 2;
            home = (HomeSweetHome) Type.HOME
                    .getMeFromHere(p.model, Utils.getRowIdx(newY), Utils.getColIdx(newX)
                            , GenderEnum.BOTH)
                    .stream().findFirst().orElse(null);
        } while (newX > Const.FIELD_WIDTH - Person.width
                || newY > Const.FIELD_HEIGHT - Person.height ||
                newX < 0 || newY < 0 || home != null);

        p.buildHome(newX, newY);
        p.currentState = p.goingToNearHome;
    }
}
