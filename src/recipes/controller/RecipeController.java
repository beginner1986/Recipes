package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.data.RecipeRepository;
import recipes.model.Recipe;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
public class RecipeController {

    RecipeRepository repository;

    @Autowired
    RecipeController(RecipeRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/api/recipe/new")
    public ResponseEntity<Map<String, Long> > postRecipe(@Valid @RequestBody Recipe recipe, Errors errors) {

        if(errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            repository.save(recipe);
        }

        return new ResponseEntity<>(Collections.singletonMap("id", recipe.getId()), HttpStatus.OK);
    }

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable long id) {
        Optional<Recipe> recipe = repository.findById(id);

        if(recipe.isPresent()) {
            return recipe.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/recipe/{id}")
    public void deleteRecipe(@PathVariable long id) {
        Optional<Recipe> recipe = repository.findById(id);

        if(recipe.isPresent()) {
            repository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
