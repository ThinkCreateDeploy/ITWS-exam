# Recipe Box — ITWS Exam Project

A small full-stack app for storing recipes and their ingredients.

- **Backend:** Spring Boot (Web, Data JPA, Thymeleaf, Validation)
- **Frontend:** Plain HTML + [htmx](https://htmx.org) (no JS framework, no build step)
- **Database:** H2, in-memory (zero setup)

## Requirements checklist

| Requirement | Where it's satisfied |
|---|---|
| DB with ≥ 2 tables | `recipe`, `ingredient` (see `src/main/resources/data.sql`) |
| Relation between tables | `ingredient.recipe_id` → `recipe.id` (one recipe → many ingredients) |
| CRUD on both tables | `RecipeController` (create/read/update/delete recipes), `IngredientController` (create/read/update/delete ingredients) |
| Backend | Spring Boot 3 |
| Frontend | Server-rendered HTML (Thymeleaf) + htmx for dynamic interactions, no login |
| No login / no external API | Confirmed — none used |

## Project structure

```
recipe-box/
├── pom.xml
├── src/main/java/com/example/recipebox/
│   ├── RecipeBoxApplication.java
│   ├── config/WebConfig.java        # enables PUT/DELETE form-body parsing for htmx
│   ├── model/                       # Recipe, Ingredient (JPA entities)
│   ├── repository/                  # Spring Data JpaRepository interfaces
│   ├── service/                     # business logic / CRUD
│   └── controller/                  # RecipeController, IngredientController
└── src/main/resources/
    ├── application.properties
    ├── data.sql                     # seed data (2 sample recipes)
    ├── templates/
    │   ├── index.html                # recipe list page
    │   ├── recipe-detail.html        # one recipe + its ingredients
    │   └── fragments/                 # HTML fragments returned to htmx
    └── static/css/style.css
```

## How to run

**Requirements:** Java 17+ and Maven (or use the included `mvnw` wrapper if you add one).

```bash
cd recipe-box
mvn spring-boot:run
```

Then open **http://localhost:8080** in your browser.

- H2 console (optional, to inspect the tables directly): **http://localhost:8080/h2-console**
  JDBC URL: `jdbc:h2:mem:recipebox`, user `sa`, empty password.

## How the CRUD flow works (for your presentation/demo)

1. **Recipes list** (`/`) — shows all recipes as cards. "+ New Recipe" reveals an inline
   form (`GET /recipes/new`); submitting it (`POST /recipes`) re-renders the list.
2. Each card has **Edit** (`GET /recipes/{id}/edit` swaps the card for an editable form)
   and **Delete** (`DELETE /recipes/{id}`, removes the card — cascades to its ingredients).
3. **Recipe detail** (`/recipes/{id}`) — shows the recipe plus a table of its ingredients,
   with the same add / edit / delete pattern scoped under `/recipes/{id}/ingredients/...`.
4. All of this happens **without page reloads**: htmx intercepts the form submissions and
   button clicks, calls the REST-ish endpoints, and swaps only the relevant piece of HTML
   back into the page (`hx-get`, `hx-post`, `hx-put`, `hx-delete` + `hx-target`/`hx-swap`).

## Extending it further (optional nice-to-haves)

- Add validation error messages back into the form fragments (Bean Validation is already
  wired via `@NotBlank`, you'd just need to check `BindingResult` in the controllers).
- Add a search/filter box on the recipe list (`hx-get` on `keyup` with `hx-trigger="keyup changed delay:300ms"`).
- Switch H2 for PostgreSQL/MySQL by changing `application.properties` if you want data to
  persist across restarts.
