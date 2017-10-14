package com.foodapi.model;

import java.util.List;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class FindByIDResponse {
    String name;
    List<Steps> steps;
}
