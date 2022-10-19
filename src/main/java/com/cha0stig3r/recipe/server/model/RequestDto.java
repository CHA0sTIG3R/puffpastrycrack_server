package com.cha0stig3r.recipe.server.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class RequestDto {
    private final String name;
    private final String type;
    private final MultipartFile image;
    private final String description;
    private final List<String> ingredients;
    private final List<String> directions;

    public RequestDto(String name, String type, MultipartFile image, String description, String ingredients, String directions) {
        this.name = name;
        this.type = type;
        this.image = image;
        this.description = description;
        this.ingredients = ingredients.lines().toList();
        this.directions = directions.lines().toList();
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
