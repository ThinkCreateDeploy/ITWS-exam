package com.artkir.itws_exam.controller;

import com.artkir.itws_exam.model.Ingredient;
import com.artkir.itws_exam.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recipes/{recipeId}/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public String listFragment(@PathVariable Long recipeId, Model model) {
        model.addAttribute("recipeId", recipeId);
        model.addAttribute("ingredients", ingredientService.findByRecipe(recipeId));
        return "fragments/ingredient-fragments :: list";
    }

    @GetMapping("/{id}/row")
    public String rowFragment(@PathVariable Long recipeId, @PathVariable Long id, Model model) {
        model.addAttribute("recipeId", recipeId);
        model.addAttribute("ingredient", ingredientService.findById(id));
        return "fragments/ingredient-fragments :: row";
    }

    @GetMapping("/new-button")
    public String newButton(@PathVariable Long recipeId, Model model) {
        model.addAttribute("recipeId", recipeId);
        return "fragments/ingredient-fragments :: newButton";
    }

    @GetMapping("/new")
    public String newForm(@PathVariable Long recipeId, Model model) {
        model.addAttribute("recipeId", recipeId);
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("isNew", true);
        return "fragments/ingredient-fragments :: form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long recipeId, @PathVariable Long id, Model model) {
        model.addAttribute("recipeId", recipeId);
        model.addAttribute("ingredient", ingredientService.findById(id));
        model.addAttribute("isNew", false);
        return "fragments/ingredient-fragments :: form";
    }

    @PostMapping
    public String create(@PathVariable Long recipeId, @ModelAttribute Ingredient ingredient, Model model) {
        ingredientService.create(recipeId, ingredient);
        model.addAttribute("recipeId", recipeId);
        model.addAttribute("ingredients", ingredientService.findByRecipe(recipeId));
        return "fragments/ingredient-fragments :: list";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long recipeId, @PathVariable Long id,
                          @ModelAttribute Ingredient ingredient, Model model) {
        Ingredient saved = ingredientService.update(id, ingredient);
        model.addAttribute("recipeId", recipeId);
        model.addAttribute("ingredient", saved);
        return "fragments/ingredient-fragments :: row";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ingredientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
