using CoffeeBrewer.Contracts.CoffeeBrewer.Step;
using CoffeeBrewer.Contracts.Util;

namespace CoffeeBrewer.Contracts.CoffeeBrewer.Recipe;

public record CreateRecipeRequest(
    string Name,
    string Description,
    double CoffeeAmount,
    double WaterTemperature,
    GrindSize GrindSize,
    int MethodId
);