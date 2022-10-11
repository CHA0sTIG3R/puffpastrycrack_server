package com.cha0stig3r.recipe.server.service;

import com.cha0stig3r.recipe.server.model.Recipe;
import com.cha0stig3r.recipe.server.model.RecipeDto;
import com.cha0stig3r.recipe.server.model.RequestBody;
import com.cha0stig3r.recipe.server.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import static com.cha0stig3r.recipe.server.utility.FormatRecipeUtility.dtoFormat;

@Service
public class RecipeService {

    public String RESOURCES_DIR = new ClassPathResource("src/main/resources/images/").getPath();

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
        String imgName =  new Date().getTime() + "-" +  imageName;
        Path imageFile = Paths.get(RESOURCES_DIR +imgName);
        Files.createDirectories(imageFile.getParent());

        Files.write(imageFile, content);

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

    public List<RecipeDto> getByType(String type) {
        List<Recipe> recipes = recipeRepo.findByType(type);
        return dtoFormat(recipes);
    }

    public List<RecipeDto> getSearchedName(String query) {
        var list = recipeRepo.findByNameContainingIgnoreCase(query);
        return dtoFormat(list);
    }
}
