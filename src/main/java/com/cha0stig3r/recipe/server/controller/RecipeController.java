package com.cha0stig3r.recipe.server.controller;

import com.cha0stig3r.recipe.server.model.RecipeDto;
import com.cha0stig3r.recipe.server.model.RequestBody;
import com.cha0stig3r.recipe.server.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class RecipeController {

    @Autowired
    private RecipeService service;

    @PostMapping(value = "/add-recipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addRecipe(RequestBody request) throws Exception {
        return service.addRecipe(request);
    }

    @GetMapping(value = "/url/{url}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ByteArrayResource getImg(@PathVariable String url){
        return service.getImage(url);
    }

    @GetMapping("/get-recipe/{id}")
    public RecipeDto getRecipe(@PathVariable Long id){
        return service.getRecipeById(id);
    }

    @GetMapping("/search-recipe")
    public List<RecipeDto> getSearched(@RequestParam String query){
        return service.getSearchedName(query);
    }

    @GetMapping("/get-recipes/All")
    public List<RecipeDto> getRecipes(){
        return service.getRecipes();
    }

    @GetMapping(value = {"/get-recipes/recent/{type}", "/get-recipes/recent/{type}/{amount}"})
    public List<RecipeDto> getRecentType(@PathVariable String type, @PathVariable(required = false) Optional<Integer> amount){
        return amount.map(integer -> service.getRecentType(type).subList(0, integer)).orElseGet(() -> service.getRecentType(type));
    }

    @GetMapping("/get-recipes/{type}")
    public List<RecipeDto> getTypes(@PathVariable String type){
        return  service.getType(type);
    }

}
