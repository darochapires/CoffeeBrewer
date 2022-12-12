using CoffeeBrewer.Contracts.Util;

namespace CoffeeBrewer.Contracts.CoffeeBrewer.Recipe;

public record UpsertRecipeRequest(
    string Name,
    string Description,
    int MethodId,
    GrindSize GrindSize
);