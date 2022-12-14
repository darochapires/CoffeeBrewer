using CoffeeBrewer.Contracts.Util;

namespace CoffeeBrewer.Contracts.CoffeeBrewer.Recipe;

public record RecipeResponse(
    int Id,
    string Name,
    string? Description,
    double CoffeeAmount,
    double WaterTemperature,
    GrindSize GrindSize,
    int MethodId,
    double WaterAmount
    //TODO: ICollection<Step>? Steps
);