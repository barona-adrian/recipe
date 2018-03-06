package com.foodapi.model.search.request.instructions;

@lombok.Getter
@lombok.Setter
public class AnalyzedInstructionsRequest {
    private String recipeID;
    final private boolean stepBreakdown = true;
}
