package com.artkir.itws_exam.controller;

import com.artkir.itws_exam.model.Recipe;
import com.artkir.itws_exam.service.IngredientService;
import com.artkir.itws_exam.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class RecipeController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public RecipeController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "index";
    }

    @GetMapping("/recipes")
    public String listFragment(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "fragments/recipe-fragments :: list";
    }

    @GetMapping("/recipes/{id}/card")
    public String cardFragment(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "fragments/recipe-fragments :: card";
    }

    @GetMapping("/recipes/new-button")
    public String newButton() {
        return "fragments/recipe-fragments :: newButton";
    }

    @GetMapping("/recipes/new")
    public String newForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("isNew", true);
        return "fragments/recipe-fragments :: form";
    }

    @GetMapping("/recipes/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        model.addAttribute("isNew", false);
        return "fragments/recipe-fragments :: form";
    }

    @PostMapping("/recipes")
    public String create(@ModelAttribute Recipe recipe, Model model) {
        recipeService.create(recipe);
        model.addAttribute("recipes", recipeService.findAll());
        return "fragments/recipe-fragments :: list";
    }

    @PutMapping("/recipes/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Recipe recipe, Model model) {
        Recipe saved = recipeService.update(id, recipe);
        model.addAttribute("recipe", saved);
        return "fragments/recipe-fragments :: card";
    }

    @DeleteMapping("/recipes/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        recipeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recipes/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        model.addAttribute("recipeId", id);
        model.addAttribute("ingredients", ingredientService.findByRecipe(id));
        return "recipe-detail";
    }
}
