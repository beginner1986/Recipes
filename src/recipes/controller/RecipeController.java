package recipes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.model.Recipe;

@RestController
public class RecipeController {
    Recipe recipe;

    @PostMapping("/api/recipe")
    public void postRecipe(@RequestBody Recipe recipe) {
        this.recipe = recipe;
    }

    @GetMapping("/api/recipe")
    public Recipe getRecipe() {
        return this.recipe;
    }
}
