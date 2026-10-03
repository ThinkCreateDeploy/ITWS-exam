package com.artkir.itws_exam.service;

import com.artkir.itws_exam.model.Recipe;
import com.artkir.itws_exam.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Recipe findById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found: " + id));
    }

    public Recipe create(Recipe recipe) {
        recipe.setId(null);
        return recipeRepository.save(recipe);
    }

    public Recipe update(Long id, Recipe updated) {
        Recipe existing = findById(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setInstructions(updated.getInstructions());
        existing.setPrepTimeMinutes(updated.getPrepTimeMinutes());
        existing.setServings(updated.getServings());
        return recipeRepository.save(existing);
    }

    public void delete(Long id) {
        recipeRepository.deleteById(id);
    }
}
