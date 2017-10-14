package com.foodapi.model;

import java.util.List;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Entity {

    private List<FindByIngredientsResponse> findByIngredientsResponses;

}
