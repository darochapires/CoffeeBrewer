using CoffeeBrewer.Contracts.CoffeeBrewer.Step;
using CoffeeBrewer.Contracts.Util;

namespace CoffeeBrewer.Contracts.CoffeeBrewer.Recipe;

public record CreateRecipeRequest(
    string Name,
    string Description,
    double WaterTemperature,
    GrindSize GrindSize,
    int MethodId,
    List<CreateStepRequest> Steps
);