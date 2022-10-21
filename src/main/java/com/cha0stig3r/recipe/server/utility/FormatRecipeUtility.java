package com.cha0stig3r.recipe.server.utility;

import com.cha0stig3r.recipe.server.model.Recipe;
import com.cha0stig3r.recipe.server.model.RecipeDto;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FormatRecipeUtility {
    public static List<RecipeDto> dtoFormat(@NotNull List<Recipe> list) {
        return list.stream()
                .map(e -> new RecipeDto(e.getId(),
                        e.getName(),
                        e.getType(),
                        e.getDescription(),
                        e.getImgLocation(),
                        e.getIngredients(),
                        e.getDirections())).toList();
    }

    public static RecipeDto dtoFormat(@NotNull Recipe list) {
        return new RecipeDto(list.getId(),
                        list.getName(),
                        list.getType(),
                        list.getDescription(),
                        list.getImgLocation(),
                        list.getIngredients(),
                        list.getDirections());
    }

}
