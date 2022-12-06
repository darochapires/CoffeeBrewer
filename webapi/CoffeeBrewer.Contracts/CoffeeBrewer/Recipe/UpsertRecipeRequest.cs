namespace CoffeeBrewer.Contracts.CoffeeBrewer.Recipe;

public record UpsertRecipeRequest(
    string Name,
    string Description,
    Guid MethodId
);