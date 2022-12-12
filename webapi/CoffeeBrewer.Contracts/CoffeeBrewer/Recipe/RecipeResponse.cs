namespace CoffeeBrewer.Contracts.CoffeeBrewer.Recipe;

public record RecipeResponse(
    int Id,
    string Name,
    string? Description
);