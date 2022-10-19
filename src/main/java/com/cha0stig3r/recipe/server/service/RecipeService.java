package com.cha0stig3r.recipe.server.service;

import com.cha0stig3r.recipe.server.model.Recipe;
import com.cha0stig3r.recipe.server.model.RecipeDto;
import com.cha0stig3r.recipe.server.model.RequestDto;
import com.cha0stig3r.recipe.server.model.RequestUpdate;
import com.cha0stig3r.recipe.server.repository.RecipeRepository;
import com.cha0stig3r.recipe.server.utility.GCSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import static com.cha0stig3r.recipe.server.utility.FormatRecipeUtility.dtoFormat;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepo;

    public String addRecipe(RequestDto request) throws Exception{
        Recipe recipe = new Recipe();
        saveToDB(request, recipe);
        return "Recipe Added";
    }

    @Transactional
    public String updateRecipe(Long id, RequestUpdate request){
        recipeRepo
                .findById(id)
                .ifPresent(recipe -> {
                    try {
                        var item = new Recipe();
                        item.setId(recipe.getId());
                        item.setName(request.getName());
                        item.setType(request.getType());
                        item.setDescription(request.getDescription());
                        item.setImgLocation(recipe.getImgLocation());
                        item.setDate(new Date());
                        item.setIngredients(request.getIngredients());
                        item.setDirections(request.getDirections());
                        recipeRepo.save(item);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return "Recipe Updated";
    }

    @Transactional
    public String updateRecipe(Long id, RequestDto request){
        recipeRepo
                .findById(id)
                .ifPresent(recipe -> {
                    try {
                        var item = new Recipe();
                        item.setId(recipe.getId());
                        item.setName(request.getName());
                        item.setType(request.getType());
                        item.setDescription(request.getDescription());
                        item.setImgLocation(recipe.getImgLocation());
                        item.setDate(new Date());
                        item.setIngredients(request.getIngredients());
                        item.setDirections(request.getDirections());
                        update(request.getImage().getBytes(), imgName(recipe.getImgLocation()));
                        recipeRepo.save(item);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return "Recipe Updated";
    }

    private String imgName(String location) throws URISyntaxException {
        return new File(new URI(location).getPath()).getName();
    }

    @Transactional
    public String deleteRecipe(Long id) throws URISyntaxException {
        var img = new File(new URI(recipeRepo.findById(id).map(Recipe::getImgLocation).get()).getPath()).getName();
        recipeRepo.deleteById(id);
        GCSUtil.deleteImage(img);
        return "Recipe Deleted";
    }

    private void saveToDB(RequestDto request, Recipe recipe) throws Exception {
        recipe.setName(request.getName());
        recipe.setType(request.getType());
        recipe.setDescription(request.getDescription());
        recipe.setImgLocation(save(request.getImage().getBytes(), request.getImage().getOriginalFilename()));
        recipe.setDate(new Date());
        recipe.setIngredients(request.getIngredients());
        recipe.setDirections(request.getDirections());
        recipeRepo.save(recipe);
    }



    private String save(byte [] content, String imageName) throws Exception{
        String imgName =  GCSUtil.imgUploader(imageName, content);


        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/url/")
                .path(imgName)
                .toUriString();
    }

    private void update(byte [] content, String imageName) throws Exception{
        GCSUtil.updateImage(imageName, content);
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
        if (recipe.isEmpty()){
            return new RecipeDto(null, null, null, null, null, null, null);
        }
        return dtoFormat(recipe.get());
    }

    public List<RecipeDto> getType(String type) {
        var types = recipeRepo.findRecipeByTypeIgnoreCase(type);
        return dtoFormat(types);
    }

    public ByteArrayResource getImage(String name) {
        return GCSUtil.downloadImage(name);
    }
}
