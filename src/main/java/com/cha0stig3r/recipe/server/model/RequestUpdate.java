package com.cha0stig3r.recipe.server.model;

import java.util.List;

public class RequestUpdate {
    private final String name;
    private final String type;
    private final String description;
    private final List<String> ingredients;
    private final List<String> directions;

    public RequestUpdate(String name, String type, String description, String ingredients, String directions) {
        this.name = name;
        this.type = type;
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
