package com.deliciousapp.onetime.findByID.model.output;

import java.util.List;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Steps {

    private int number;
    private Length length;
    private List<Ingredients> ingredients;
    private List<Equipment> equipment;
    private String step;

}
