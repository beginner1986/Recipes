package recipes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.model.Recipe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
public class RecipeController {
    private ConcurrentMap<Long, Recipe> recipes = new ConcurrentHashMap<>();

    @PostMapping("/api/recipe/new")
    public Map postRecipe(@RequestBody Recipe recipe) {
        long id = recipes.size() + 1;
        recipes.put(id, recipe);

        return Collections.singletonMap("id", id);
    }

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable long id) {
        Recipe recipe = recipes.get(id);

        if(recipe != null) {
            return recipe;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
