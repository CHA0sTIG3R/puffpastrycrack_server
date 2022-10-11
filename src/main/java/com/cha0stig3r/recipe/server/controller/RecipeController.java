package com.cha0stig3r.recipe.server.controller;

import com.cha0stig3r.recipe.server.model.RecipeDto;
import com.cha0stig3r.recipe.server.model.RequestBody;
import com.cha0stig3r.recipe.server.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
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
    public FileSystemResource getImg(@PathVariable String url){
        return new FileSystemResource(service.RESOURCES_DIR + url);
    }

    @GetMapping("/search-recipe")
    public List<RecipeDto> getSearched(@RequestParam String query){
        return service.getSearchedName(query);
    }

    @GetMapping("/get-recipe/All")
    public List<RecipeDto> getRecipes(){
        return service.getRecipes();
    }

    @GetMapping(value = {"/get-recipe/{type}", "/get-recipe/{type}/{amount}"})
    public List<RecipeDto> getByType(@PathVariable String type, @PathVariable(required = false) Optional<Integer> amount){
        return amount.map(integer -> service.getByType(type).subList(0, integer)).orElseGet(() -> service.getByType(type));
    }

}
