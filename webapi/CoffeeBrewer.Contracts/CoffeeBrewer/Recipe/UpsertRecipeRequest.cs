using CoffeeBrewer.Contracts.Util;

namespace CoffeeBrewer.Contracts.CoffeeBrewer.Recipe;

public record UpsertRecipeRequest(
    string Name,
    string Description,
    double CoffeeAmount,
    double WaterTemperature,
    GrindSize GrindSize,
    int MethodId
);