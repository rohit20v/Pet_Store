package store.server;

public enum FoodAndAccessories {
    COLLAR("accessory"),
    TOY("accessory"),
    BRUSH("accessory"),
    NAME_TAG("accessory"),
    FISH_TANK("accessory"),
    COSTUME("accessory"),
    WET_FOOD("food"),
    DRY_FOOD("food"),
    FISH_FOOD("food"),
    REPTILE_FOOD("food"),
    BIRD_FOOD("food");

    private final String stuff;

    FoodAndAccessories(String stuff) {
        this.stuff = stuff;
    }


    public String getStuff() {
        return stuff;
    }


}
