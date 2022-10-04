package com.cha0stig3r.recipe.server.repository;

import com.cha0stig3r.recipe.server.model.Recipe;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends ListCrudRepository<Recipe, Long> {
}
