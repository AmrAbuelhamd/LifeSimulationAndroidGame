package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GroundType;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.FemalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.MalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

import java.util.List;

public class SearchPartner implements State {
    GroundType nextCellType;

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

    private void searchHome(MalePerson p) {
        //build home
        //1- search near homes. 2- take decision and build
        HomeSweetHome nearestHome = (HomeSweetHome) Utils.searchAroundAnimal(p.HOUSE_VISION, p.getX(), p.getY(),
                p.model, List.of(Type.HOME), GenderEnum.BOTH).stream().findFirst().orElse(null);

        if (nearestHome == null) {
            p.buildHome(p.getX(), p.getY());
            p.showMyHome();
            p.currentState = p.waitHome;
            return;
        }
        HomeSweetHome home;
        int newX = 0;
        int newY = 0;//i will build new one here
        boolean found = false;
        for (int i = 2; i < 100 && !found; i += 4) {

            int r = Utils.getRandom(0, Person.moveDirection.length);
            int[] dir = Person.moveDirection[r];
            newX = nearestHome.getX() + dir[0] * Person.width * i;
            newY = nearestHome.getY() + dir[1] * Person.height * i;

            if (newX < 0) {
                newX = 0;
            } else if (newX > Const.FIELD_WIDTH - Person.width) {
                newX = Const.FIELD_WIDTH - Person.width;
            }
            if (newY < 0) {
                newY = 0;
            } else if (newY > Const.FIELD_HEIGHT - Person.height) {
                newY = Const.FIELD_HEIGHT - Person.height;
            }
            home = (HomeSweetHome) Type.HOME
                    .getMeFromHere(p.model, Utils.getRowIdx(newY), Utils.getColIdx(newX)
                            , GenderEnum.BOTH)
                    .stream().findFirst().orElse(null);
            if (home == null) {
                if (!p.model.noGranaryHere(newX, newY, Person.width * 2, Person.height * 2)) {
                    nextCellType = p.model.getNextCellType(newX, newY);
                    if (nextCellType != GroundType.WATER && nextCellType != GroundType.VOLCANO)
                        found = true;
                }
            }

        }
        if (found) {
            p.buildHome(newX, newY);
            p.currentState = p.goingToNearHome;
        } else {
            throw new RuntimeException("no free space found");
        }
    }

    @Override
    public String getStateName() {
        return "Search Partner";
    }
}
