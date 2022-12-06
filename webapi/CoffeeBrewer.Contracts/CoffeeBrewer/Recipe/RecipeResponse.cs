namespace CoffeeBrewer.Contracts.CoffeeBrewer.Recipe;

public record RecipeResponse(
    Guid Id,
    string Name,
    string Description,
    DateTime LastModifiedDateTime
);