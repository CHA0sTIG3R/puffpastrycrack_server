package com.cha0stig3r.recipe.server.controller;

import com.cha0stig3r.recipe.server.model.Recipe;
import com.cha0stig3r.recipe.server.model.RecipeDto;
import com.cha0stig3r.recipe.server.model.RequestBody;
import com.cha0stig3r.recipe.server.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    private RecipeService service;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/add-recipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addRecipe(RequestBody request) throws Exception {
        return service.addRecipe(request);
    }

    @GetMapping(value = "/url/{url}", produces = MediaType.IMAGE_JPEG_VALUE)
    public FileSystemResource getImg(@PathVariable String url){
        return new FileSystemResource(service.RESOURCES_DIR + url);
    }

    @GetMapping("/list")
    public List<RecipeDto> getRecipes(){
        return service.getRecipes();
    }
}
