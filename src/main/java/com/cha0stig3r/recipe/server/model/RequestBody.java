package com.cha0stig3r.recipe.server.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class RequestBody {
    private String name;
    private String type;
    private MultipartFile image;
    private String description;
    private List<String> ingredients;
    private List<String> directions;

    public RequestBody(String name, String type, MultipartFile image, String description, List<String> ingredients, List<String> directions) {
        this.name = name;
        this.type = type;
        this.image = image;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public MultipartFile getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getDirections() {
        return directions;
    }
}
