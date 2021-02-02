package com.blogspot.soyamr.lifesimulation.model.game_elements;

import android.graphics.Bitmap;

import com.blogspot.soyamr.lifesimulation.model.GameBitmaps;
import com.blogspot.soyamr.lifesimulation.model.Model;

import java.util.ArrayList;
import java.util.List;

public enum Type {
    //PLANTS
    CARROT {
        @Override
        public List<Type> getFoodList() {
            return null;
        }

        @Override
        public Bitmap getImage(GenderEnum genderEnum) {
            return GameBitmaps.carrotImg;
        }
    }, //*3
    OAT {
        @Override
        public List<Type> getFoodList() {
            return null;
        }

        @Override
        public Bitmap getImage(GenderEnum genderEnum) {
            return GameBitmaps.oatImg;
        }
    },    //*3
    APPLE {
        @Override
        public List<Type> getFoodList() {
            return null;
        }

        @Override
        public Bitmap getImage(GenderEnum genderEnum) {
            return GameBitmaps.appleImg;
        }
    },  //*3
    //HERBIVORE
    RABBIT {
        @Override
        public List<Type> getFoodList() {
            return List.of(Type.CARROT);
        }

        @Override
        public Bitmap getImage(GenderEnum genderEnum) {
            if (genderEnum == GenderEnum.MALE) {
                return GameBitmaps.rabbitImg;
            } else {
                return GameBitmaps.rabbitImgF;
            }
        }
    },     //carrot    *3
    MOUSE {
        @Override
        public List<Type> getFoodList() {
            return List.of(Type.OAT);
        }

        @Override
        public Bitmap getImage(GenderEnum genderEnum) {
            if (genderEnum == GenderEnum.MALE) {
                return GameBitmaps.mouseImg;
            } else {
                return GameBitmaps.mouseImgF;
            }
        }
    },      //oat       *2
    DEER {
        @Override
        public List<Type> getFoodList() {
            return List.of(Type.APPLE);
        }

        @Override
        public Bitmap getImage(GenderEnum genderEnum) {
            if (genderEnum == GenderEnum.MALE) {
                return GameBitmaps.deerImg;
            } else {
                return GameBitmaps.deerImgF;
            }
        }
    },       //apple     *2
    //CARNIVORE
    FOX {
        @Override
        public List<Type> getFoodList() {
            return List.of(Type.MOUSE, Type.RABBIT);
        }

        @Override
        public Bitmap getImage(GenderEnum genderEnum) {
            if (genderEnum == GenderEnum.MALE) {
                return GameBitmaps.foxImg;
            } else {
                return GameBitmaps.foxImgF;
            }
        }
    },        //mouse, rabbit     *0
    WOLF {
        @Override
        public List<Type> getFoodList() {
            return List.of(Type.DEER, Type.RACCOON);
        }

        @Override
        public Bitmap getImage(GenderEnum genderEnum) {
            if (genderEnum == GenderEnum.MALE) {
                return GameBitmaps.wolfImg;
            } else {
                return GameBitmaps.wolfImgF;
            }
        }
    },       //deer,  raccoon    *0
    LION {
        @Override
        public List<Type> getFoodList() {
            return List.of(Type.PIG);
        }

        @Override
        public Bitmap getImage(GenderEnum genderEnum) {
            if (genderEnum == GenderEnum.MALE) {
                return GameBitmaps.lionImg;
            } else {
                return GameBitmaps.lionImgF;
            }
        }
    },       //pig               *0
    //OMNIVORE
    BEAR {
        @Override
        public List<Type> getFoodList() {
            return List.of(Type.RABBIT, Type.OAT);
        }

        @Override
        public Bitmap getImage(GenderEnum genderEnum) {
            if (genderEnum == GenderEnum.MALE) {
                return GameBitmaps.bearImg;
            } else {
                return GameBitmaps.bearImgF;
            }
        }
    },       //rabbit, oat       *0
    PIG {
        @Override
        public List<Type> getFoodList() {
            return List.of(Type.DEER, Type.CARROT);
        }

        @Override
        public Bitmap getImage(GenderEnum genderEnum) {
            if (genderEnum == GenderEnum.MALE) {
                return GameBitmaps.pigImg;
            } else {
                return GameBitmaps.pigImgF;
            }
        }
    },        //deer, carrot      *2
    RACCOON {
        @Override
        public List<Type> getFoodList() {
            return List.of(Type.APPLE, Type.MOUSE);
        }

        @Override
        public Bitmap getImage(GenderEnum genderEnum) {
            if (genderEnum == GenderEnum.MALE) {
                return GameBitmaps.raccoonImg;
            } else {
                return GameBitmaps.raccoonImgF;
            }
        }
    },    //apple, mouse      *1
    //special types
    PERSON {
        @Override
        public List<Type> getFoodList() {
            return List.of(Type.PIG, Type.CARROT, Type.RABBIT, Type.OAT, Type.APPLE);
        }

        @Override
        public Bitmap getImage(GenderEnum genderEnum) {
            if (genderEnum == GenderEnum.MALE) {
                return GameBitmaps.personImg;
            } else {
                return GameBitmaps.personImgF;
            }
        }
    },     //pig, rabbit, carrot, oat, apple
    HOME {
        @Override
        public List<Type> getFoodList() {
            return null;
        }

        @Override
        public Bitmap getImage(GenderEnum genderEnum) {
            return GameBitmaps.homeImg;
        }
    },
    GRANARY {
        @Override
        public List<Type> getFoodList() {
            return null;
        }

        @Override
        public Bitmap getImage(GenderEnum genderEnum) {
            return GameBitmaps.granaryImg;
        }
    };


    public List<GameObject> getMeFromHere(Model model, int i, int j, GenderEnum genderEnum) {
        List<GameObject> result = new ArrayList<>();
        List<GameObject> objectSOnCell = model.getObjectResidingHere(i, j);

        for (GameObject currentObject : objectSOnCell)
            if (currentObject.isAlive && currentObject.type == this &&
                    (genderEnum == GenderEnum.BOTH || genderEnum == currentObject.genderEnum)) {
                result.add(currentObject);
            }

        return result;
    }

    public abstract List<Type> getFoodList();

    public abstract Bitmap getImage(GenderEnum genderEnum);

}
