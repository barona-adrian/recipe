package com.foodapi.model.search.response.instruction;

import java.util.List;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class AnalyzedInstructionsResponse {
    private String name;
    private List<Steps> steps;
}
