package com.artkir.itws_exam.service;

import com.artkir.itws_exam.model.Ingredient;
import com.artkir.itws_exam.model.Recipe;
import com.artkir.itws_exam.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final RecipeService recipeService;

    public IngredientService(IngredientRepository ingredientRepository, RecipeService recipeService) {
        this.ingredientRepository = ingredientRepository;
        this.recipeService = recipeService;
    }

    public List<Ingredient> findByRecipe(Long recipeId) {
        return ingredientRepository.findByRecipeIdOrderByNameAsc(recipeId);
    }

    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found: " + id));
    }

    public Ingredient create(Long recipeId, Ingredient ingredient) {
        Recipe recipe = recipeService.findById(recipeId);
        ingredient.setId(null);
        ingredient.setRecipe(recipe);
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Long id, Ingredient updated) {
        Ingredient existing = findById(id);
        existing.setName(updated.getName());
        existing.setQuantity(updated.getQuantity());
        existing.setUnit(updated.getUnit());
        return ingredientRepository.save(existing);
    }

    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }
}
