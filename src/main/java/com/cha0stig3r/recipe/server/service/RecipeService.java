package com.cha0stig3r.recipe.server.service;

import com.cha0stig3r.recipe.server.model.Recipe;
import com.cha0stig3r.recipe.server.model.RecipeDto;
import com.cha0stig3r.recipe.server.model.RequestBody;
import com.cha0stig3r.recipe.server.repository.RecipeRepository;
import com.cha0stig3r.recipe.server.utility.GCSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;
import java.util.List;

import static com.cha0stig3r.recipe.server.utility.FormatRecipeUtility.dtoFormat;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepo;

    public String addRecipe(RequestBody request) throws Exception{
        Recipe recipe = new Recipe();
        recipe.setName(request.getName());
        recipe.setType(request.getType());
        recipe.setDescription(request.getDescription());
        recipe.setImgLocation(save(request.getImage().getBytes(), request.getImage().getOriginalFilename()));
        recipe.setDate(new Date());
        recipe.setIngredients(request.getIngredients());
        recipe.setDirections(request.getDirections());
        recipeRepo.save(recipe);
        return "Recipe Added";
    }

    public String save(byte [] content, String imageName) throws Exception{
        String imgName =  GCSUtil.imgUploader(imageName, content);


        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/url/")
                .path(imgName)
                .toUriString();
    }

    public List<RecipeDto> getRecipes(){
        List<Recipe> recipes = recipeRepo.findAll();
        return dtoFormat(recipes);
    }

    public List<RecipeDto> getRecentType(String type) {
        List<Recipe> recipes = recipeRepo.findRecipesByTypeIgnoreCaseOrderByDate(type);
        return dtoFormat(recipes);
    }

    public List<RecipeDto> getSearchedName(String query) {
        var list = recipeRepo.findByNameContainingIgnoreCase(query);
        return dtoFormat(list);
    }

    public RecipeDto getRecipeById(Long id) {
        var recipe = recipeRepo.findById(id);
        return dtoFormat(recipe.get());
    }

    public List<RecipeDto> getType(String type) {
        var types = recipeRepo.findRecipeByTypeIgnoreCase(type);
        return dtoFormat(types);
    }

    public ByteArrayResource getImage(String name) {
        return GCSUtil.downloadObjectIntoMemory(name);
    }
}
