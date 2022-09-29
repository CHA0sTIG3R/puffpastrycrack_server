package com.cha0stig3r.recipe.server.repository;

import com.cha0stig3r.recipe.server.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    Recipe findRecipesById(Integer id);
    Recipe findRecipesByName(String name);
    Recipe findRecipesByType(String type);
    Recipe findRecipesByDate(Date date);
}
