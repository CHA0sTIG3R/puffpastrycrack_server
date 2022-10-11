package com.cha0stig3r.recipe.server.utility;

import com.cha0stig3r.recipe.server.model.Recipe;
import com.cha0stig3r.recipe.server.model.RecipeDto;

import java.util.List;

public class FormatRecipeUtility {
    public static List<RecipeDto> dtoFormat(List<Recipe> list) {
        return list.stream()
                .map(e -> new RecipeDto(e.getId(),
                        e.getName(),
                        e.getType(),
                        e.getDescription(),
                        e.getImgLocation(),
                        e.getIngredients(),
                        e.getDirections())).toList();
    }
}
