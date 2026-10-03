MERGE INTO recipe (id, name, description, instructions, prep_time_minutes, servings) KEY(id) VALUES
    (1, 'Margherita Pizza', 'Classic Italian pizza with tomato, mozzarella and basil.',
       'Preheat oven to 250C with a pizza stone if you have one.' ||
       'Stretch the dough into a round base.
        Spread the tomato sauce evenly, leaving a border for the crust.
        Tear the mozzarella and scatter it over the sauce.
        Bake for 8-10 minutes until the crust is golden.
        Top with fresh basil leaves right before serving.', 30, 4),
    (2, 'Guacamole', 'Fresh and simple avocado dip.',
       'Halve and pit the avocados, scoop the flesh into a bowl.
        Mash with a fork to your preferred texture.
        Stir in the lime juice and salt.
        Taste and adjust seasoning, serve immediately.', 10, 2);

MERGE INTO ingredient (id, name, quantity, unit, recipe_id) VALUES
  (1, 'Pizza dough', 1, 'ball', 1),
  (2, 'Tomato sauce', 150, 'ml', 1),
  (3, 'Mozzarella', 200, 'g', 1),
  (4, 'Fresh basil', 10, 'leaves', 1),
  (5, 'Avocado', 2, 'pcs', 2),
  (6, 'Lime juice', 1, 'tbsp', 2),
  (7, 'Salt', 0.5, 'tsp', 2);
