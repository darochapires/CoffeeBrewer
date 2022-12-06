namespace CoffeeBrewer.Contracts.CoffeeBrewer.Recipe;

public record CreateRecipeRequest(
    string Name,
    string Description,
    Guid MethodId
);