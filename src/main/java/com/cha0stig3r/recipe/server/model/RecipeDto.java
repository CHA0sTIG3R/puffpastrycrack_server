package com.cha0stig3r.recipe.server.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link Recipe} entity
 */
public record
RecipeDto(Long id, String name, String type, String description, String imgLocation,
                        List<String> ingredients, List<String> directions) implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeDto entity = (RecipeDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.type, entity.type) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.imgLocation, entity.imgLocation) &&
                Objects.equals(this.ingredients, entity.ingredients) &&
                Objects.equals(this.directions, entity.directions);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "type = " + type + ", " +
                "description = " + description + ", " +
                "imgLocation = " + imgLocation + ", " +
                "ingredients = " + ingredients + ", " +
                "directions = " + directions + ")";
    }
}