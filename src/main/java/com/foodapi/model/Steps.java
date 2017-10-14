package com.foodapi.model;

import java.util.List;

@lombok.Getter
@lombok.Setter
public class Steps {

    String number;
    String step;
    List<Ingredients> ingredients;
    List<Equipment> equipment;


}
