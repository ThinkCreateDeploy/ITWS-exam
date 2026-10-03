package com.artkir.itws_exam.repository;

import com.artkir.itws_exam.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
