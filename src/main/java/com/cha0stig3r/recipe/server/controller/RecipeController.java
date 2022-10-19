package com.cha0stig3r.recipe.server.controller;

import com.cha0stig3r.recipe.server.model.RecipeDto;
import com.cha0stig3r.recipe.server.model.RequestDto;
import com.cha0stig3r.recipe.server.model.RequestUpdate;
import com.cha0stig3r.recipe.server.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class RecipeController {

    @Autowired
    private RecipeService service;

    @PostMapping(value = "/add-recipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addRecipe(RequestDto request) throws Exception {
        return service.addRecipe(request);
    }

    @PostMapping(value = "/update-recipe/img/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateRecipe(@PathVariable Long id, RequestDto request) throws Exception {
        return service.updateRecipe(id, request);
    }

    @PostMapping(value = "/update-recipe/{id}")
    public String updateRecipe(@PathVariable Long id, RequestUpdate request){
        return service.updateRecipe(id, request);
    }

    @DeleteMapping("/delete-recipe/{id}")
    public String deleteRecipe(@PathVariable Long id) throws URISyntaxException {
        return service.deleteRecipe(id);
    }

    @GetMapping(value = "/url/{url}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
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

    @GetMapping("/get-recipes/recent/{type}")
    public List<RecipeDto> getRecentType(@PathVariable String type){
        return service.getRecentType(type).subList(0, 6);
    }

    @GetMapping("/get-recipes/{type}")
    public List<RecipeDto> getTypes(@PathVariable String type){
        return  service.getType(type);
    }

}
