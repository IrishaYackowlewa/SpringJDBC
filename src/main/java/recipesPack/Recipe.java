package recipesPack;

public class Recipe {
    private final int id;
    private final String description;
    private final String recipe;

    public Recipe(int id, String description, String recipe) {
        this.id = id;
        this.description = description;
        this.recipe = recipe;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getRecipe() {
        return recipe;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", recipe='" + recipe + '\'' +
                '}';
    }
}
