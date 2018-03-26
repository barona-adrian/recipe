package com.deliciousapp.onetime.findByID.model.input;

@lombok.Getter
@lombok.Setter
public class AnalyzedInstructionsRequest {
    private String recipeID;
    final private boolean stepBreakdown = true;
}
