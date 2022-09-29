package com.cha0stig3r.recipe.server.controller;

import com.cha0stig3r.recipe.server.model.Recipe;
import com.cha0stig3r.recipe.server.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepo;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add-recipe")
    public String addRecipe(@RequestParam String name,
                            @RequestParam String type,
                            @RequestParam List<String> ingredients,
                            @RequestParam List<String> directions)
    {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setType(type);
        recipe.setDate(new Date());
        recipe.setIngredients(ingredients);
        recipe.setDirections(directions);
        recipeRepo.save(recipe);
        return "Recipe Added";
    }

    @GetMapping("/list")
    public Iterable<Recipe> getRecipes(){
        return recipeRepo.findAll();
    }

    @GetMapping("/date")
    public Iterable<Recipe> getRecipesByDate(){
        List<Recipe> list = new ArrayList<>();
        list.add(recipeRepo.findRecipesByDate(new Date()));
        return list;
    }
}
