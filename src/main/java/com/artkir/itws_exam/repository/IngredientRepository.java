package com.artkir.itws_exam.repository;

import com.artkir.itws_exam.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByRecipeIdOrderByNameAsc(Long recipeId);
}
