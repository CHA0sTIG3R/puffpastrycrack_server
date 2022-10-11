package com.cha0stig3r.recipe.server.repository;

import com.cha0stig3r.recipe.server.model.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface RecipeRepository extends ListCrudRepository<Recipe, Long> {
    List<Recipe> findByType(String type);

    List<Recipe> findByNameContainingIgnoreCase(String query);
}
